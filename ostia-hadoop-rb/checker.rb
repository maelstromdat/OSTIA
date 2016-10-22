#:nodoc:
module Ostia
  class Checker
    def initialize(graph)
      @graph = graph.make_undirect
      @components = {}
    end

    def run
      msgs = []
      msgs << connectivity
      msgs.select { |msg| msg[:name] != :pass }
    end

    def connectivity
      @graph.keys.each.with_index(1) do |node, i|
        next unless @components[node].nil?
        deep_first_visit(node, i)
      end
      if @components.values.uniq.count > 1
        return { name: :disconnected_components,
                 metadata: { components: components }
        }
      end
      { name: :pass, metadata: {} }
    end

    private

    def components
      output = []
      @components.values.uniq.each do |id|
        output << @components.select { |_, v| v == id }.map(&:first)
      end
      output
    end

    def deep_first_visit(node, component_id)
      @components[node] = component_id
      @graph[node].each do |neighbor|
        next if !@components[neighbor].nil?
        deep_first_visit(neighbor, component_id)
      end
    end
  end
end
