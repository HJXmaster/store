package com.mall.mapreduce;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Step3 {
	static class MyMapper  extends  Mapper<LongWritable, Text, Text, Text>{  

		 public void map(LongWritable k1, Text v1, Context context) 
						 throws java.io.IOException, java.lang.InterruptedException
		 {
			String[] lines=v1.toString().split("\\s+");
			String[] cid=lines[1].split(";");
			HashMap<String,ArrayList<String>> hm=new HashMap<String,ArrayList<String>>();
			for(int i=0;i<cid.length;i++){
				String[] a=cid[i].split(",");
				if(hm.containsKey(a[0])){
					hm.get(a[0]).add(a[1]);
				}else {
					ArrayList<String> val=new ArrayList<String>();
					val.add(a[1]);
					hm.put(a[0], val);
				}
			}
			
			for(Map.Entry<String,ArrayList<String>> en:hm.entrySet()){
				String key=en.getKey();
				ArrayList<String> al=en.getValue();
				double sum=0;
				for(String str:al){
					sum+=Double.parseDouble(str);
				}
				try{
					
					context.write(new Text(key), new Text(lines[0]+","+String.format("%.2f", sum/al.size())));
				}catch(Exception e){
					
				}
			}
		 }
		 
//		 public void cleanup(Context context) throws java.io.IOException, java.lang.InterruptedException{
//			 context.write(new IntWritable(i), new LongWritable(sum));
//		 }
		
	}
	
//	static class  MyReduce extends Reducer<Text, Text, Text, Text>{
//
//		public void reduce(Text k2, Iterable<Text> v2, Context context) throws java.io.IOException, java.lang.InterruptedException
//		 {
//			int sum=0;
//			
//			for(Text value:v2){
//				
//			}
//			 System.out.println("reduce......");
//			 context.write(k2, new IntWritable(sum));
//		 }
////		public void cleanup(Context context) throws java.io.IOException, java.lang.InterruptedException{
////			 try{
////				 context.write(new Text("avg:"), new Text(sum/i+""));
////			 }catch(ArithmeticException e){
////				 context.write(new Text("no num"), new Text(""));
////			 }
////		 }
//		    
//	}
	

	public static void run(String INPUT_PATH,String OUTPUT_PATH) throws  Exception {
		Configuration  conf=new Configuration();
		FileSystem  fs=FileSystem.get(new URI(OUTPUT_PATH),conf);
	 
		if(fs.exists(new Path(OUTPUT_PATH)))
				fs.delete(new Path(OUTPUT_PATH));
		
		Job  job=new Job(conf,"myjob");
		
		job.setJarByClass(Step3.class);
		job.setMapperClass(MyMapper.class);
//		job.setReducerClass(MyReduce.class);
		//job.setCombinerClass(MySortReduce.class);
		 
	
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class); 
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job,new Path(INPUT_PATH));
//		FileInputFormat.addInputPath(job,new Path(INPUT_PATH1));
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH));
		
		job.waitForCompletion(true);

	}
}
