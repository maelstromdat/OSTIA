#:nodoc:
module Ostia
  class Storm_parser
    
      def generate_graph_storm(graph,spouts,bolts)
        
#------  generate spouts -----------        
          spouts.each do |line|
            puts line
            methods = line.split('.')
            destination_node = extract_component_name(methods.first)
            extract_parallelism(graph,destination_node, line)
            
            #aggiungo spout
            graph.add_node(destination_node, "is_spout")
            puts "aggiunto spout:" + destination_node
          end
      
#------- generate bolts ------------      
          bolts.each do |line|
            source_node = extract_component_name(line)
            label = extract_label(line)
            extract_parallelism(graph,source_node, line)
            
            #aggiungo bolt
            graph.add_node(source_node, "is_bolt")
            puts "aggiunto bolt:" + source_node
            
            # aggiungo i link
            destinations = line.scan(/Grouping\(\"(\w+)/)
            destinations.each do |destination|
                destination_node = destination.first
                graph.add_link(destination_node,source_node, label)
            end
          end
    end  
    


    def extract_parallelism(graph,node, line)
      parallelism =  line.scan(/(\d+)\s*\)/).first
      parallelism = parallelism.nil? ? nil : parallelism.first
      graph.add_parallelism(node, parallelism)
    end



    def extract_component_name(line)
      unless line.scan(/\"(\w+)\"/).first.nil? #controllo che non sia nil
       result = line.scan(/\"(\w+)\"/).first.first
      else
      end
      return result
    end
    


    def extract_label(line)
      unless line.scan(/(\w+Grouping)\(/).first.nil? #controllo che non sia nil
      line.scan(/(\w+Grouping)\(/).first.first
      else " "
      end
    end
    
    
  end
end