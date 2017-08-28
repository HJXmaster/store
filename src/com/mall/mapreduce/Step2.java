package com.mall.mapreduce;

import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Step2 {
	static class MyMapper  extends  Mapper<LongWritable, Text, Text, Text>{  

		 public void map(LongWritable k1, Text v1, Context context) 
						 throws java.io.IOException, java.lang.InterruptedException
		 {
			String[] lines=v1.toString().split("\\s+");
			String[] cid=lines[1].split(";");
			for(int i=0;i<cid.length;i++){
				String[] a=cid[i].split(",");
				
				for(int j=0;j<cid.length;j++){
					String[] b=cid[j].split(",");
					if(!a[0].equals(b[0])){
						context.write(new Text(a[0]+","+b[0]), new Text(lines[0]));
						System.out.println("key:"+a[0]+","+b[0]);
					}
				}
			}
			System.out.println("map "+v1.toString().split("\\s+")[0]);
		 }
		 
//		 public void cleanup(Context context) throws java.io.IOException, java.lang.InterruptedException{
//			 context.write(new IntWritable(i), new LongWritable(sum));
//		 }
		
	}
	
	static class  MyReduce extends Reducer<Text, Text, Text, NullWritable>{

		public void reduce(Text k2, Iterable<Text> v2, Context context) throws java.io.IOException, java.lang.InterruptedException
		 {
			HashMap<String,Integer> map=new HashMap<String,Integer>();
			for(Text value:v2){
				map.put(value.toString(), 1);
			}
			
			System.out.println("key:"+k2.toString()+" "+map.keySet().size());
			
			 context.write(new Text(k2.toString()+","+map.keySet().size()), NullWritable.get());
		 }
//		public void cleanup(Context context) throws java.io.IOException, java.lang.InterruptedException{
//			 try{
//				 context.write(new Text("avg:"), new Text(sum/i+""));
//			 }catch(ArithmeticException e){
//				 context.write(new Text("no num"), new Text(""));
//			 }
//		 }
		    
	}
	

	public static void run(String INPUT_PATH,String OUTPUT_PATH) throws  Exception {
		Configuration  conf=new Configuration();
		FileSystem  fs=FileSystem.get(new URI(OUTPUT_PATH),conf);
	 
		if(fs.exists(new Path(OUTPUT_PATH)))
				fs.delete(new Path(OUTPUT_PATH));
		
		Job  job=new Job(conf,"myjob");
		
		job.setJarByClass(Step2.class);
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReduce.class);
		//job.setCombinerClass(MySortReduce.class);
		 
	
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class); 
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		FileInputFormat.addInputPath(job,new Path(INPUT_PATH));
//		FileInputFormat.addInputPath(job,new Path(INPUT_PATH1));
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH));
		
		job.waitForCompletion(true);

	}
}
