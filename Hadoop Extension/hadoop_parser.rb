require 'set'
#:nodoc:
module Ostia
  class Hadoop_parser
     
    def initialize
		@InputPath = Array.new    	# array che contiene il nome dei path di ingresso ai mapper (serve a collegare piÃº job)
		@OutputPath = Array.new    	# array che contiene¡ il nome dei path di ingresso ai mapper (serve a collegare piÃº job)
		@OpenLink = Array.new     	# array che contiene¡ il nome dei nodi con link pendenti
		@Jobs = Set.new				      # Set che contiene tutti i nomi dei job
		@InOut = Set.new            # Set che contiene tutti i nomi delle risorse input e output
    end
    
	
    def generate_graph_hadoop(graph,inputs,mappers,chains,combiners,partitioners,reducers,numReduceTask,outputs,namedOutputs)

          generate_input(graph,inputs)     
      		generate_mapper(graph,mappers)
        	generate_chain(graph,chains)
      		generate_combiner(graph,combiners)
      		generate_partitioner(graph,partitioners,numReduceTask)
      		generate_reducer(graph,reducers,numReduceTask)
          generate_output(graph,outputs,namedOutputs)

    end   


#=====================================================================================================================    
# =>                             generate_input
# =>  note:     (le risorse di input e output sono indipendenti dai job)
#=====================================================================================================================    


    def generate_input(graph,inputs)
      
puts"--- INPUT --------"    

       # ciclo gli Inputs
       inputs.each_with_index do |inline,i|
           fields = inline.split(',')
           
#---------------------creo l'elenco dei job -------------------------

           found_job = fields[0].split('(').last
           @Jobs << found_job
             
             
#---------------------creo l'elenco delle risorse IO ----------------

           io_resource = "IO_" + fields[1].chomp(')')
           unless @InOut.include? io_resource
              #Se non é giá stata creata, aggiungo la risorsa di IO al grafo
              graph.add_node("\"" + io_resource + "\"","is_resourceIO")
              puts "aggiunta risorsa IO: " + io_resource
              @InOut << io_resource
           end 
             
             
#---------------------creo gli elementi INPUT -----------------------        
     
            #aggiungo il nodo di input e il collegamento con la risorse IO 
            input_name = found_job + "." + "INPUT_" + fields[1].chomp(')')               
            
            #aggiungo il nodo   
            graph.add_node("\"" + input_name + "\"","is_input")
            puts "aggiunto input: " + input_name
            
            # aggiungo il link
            graph.add_link(io_resource, input_name,"")
            puts "aggiunto link: " + "\"" + io_resource + "\"" + "->" + input_name
                
             
             # analizzo gli inputPath Multipli       
             unless fields[3].nil? 
                  classe = fields[3].split('.') 
                  mapper_name = found_job + "." + "MAPPER_" + classe[0]
                  
                  #aggiungo il nodo
                  graph.add_node(mapper_name,"is_mapper")
                  puts "aggiunto multiMapper: " + mapper_name
                  
                  # aggiungo il link
                  graph.add_link(input_name, mapper_name,"")
                  puts "aggiunto link: " + "\"" + input_name + "\"" + "->" + mapper_name
                    
                  # memorizzo i link aperti (per creare i futuri link) 
                  @OpenLink.push(mapper_name);               
                                 
                        
            else  # allore é un iputPath semplice che punta al mapper class definito
                  #inserisco l'input nella lista dei nodi aperti per il job corrent
                  @OpenLink.push(input_name);
            
            end 
        end 

  end



  
