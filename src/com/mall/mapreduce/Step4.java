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
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Step4 {
	static int userNum=5;
	static int goodsNum=15;
	static class MyMapper  extends  Mapper<LongWritable, Text, Text, Text>{  

		char matrix;
		public void setup(Context context){
			if(((FileSplit) context.getInputSplit()).getPath().getParent().getName().contains("output2")){
				matrix='A';
//				System.out.println("matrix");
			}else {
				matrix='B';
//				System.out.println("grade");
			}
		}
		 public void map(LongWritable k1, Text v1, Context context) 
						 throws java.io.IOException, java.lang.InterruptedException
		 {
			if(matrix=='A'){
				String[] lines=v1.toString().split(",");
				for(int i=1;i<=userNum;i++){
					context.write(new Text(i+","+lines[0]), new Text("matrix:"+lines[1]+","+lines[2]));
				}
			}else {
				String[] a=v1.toString().split("\\s+");
				String[] b=a[1].split(",");
				System.out.println(b[1]);
				for(int i=1;i<=goodsNum;i++){
					
					context.write(new Text(b[0]+","+i), new Text("grade:"+a[0]+","+b[1]));
				}
			}
		 }
		 
//		 public void cleanup(Context context) throws java.io.IOException, java.lang.InterruptedException{
//			 context.write(new IntWritable(i), new LongWritable(sum));
//		 }
		
	}
	
	static class  MyReduce extends Reducer<Text, Text, Text, NullWritable>{

		public void reduce(Text k2, Iterable<Text> v2, Context context) throws java.io.IOException, java.lang.InterruptedException
		 {
			double sum=0.0;
			int[] a=new int[goodsNum];
			double[] b=new double[goodsNum];
			
			for(Text value:v2){
				if(value.toString().contains("matrix:")){
					String[] lines=value.toString().replaceAll("matrix:", "").split(",");
					a[Integer.parseInt(lines[0])-1]=Integer.parseInt(lines[1]);
//					System.out.println("matrix");
				}else {
					String[] lines=value.toString().replaceAll("grade:", "").split(",");
					b[Integer.parseInt(lines[0])-1]=Double.parseDouble(lines[1]);
//					System.out.println("grade");
				}
			}
			for(int i=0;i<a.length;i++){
//				System.out.println(a[i]+" "+b[i]);
				sum+=a[i]*b[i];
			}
			 System.out.println("reduce......");
			 context.write(new Text(k2.toString()+","+String.format("%.2f", sum)), NullWritable.get());
		 }
//		public void cleanup(Context context) throws java.io.IOException, java.lang.InterruptedException{
//			 try{
//				 context.write(new Text("avg:"), new Text(sum/i+""));
//			 }catch(ArithmeticException e){
//				 context.write(new Text("no num"), new Text(""));
//			 }
//		 }
		    
	}
	

	public static void run(String INPUT_PATH,String INPUT_PATH1,String OUTPUT_PATH) throws  Exception {
		Configuration  conf=new Configuration();
		FileSystem  fs=FileSystem.get(new URI(OUTPUT_PATH),conf);
	 
		if(fs.exists(new Path(OUTPUT_PATH)))
				fs.delete(new Path(OUTPUT_PATH));
		
		Job  job=new Job(conf,"myjob");
		
		job.setJarByClass(Step4.class);
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReduce.class);
//		job.setCombinerClass(MySortReduce.class);
		 
	
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class); 
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		FileInputFormat.addInputPath(job,new Path(INPUT_PATH));
		FileInputFormat.addInputPath(job,new Path(INPUT_PATH1));
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH));
		
		job.waitForCompletion(true);

	}
}
