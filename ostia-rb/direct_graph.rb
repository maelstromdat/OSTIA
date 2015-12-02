require 'json'

#:nodoc:
module Ostia
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
end
