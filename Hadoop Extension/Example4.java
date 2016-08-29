public class WordCountSorting {
// here the word keys shall be sorted
      //let us write the wordcount logic first

      public static void main(String[] args)throws IOException,InterruptedException,ClassNotFoundException {
            //THE DRIVER CODE FOR MR CHAIN
            Configuration conf1=new Configuration();
            Job j1=Job.getInstance(conf1);
            j1.setJarByClass(WordCountSorting.class);
            j1.setMapperClass(MyMapper.class);
            j1.setReducerClass(MyReducer.class);

            j1.setMapOutputKeyClass(Text.class);
            j1.setMapOutputValueClass(IntWritable.class);
            j1.setOutputKeyClass(LongWritable.class);
            j1.setOutputValueClass(Text.class);
            Path outputPath=new Path("FirstMapper");
            FileInputFormat.addInputPath(j1,new PathArgs0);
                  FileOutputFormat.setOutputPath(j1,outputPath);
                  outputPath.getFileSystem(conf1).delete(outputPath);
            j1.waitForCompletion(true);
                  Configuration conf2=new Configuration();
                  Job j2=Job.getInstance(conf2);
                  j2.setJarByClass(WordCountSorting.class);
                  j2.setMapperClass(MyMapper2.class);
                  j2.setNumReduceTasks(0);
                  j2.setOutputKeyClass(Text.class);
                  j2.setOutputValueClass(IntWritable.class);
                  Path outputPath1=new Path(args[1]);
                  FileInputFormat.addInputPath(j2, outputPath);
                  FileOutputFormat.setOutputPath(j2, outputPath1);
                  outputPath1.getFileSystem(conf2).delete(outputPath1, true);
                  System.exit(j2.waitForCompletion(true)?0:1);
      }

}