#===================================================================================================================== 
# =>                                generate_mapper   
#=====================================================================================================================    
   def generate_mapper(graph,mappers)
     
   puts"--- MAPPER --------"
           
      @Jobs.each do |current_job|        
       
         # ciclo i mapper
         mappers.each_with_index do |inline,i|

             fields = inline.split('.')
  
             # se il job da analizzare é uguale a quello corrente =>
             if current_job == fields[0] 
                current_other = fields[1]
    
                mapper_name = current_job + "." + "MAPPER_" + current_other.split('.')[0].split('(')[1]
                
                #aggiungo il nodo mapper
                graph.add_node(mapper_name,"is_mapper")
                puts "aggiunto mapper: " + mapper_name
                 
                # chiudo i nodi pendenti ( job per job) 
                Close_ALL_pending_links(graph,current_job,mapper_name) # il nome Ã© nella forma job.command(
    
                # memorizzo i link aperti (per creare i futuri link)
                @OpenLink.push(mapper_name); 
             end
         end 
      end
     
  end
  
  


#=====================================================================================================================    
# =>                                 generate_combiner
#=====================================================================================================================    
     
   def generate_combiner(graph,combiners)
   
   puts"--- COMBINER ------"
   
      @Jobs.each do |current_job| 

           if combiners.size == 0
              return
           end
            
           fields = combiners.first.split('.')
          
           # se il job da analizzare é uguale a quello corrente
           if current_job == fields[0] 
                combiner_name = current_job + "." + "COMBINER_" + fields[1].split('(')[1]
                
                #aggiungo il nodo combiner
                graph.add_node(combiner_name,"is_combiner")
                puts "aggiunto combiner:" + combiner_name
                
                # chiudo i nodi pendenti ( job per job) 
                Close_ALL_pending_links(graph,current_job,combiner_name)
                
                # memorizzo i link aperti (per creare i futuri link)
                puts "sto aggiungendo" + combiner_name
                @OpenLink.push(combiner_name);
                
            end  
     end #do jobs
    
  end




#=====================================================================================================================    
# =>                                generate_partitioner
#=====================================================================================================================          
       
   def generate_partitioner(graph,partitioners,numReduceTask)
      
   puts"--- PARTITIONER ---"    
         
      @Jobs.each do |current_job| 
  
        if partitioners.size == 0
          return
        end
  
        numReduceTask_int = 1 # valore di default
      
        unless numReduceTask.size == 0
            numReduceTask.each do |inline|
              
                fields = inline.split('.')
                if current_job == fields[0]  #se viene definito un setNumTask per qul job
                     numReduceTask_int = fields[1].scan(/\d+/)
                     numReduceTask_int = numReduceTask_int.first.to_i
                else
                    next
                end
            end 
          
          if numReduceTask_int == 0 # viene imposto che non ci sará il reducer
              next # passo al prossimo ciclo di job
          end  
        end 

       fields = partitioners.first.split('.')
      
       # se il job da analizzare é uguale a quello corrente => lo lavoro
       if current_job == fields[0] 
          partitioner_name = current_job + "." + "PARTITIONER_" + fields[1].split('(')[1]
      
          #aggiungo il nodo partitioner
          graph.add_node(partitioner_name,"is_partitioner")
          puts "aggiunto partitioner:" + partitioner_name
      
          # chiudo i nodi pendenti ( job per job) 
          Close_ALL_pending_links(graph,current_job,partitioner_name)
          
          for i in(1..numReduceTask_int)
            @OpenLink.push(partitioner_name);  
          end   
       end   
    end #do jobs
      
  end
   

  
