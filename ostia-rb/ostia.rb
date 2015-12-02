#!/usr/bin/env ruby

require './direct_graph'
require './checker'

#:nodoc:
module Ostia
  VERSION = '0.1'
  OUTPUT_FORMATS = %(dot json)

  class Runner
    def initialize(args)
      if args.size != 3 || !OUTPUT_FORMATS.include?(args.last)
        puts "Usage: ./ostia.rb <file_with_topologyBuilder>\
 <format: #{OUTPUT_FORMATS.join(', ')}>"
        exit 1
      end
      @filename    = args[0]
      @output_file = args[1]
      @format      = args[2]
      @graph       = DirectGraph.new
    end

    def run
      @source_code = File.open(@filename).read
      lines = useful_lines
      generate_graph(lines)
      problems = Checker.new(@graph).run
      if problems.any?
        puts "#{problems.size} reported:"
        problems.each do |problem|
          puts problem
        end
      else
        puts 'No errors detected'
      end
      file = File.open(@output_file, 'w')
      file.puts @graph.send("generate_#{@format}")
      file.close
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
end

Ostia::Runner.new(ARGV).run
