#!/usr/bin/env ruby

require './ostia_opt_parser'
require './direct_graph'
require './checker'
require './performance_booster'

#:nodoc:
module Ostia

  class Runner
    def initialize(args)
      opts = OstiaOptParser.new(args).run
      @filename = opts[:topology]
      @output_file = opts[:output]
      @format = opts[:format]
      @machines = opts[:machines]
      @tasks_per_machine = opts[:tasks_per_machine]
      @graph = DirectGraph.new
    end

    def run
      @source_code = File.open(@filename).read
      lines = useful_lines
      generate_graph(lines)

      problems = Checker.new(@graph).run
      if problems.any?
        puts "#{problems.size} problem reported:"
        problems.each do |problem|
          puts problem
        end
        puts ""
      end

      if @machines && @tasks_per_machine
        suggestions = PerformanceBooster.new(@graph,
                                             @machines,
                                             @tasks_per_machine).run
        if suggestions.any?
          puts "#{suggestions.size} performance issue reported:"
          suggestions.each do |suggestion|
            ap suggestion
          end
          puts ""
        end
      end

      str = @graph.send("generate_#{@format}")
      if @output_file
        file = File.open(@output_file, 'w')
        file.puts(str)
        file.close
        puts "Done."
      else
        puts str
      end
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
        extract_parallelism(destination_node, line)
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

    def extract_parallelism(node, line)
      parallelism =  line.scan(/(\d+)\s*\)/).first
      parallelism = parallelism.nil? ? nil : parallelism.first
      @graph.add_parallelism(node, parallelism)
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
