require 'json'

#:nodoc:
module Ostia
  class DirectGraph
    attr_reader :nodes, :spouts, :bolts, :resourcesIO, :inputs, :mappers, :combiners, :partitioners, :reducers, :outputs, :parallelism

    def initialize
      @nodes       = {}
      @spouts_map  = {}
      @bolts_map  = {}
      @resourcesIO_map  = {}
      @inputs_map  = {}
      @mappers_map  = {}
      @combiners_map  = {}
      @partitioners_map  = {}
      @reducers_map  = {}
      @outputs_map  = {}
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
      from = from.gsub(/[.\/]/, '_')
      unless from.nil?
        from = from.gsub(/[\(\)\[\]\"]/,'')
      end
      to = to.gsub(/[.\/]/, '_')
      unless to.nil?
        to = to.gsub(/[\(\)\[\]\"]/,'')
      end
   #   add_node(from,"")
   #   add_node(to,"")
      @nodes[from] << { node: to, label: label }
      @inverse[to] << { node: from, label: label }
      self
    end

    def add_node(node, type)
      node = node.gsub(/[.\/]/, '_')
      unless node.nil?
        node = node.gsub(/[\(\)\[\]\"]/,'')
      end

      @spouts_map[node] ||= type if type == "is_spout" # first element
      @bolts_map[node] ||= type if type == "is_bolt"
      @resourcesIO_map[node] ||= type if type == "is_resourceIO"  # first element
      @inputs_map[node] ||= type if type == "is_input"
      @mappers_map[node] ||= type if type == "is_mapper"
      @combiners_map[node] ||= type if type == "is_combiner"
      @partitioners_map[node] ||= type if type == "is_partitioner"
      @reducers_map[node] ||= type if type == "is_reducer"
      @outputs_map[node] ||= type if type == "is_output"
      
      @inverse[node] ||= []
      @nodes[node] ||= []
      self
    end

    def spouts
      @spouts_map.select { |_, is_spout| is_spout }.keys
    end

    def bolts
    #  @nodes.keys - spouts
      @bolts_map.select { |_, is_bolt| is_bolt }.keys
    end

    def resourcesIO
      @resourcesIO_map.select { |_, is_resourceIO| is_resourceIO }.keys
    end
      
    def inputs
      @inputs_map.select { |_, is_input| is_input }.keys
    end
          
    def mappers
      @mappers_map.select { |_, is_mapper| is_mapper }.keys
    end
    
    def combiners
      @combiners_map.select { |_, is_combiner| is_combiner }.keys
    end
    
    def partitioners
      @partitioners_map.select { |_, is_partitioner| is_partitioner }.keys
    end
 
     def reducers
      @reducers_map.select { |_, is_reducer| is_reducer }.keys
    end
    
     def outputs
      @outputs_map.select { |_, is_output| is_output }.keys
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
      
      hash[:topology][:resourcesIO]  = []
      hash[:topology][:inputs]  = []
      hash[:topology][:mappers]  = []
      hash[:topology][:combiners]  = []
      hash[:topology][:partitioners]  = []
      hash[:topology][:reducers]  = []
      hash[:topology][:outputs]  = []
      


      spouts.each do |spout|
        hash[:topology][:spouts] << { id: spout }
      end

      bolts.each do |bolt|
        hash[:topology][:bolts] << { id: bolt,
                                     subs: in_edges(bolt),
                                     parallelism: @parallelism[bolt] }
      end
      
                                     
      resourcesIO.each do |resourceIO|
        hash[:topology][:resourcesIO] << { id: resourceIO }
      end  
      
            inputs.each do |input|
        hash[:topology][:inputs] << { id: input,
                                     subs: in_edges(input),
                                     parallelism: @parallelism[input] }
      end
      
            mappers.each do |mapper|
        hash[:topology][:mappers] << { id: mapper,
                                     subs: in_edges(mapper),
                                     parallelism: @parallelism[mapper] }
      end
      
            combiners.each do |combiner|
        hash[:topology][:combiners] << { id: combiner,
                                     subs: in_edges(combiner),
                                     parallelism: @parallelism[combiner] }
      end
      
            partitioners.each do |partitioner|
        hash[:topology][:partitioners] << { id: partitioner,
                                     subs: in_edges(partitioner),
                                     parallelism: @parallelism[partitioner] }
      end
      
            reducers.each do |reducer|
        hash[:topology][:reducers] << { id: reducer,
                                     subs: in_edges(reducer),
                                     parallelism: @parallelism[reducer] }
      end
      
            outputs.each do |output|
        hash[:topology][:outputs] << { id: output,
                                     subs: in_edges(output),
                                     parallelism: @parallelism[output] }
      end
        
                        
      
      hash.to_json
    end
  end
end
