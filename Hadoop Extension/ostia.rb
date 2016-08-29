#!/usr/bin/env ruby

require './ostia_opt_parser'
require './direct_graph'
require './checker'
require './performance_booster'
require './hadoop_parser.rb'
require './storm_parser.rb'

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
      @hadoopParser = Hadoop_parser.new
      @stormParser = Storm_parser.new
      
    end

    def run
      @source_code = File.open(@filename).read
      lines = scan_file

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
        puts "=== Done ======================================="
      else
        puts str
      end
      
    end



    private

    def scan_file

      # TODO: comments
      # estraggo le sole righe utili, cercando le corrispondenze (rimuovendo prima eventuali spazi)

      # storm keywords
      spouts = @source_code.scan(/setSpout\("\w+".*\)/) .map { |line| line.gsub(/\s/, '') }
      bolts = @source_code.scan(/setBolt\("\w+".*\)(?:\s*\..*)*/) .map { |line| line.gsub(/\s/, '') }
            
      # hadoop keywords  
      inputs = @source_code.scan(/addInputPath\(\w+.*\)|setInputPaths\(\w+.*\)/)   .map { |line| line.gsub(/\s/, '') }  
      mappers =@source_code.scan(/\S*.setMapperClass\(\w+.*\)/)   .map { |line| line.gsub(/\s/, '') }
      chains = @source_code.scan(/\S*.addMapper\(\w+.*\)|\S*.setReducer\(\w+.*\)/)  .map { |line| line.gsub(/\s/, '') } # devo mantenere l'ordine della catena
      partitioners = @source_code.scan(/\S*.setPartitionerClass\(\w+.*\)/)  .map { |line| line.gsub(/\s/, '') }
      numReduceTask = @source_code.scan(/\S*.setNumReduceTasks\(\w+.*\)/) .map { |line| line.gsub(/\s/, '') }
      combiners = @source_code.scan(/\S*.setCombinerClass\(\w+.*\)/) .map { |line| line.gsub(/\s/, '') }
      reducers = @source_code.scan(/\S*.setReducerClass\(\w+.*\)/)   .map { |line| line.gsub(/\s/, '') }
      outputs = @source_code.scan(/setOutputPath\(\w+.*\)/)  .map { |line| line.gsub(/\s/, '') }
      namedOutputs = @source_code.scan(/addNamedOutput\(\w+.*\)/)  .map { |line| line.gsub(/\s/, '') }

      
      puts "===== Righe notevoli rilevate ================="

      if spouts.size + bolts.size > 0
        puts "Spouts\t\t #{spouts.size}"                # + inputs.inspect
        puts "Bolts:\t\t #{bolts.size}"                 # + mappers.inspect
        puts "=== Riconosciuta struttura storm ================"

        @stormParser.generate_graph_storm(@graph,spouts, bolts)
      else

        puts "Input:\t\t #{inputs.size}"                # + inputs.inspect
        puts "Mapper:\t\t #{mappers.size}"              # + mappers.inspect
        puts "Chain nodes:\t #{chains.size}"            # + chains.inspect
        puts "Combiners:\t #{combiners.size}"           # + combiners.inspect
        puts "Partitioners:\t #{partitioners.size}"     # + partitioners.inspect
        puts "Reducers:\t #{reducers.size}"             # + reducers.inspect
        puts "NumReduceTask:\t #{numReduceTask.size}"   # + numReduceTask.inspect
        puts "Outputs:\t #{outputs.size}"               # + outputs.inspect
        puts "NamedOutputs:\t #{namedOutputs.size}"     # + namedOutputs.inspect
        puts "===== Riconosciuta struttura hadoop =============="   
        
        @hadoopParser.generate_graph_hadoop(@graph,inputs,mappers,chains,combiners, partitioners, reducers, numReduceTask, outputs, namedOutputs)
      end
  
end    
end  
end



Ostia::Runner.new(ARGV).run
