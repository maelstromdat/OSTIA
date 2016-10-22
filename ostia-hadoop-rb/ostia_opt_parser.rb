require 'trollop'

module Ostia
  VERSION = '0.1'
  OUTPUT_FORMATS = %w(dot json)

  class OstiaOptParser
    def initialize(args)
      @args = args
    end

    def run
      opts = Trollop::options do
        opt :topology, "File containing the topology builder", type: String
        opt :format, "Output format:  #{OUTPUT_FORMATS.join(', ')}", type: String
        opt :machines, "Number of VMs (for performance analysis)", type: Integer
        opt :tasks_per_machine, "Max number of tasks for each machine (for performance analysis)", type: Integer
        opt :output, "Output file", type: String
      end

      if opts[:topology].nil? || !File.exist?(opts[:topology])
        Trollop::die :topology, "must exist"
      end
      if opts[:format].nil? || !OUTPUT_FORMATS.include?(opts[:format])
        Trollop::die :format, "must be one of the these: #{OUTPUT_FORMATS.join(', ')}"
      end
      opts
    end
  end
end