#===================================================================================================================== 
# =>                             generate_reducer  
#=====================================================================================================================      


  def generate_reducer(graph,reducers,numReduceTask)

  puts"--- REDUCER -------"
  
      @Jobs.each do |current_job|   
      
      # -------------- definisco il numReduceTask (per ogni job) -------------- 
        numReduceTask_int = 1   # valore di default
      
        unless numReduceTask.size == 0
            numReduceTask.each do |inline|
              
            fields = inline.split('.')
            if current_job == fields[0]  #se viene definito un setNumTask per qul job
                 numReduceTask_int = fields[1].scan(/\d+/)
                 numReduceTask_int = numReduceTask_int.first.to_i
            else
                next
            end
          end # do numReduceTask
          
          # viene imposto che non ci sará il reducer => passo al prossimo ciclo di job
          if numReduceTask_int == 0 
            next 
          end  
        end   #fine size==0
    
    
    # ---------------- ora posso definire i reducer con le quantitá corrette ------------------

        reducers.each_with_index do |inline,i|

        fields = reducers.first.split('.')
        
        # se il job da analizzare é uguale al job corrente => lo lavoro
        if current_job == fields[0]
            if numReduceTask.first == 0
              next
            end
        
           reducer_name = current_job + "." + "REDUCER_" + fields[1].split('(')[1]

            #aggiungo il nodo reducer
            if numReduceTask_int == 1 # Reducer singolo

                graph.add_node(reducer_name,"is_reducer")
                puts "aggiunto reducer:" + reducer_name
                
                Close_ALL_pending_links(graph,current_job,reducer_name)
                @OpenLink.push(reducer_name);  
              
            else # Reducer multiplo
              
                for i in(1..numReduceTask_int)
                  
                  reducer_unique_name = reducer_name + "_#{i}" # il reducer Ã© sempre lo stesso, per discriminarlo aggiungo un suffisso
                  
                  graph.add_node(reducer_unique_name,"is_reducer")
                  puts "aggiunto reducer:" + reducer_unique_name

                  #aggiungo il link tra l'i-esimo nodo pendente (dal partitioner) e l'i-esimo reducer
                  graph.add_link(@OpenLink.first, reducer_unique_name, "")
                  puts "aggiunto link:" + @OpenLink.first + "->" + reducer_unique_name
                  
                  #elimino il primo elemento dell'array dei nodi pendenti ( ormai é stato collegato)
                  @OpenLink.shift;
              
                  # creo i nuovi link pendenti
                  @OpenLink.push(reducer_unique_name);
                
                end
            end # fine multiReducer
          end # if currient_job
        end # do reducers
    end #do jobs 

  end


	 
    
#=====================================================================================================================
# =>                           generate_chain 
#=====================================================================================================================       
    
    
    def generate_chain(graph,chains)
            
     puts "--- CHAIN ---------"
            
      @Jobs.each do |current_job|            
         
         name = Array.new
         
         # ciclo sui nodi della catena 
         chains.each_with_index do |in_line,i|
            fields = in_line.split(',')
            classe = fields[1].split('.')     
            
            # controllo se e un mapper
            node = in_line.scan(/addMapper\(\w+.*\)/).map { |line| line.gsub(/\s/, '') }
            unless node.size == 0
              type = "MAPPER_" 
              is_what = "is_mapper"
            end
            
            # controllo se e un reducer
               node = in_line.scan(/setReducer\(\w+.*\)/).map { |line| line.gsub(/\s/, '') }
            unless node.size == 0
              type = "REDUCER_"
              is_what = "is_reducer"
            end

            if current_job == fields[0].split('(').last # se il job da analizzare é uguale al job del mapper => lo lavoro
                   
                name[i] = current_job + "." + type + fields[1].split('(').last.split('.').first
    
                graph.add_node(name[i],is_what)
                puts "aggiunto nodo chain:" + name[i]
                
                # se non é il primo nodo della catena => chiudo i nodi pendenti
                if i==0
                    Close_ALL_pending_links(graph,current_job,name[i])
                end
                
                # se non é il primo nodo della catena => aggiungo il link
                unless i==0 
                  graph.add_link(name[i-1], name[i], "")
                  puts "aggiunto link:" + name[i-1] + "->" + name[i]
                end
                
                # memorizzo l'ultimo nodo della catena ( mi servirá pi u avanti per definire i link)
                @last_mapper_name = name[i]

             end #if current job 
         end #do cycle
         
         unless @last_mapper_name.nil?
          @OpenLink.push(@last_mapper_name); # l'ultimo nodo rimane con link pendente
         end
       
    end # do jobs       

  end #fine method chain
  
  
  
