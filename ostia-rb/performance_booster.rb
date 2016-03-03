require 'ap'
require 'pp'

#:nodoc
module Ostia
  class PerformanceBooster
    def initialize(graph, machines, tasks_per_machine)
      @graph = graph
      @machines = machines
      @tasks_per_machine = tasks_per_machine
    end

    def run
      msgs = []
      msgs << parallelism
      msgs.select { |msg| msg[:name] != :pass }
    end

    def parallelism
      if total_slots_available < total_tasks
        { name: :fail,
          msg: "Total slots available is #{total_slots_available}, #{total_tasks} needed" }
      else
        free_slots = total_slots_available - total_tasks
        return { name: :pass } if free_slots.zero?
        { name: :warning,
          msg: "Free slots #{free_slots}. Suggestions available.",
          suggestions: parallelism_suggestion(free_slots) }
      end
    end

    def parallelism_suggestion(free_slots)
      suggestions = []

      # Is is possible to remove some machines?
      superfluous_machines = (free_slots / @tasks_per_machine)
      if superfluous_machines > 0
        suggestions << "Remove #{superfluous_machines} machines"
      end

      # Increase parallelism level of some components
      max_fan_in_nodes = @graph.weighted_fan_in.select do |node, w_fan_in|
        w_fan_in == @graph.weighted_fan_in.values.max
      end

      nested = []
      amount_to_give = free_slots / max_fan_in_nodes.count
      max_fan_in_nodes.each do |node, _|
        nested << "Increase parallelism of node #{node} \
from #{@graph.parallelism[node]} to #{@graph.parallelism[node] + amount_to_give}"
      end
      suggestions << nested
      suggestions
    end

    def total_tasks
      @total_tasks ||= @graph.parallelism.values.inject(:+)
    end

    def total_slots_available
      @total_slots_available ||= @tasks_per_machine * @machines
    end
  end
end
