#!/usr/bin/env ruby

require 'ap'

#:nodoc:
class DirectGraph
  def initialize
    @nodes = {}
  end

  def add_link(from, to, label)
    @nodes[from] ||= []
    @nodes[from] << { node: to, label: label }
    self
  end

  def add_node(node)
    @nodes[node] = []
    self
  end

  def output(type)
    case type
    when :dot
      generate_dot
    else
      puts "Available output format: .dot"
      exit 1
    end
  end

  private

  def generate_dot
    str  = "digraph G {"
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
end

#:nodoc:
class Ostia
  def initialize(args)
    if args.size != 1
      puts 'Usage: ./ostia.rb <file_with_topologyBuilder>'
      exit 1
    end
    @filename = args.first
    @graph = DirectGraph.new
  end

  def run
    @source_code = File.open(@filename).read
    lines = useful_lines
    generate_graph(lines)
    puts @graph.output(:dot)
  end

  private

  def useful_lines
    spouts = @source_code.scan(%r{setSpout\("\w+".*\)})
    bolts = @source_code.scan(%r{setBolt\("\w+".*\)(?:\s*\..*)*})
            .map { |line| line.gsub(/\s/, '') }
    spouts + bolts
  end

  def generate_graph(lines)
    lines.each do |line|
      methods = line.split('.')
      destination_node = extract_component_name(methods.first)
      if methods.size == 1
        @graph.add_node(destination_node)
      else
        methods.each_with_index do |line, i|
          next if i == 0
          source_node = extract_component_name(line)
          label = extract_label(line)
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

Ostia.new(ARGV).run
