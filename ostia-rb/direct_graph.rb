require 'json'

#:nodoc:
module Ostia
  class DirectGraph
    attr_reader :nodes, :spouts, :parallelism

    def initialize
      @nodes       = {}
      @spouts_map  = {}
      @inverse     = {}
      @parallelism = {}
    end

    def add_parallelism(node_name, parallelism)
      @parallelism[node_name] = parallelism.to_i
    end

    def fan_in
      @fan_in ||= @inverse.each_with_object({}) do |data, fan_in_hash|
        node = data.first
        other = data.last
        fan_in_hash[node] = other.count
      end
    end

    def fan_out
      @fan_out ||= @nodes.each_with_object({}) do |data, fan_out_hash|
        node = data.first
        other = data.last
        fan_out_hash[node] = other.count
      end
    end

    # (fan_in * parallelism of each node) / parallelism of this node
    def weighted_fan_in
      @inverse.each_with_object({}) do |data, fan_in_hash|
        node = data.first
        other = data.last
        next if other.count.zero?
        weight = 0
        other.each do |neighbor|
          node_name = neighbor[:node]
          weight += @parallelism[node_name]
        end
        fan_in_hash[node] = weight / @parallelism[node].to_f
      end
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

    def make_undirect
      undirect = {}
      @nodes.each do |node, neighbors|
        neighbor_names = neighbors.map { |hash| hash[:node] }
        neighbor_names.each do |name|
          undirect[name] ||= []
          undirect[node] ||= []
          undirect[name] << node
          undirect[node] << name
        end
      end
      undirect
    end

    def generate_dot
      str  = 'digraph G {'
      @nodes.each do |node, neighbors|
        next if neighbors.empty?
        neighbors.each do |destination|
          parallelism_source = @parallelism[node] ? "_#{@parallelism[node]}" : ''
          parallelism_destination = @parallelism[destination[:node]] ? "_#{@parallelism[destination[:node]]}" : ''
          str += "\n  "\
                 + node + parallelism_source + ' -> ' \
                 + destination[:node] +  parallelism_destination\
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
        hash[:topology][:bolts] << { id: bolt,
                                     subs: in_edges(bolt),
                                     parallelism: @parallelism[bolt] }
      end

      hash.to_json
    end
  end
end