#===================================================================================================================== 
# =>                              generate_output  
#=====================================================================================================================  

  def generate_output(graph,outputs,namedOutputs)
    
   puts "--- OUTPUT --------"   
 

#------------------- aggiungo le risorse -------------------------
         # ciclo gli output per identificare nuove risorse IO
         outputs.each_with_index do |inline,i|
             fields = inline.split(',')      
             found_job = fields[0].split('(').last
             io_resource = "IO_" + fields[1].chomp(')')
             
             #Se non é giá stata creata, aggiungo la risorsa al set a al grafo
             unless @InOut.include? io_resource
                graph.add_node("\"" + io_resource + "\"","is_resourceIO")
                puts "aggiunta risorsa IO: " + io_resource
                @InOut << io_resource
             end   
         end
         
#--------------------- aggiungo gli output ----------------------------  
     @Jobs.each do |current_job|                    
         # per ogni job ciclo i propri output
         outputs.each_with_index do |inline,i|
           
             fields = inline.split(',')
             found_job = fields[0].split('(').last
             io_resource = "IO_" + fields[1].chomp(')')

             # se il job da analizzare é uguale a quello letto => lo analizzo
             if current_job == found_job     
                         
                #aggiungo il nodo di output e il collegamento con la risorse IO      
                output_name = found_job + "." + "OUTPUT_" + fields[1].chomp(')') 
                     
                graph.add_node("\"" + output_name + "\"","is_output")
                puts "aggiunto output: " + output_name
                
                graph.add_link(output_name,io_resource,"")
                puts "aggiunto link: " + "\"" + output_name + "\"" + "->" + io_resource
        
                # chiudo i nodi pendenti ( job per job)    
                Close_ALL_pending_links(graph,current_job,output_name)     
             end
               
 #------------------------- aggiungo i file name---------------------------------------------------------            
             # ci filename nomi customizzati
             unless namedOutputs.size == 0
                 namedOutputs.each do |nameline| 
                   
                 fields_nameline = nameline.split(',') 
                 if current_job == fields_nameline[0].split('(').last
                   namedOutput = "FILE_" + fields[1].chomp(')') + "File_" + fields_nameline[1].chomp(')')
                   
                   graph.add_node("\"" + namedOutput + "\"","is_file")
                   puts "aggiunto output: " + namedOutput
                
                   graph.add_link(io_resource,namedOutput,"")
                   puts "aggiunto link: " + "\"" + io_resource + "\"" + "->" + namedOutput 
                end
               end    
             end
             
             
        end #do outputs      
    end # do jobs
    
    
  end

  
  
    
    
#=====================================================================================================================    
# =>                    Close_ALL_pending_links
#=====================================================================================================================    
        
    def Close_ALL_pending_links(graph,current_job,destination_node)
  
        node_to_delete = Array.new
      
        # chiudo i link aperti, ciclando sull'array dei nodi pendenti
        @OpenLink.each_with_index do |source_node,i|
          
            unless source_node.nil?
              if source_node.split('.')[0].nil? # allora ho passato un output
                 # aggiungo il link
                 graph.add_link(source_node, current_job + "." + destination_node, "")  
                 puts "aggiunto link #{i}:" + source_node + "->" + current_job + "." + destination_node
              end
            
              if source_node.split('.')[0] == current_job
                 # aggiungo il link
                 graph.add_link(source_node, destination_node, "")          
                 puts "aggiunto link:" + source_node + "->" + destination_node
              end        
            end
        end
        
        #dopo aver collegato tutti i link aperti di quel job, creo la lista degli elementi da cancellare  
          @OpenLink.each do |source_node|
              unless source_node.nil?
                  if source_node.split('.')[0] == current_job
                     node_to_delete << source_node
                  end
              end
          end
           # elimino dall'array gli elementi selezionati
           node_to_delete.each do|delete_node|
              @OpenLink.delete(delete_node);
           end

    end

#=====================================================================================================================      
#=====================================================================================================================    
  
  
 end
end