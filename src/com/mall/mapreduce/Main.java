package com.mall.mapreduce;

public class Main {

	public static void main(String[] args) throws Exception{
		
		String INPUT_PATH="hdfs://master:9000/store/input/warehouse";
		String OUTPUT_PATH1="hdfs://master:9000/store/output1/";
		String OUTPUT_PATH2="hdfs://master:9000/store/output2/";
		String OUTPUT_PATH3="hdfs://master:9000/store/output3/";
		String OUTPUT_PATH4="hdfs://master:9000/store/output4/";
		
		HdfsOperater.deletedir(INPUT_PATH);
		
		SqoopUtil.import_data(INPUT_PATH);
		Step1.run(INPUT_PATH,OUTPUT_PATH1);
		Step2.run(OUTPUT_PATH1,OUTPUT_PATH2);
		Step3.run(OUTPUT_PATH1,OUTPUT_PATH3);
		Step4.run(OUTPUT_PATH2,OUTPUT_PATH3,OUTPUT_PATH4);
		
		System.out.println("output successful");
		SqoopUtil.export_data(OUTPUT_PATH4,"user_hobby");
		System.out.println("export user_hobby successful");
		
		SqoopUtil.export_data(OUTPUT_PATH2,"goods_hobby");
		System.out.println("export goods_hobby successful");
	}
}
