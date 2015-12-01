#!/usr/bin/env ruby

require 'ap'
require 'json'

#:nodoc:
class DirectGraph
  def initialize
    @nodes      = {}
    @spouts_map = {}
    @inverse    = {}
  end

  def add_link(from, to, label)
    add_node(from)
    add_node(to)

    @nodes[from] << { node: to, label: label }
    @inverse[to] << { node: from, label: label }
    self
  end

  def add_node(node, is_spout: false)
    @spouts_map[node] ||= is_spout
    @inverse[node] ||= []
    @nodes[node] ||= []
    self
  end

  def spouts
    @spouts_map.select { |_, is_spout| is_spout }.keys
  end

  def bolts
    @nodes.keys - spouts
  end

  def in_edges(node)
    @inverse[node].map { |h| h[:node] }
  end

  def generate_dot
    str  = 'digraph G {'
    @nodes.each do |node, neighbors|
      next if neighbors.empty?
      neighbors.each do |destination|
        str += "\n  " \
               + node + ' -> ' \
               + destination[:node] \
               + " [label=\"#{destination[:label]}\"]"
      end
    end
    str += "\n}"
  end

  def generate_json
    hash = {}
    hash[:topology] = {}
    hash[:topology][:spouts] = []
    hash[:topology][:bolts]  = []

    spouts.each do |spout|
      hash[:topology][:spouts] << { id: spout }
    end

    bolts.each do |bolt|
      hash[:topology][:bolts] << { id: bolt, subs: in_edges(bolt) }
    end

    hash.to_json
  end
end

#:nodoc:
class Ostia
  OUTPUT_FORMATS = %w(dot json)

  def initialize(args)
    if args.size != 2 || !OUTPUT_FORMATS.include?(args.last)
      puts "Usage: ./ostia.rb <file_with_topologyBuilder>\
 <format: #{OUTPUT_FORMATS.join(', ')}>"
      exit 1
    end
    @filename = args.first
    @format = args.last
    @graph = DirectGraph.new
  end

  def run
    @source_code = File.open(@filename).read
    lines = useful_lines
    generate_graph(lines)
    @graph.send("generate_#{@format}")
  end

  private

  def useful_lines
    # TODO: comments
    spouts = @source_code.scan(/setSpout\("\w+".*\)/)
    bolts = @source_code.scan(/setBolt\("\w+".*\)(?:\s*\..*)*/)
            .map { |line| line.gsub(/\s/, '') }
    spouts + bolts
  end

  def generate_graph(lines)
    # TODO: stream_id
    lines.each do |line|
      methods = line.split('.')
      destination_node = extract_component_name(methods.first)
      if methods.size == 1
        @graph.add_node(destination_node, is_spout: true)
      else
        methods.each_with_index do |method, i|
          next if i == 0
          source_node = extract_component_name(method)
          label = extract_label(method)
          @graph.add_link(source_node, destination_node, label)
        end
      end
    end
  end

  def extract_component_name(line)
    line.scan(/\"(\w+)\"/).first.first
  end

  def extract_label(line)
    line.scan(/(\w+Grouping)\(/).first.first
  end
end

puts Ostia.new(ARGV).run
