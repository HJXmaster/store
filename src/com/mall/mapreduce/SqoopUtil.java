package com.mall.mapreduce;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.sqoop.Sqoop;
import org.apache.sqoop.tool.ExportTool;
import org.apache.sqoop.tool.ImportTool;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class SqoopUtil {

	static{
		Connection connect=null;
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.152.128:3306/store","root","0113");
			 Statement stmt = (Statement) connect.createStatement();
			 stmt.execute("truncate table user_hobby");
			 stmt.execute("truncate table goods_hobby");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(connect!=null){
			 try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		 
	}
	public static void import_data(String input){
		Configuration conf = new Configuration();
		conf.set("mapreduce.framework.name", "yarn");  
        conf.set("yarn.resourcemanager.address", "192.168.152.128:8032"); 
        conf.set("mapreduce.app-submission.cross-platform", "true");
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl",org.apache.hadoop.fs.LocalFileSystem.class.getName());
		ArrayList<String> list = new ArrayList<String>(); 
		
		list.add("--connect");
		list.add("jdbc:mysql://192.168.152.128:3306/store");
		list.add("--table");
		list.add("warehouse");
		list.add("--username");
		list.add("root"); 
		list.add("--password");
		list.add("0113"); 
		list.add("--target-dir");
		list.add(input);
		list.add("-m");
		list.add("1");
		
		ImportTool importer= new ImportTool();
		Sqoop sqoop = new Sqoop(importer);
		sqoop.setConf(conf);
		String[] arg = new String[1];
		arg = list.toArray(new String[0]);
		int result = Sqoop.runSqoop(sqoop, arg);
		
	}
	public static void export_data(String output,String table){
		Configuration conf = new Configuration();
		conf.set("mapreduce.framework.name", "yarn");  
        conf.set("yarn.resourcemanager.address", "192.168.152.128:8032");  
        conf.set("mapreduce.app-submission.cross-platform", "true");
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl",org.apache.hadoop.fs.LocalFileSystem.class.getName());
		ArrayList<String> list = new ArrayList<String>(); 
		
		list.add("--connect");
		list.add("jdbc:mysql://192.168.152.128:3306/store");
		list.add("--table");
		list.add(table);
		list.add("--username");
		list.add("root"); 
		list.add("--password");
		list.add("0113"); ;
		list.add("--fields-terminated-by");
		list.add(",");
		list.add("--export-dir");
		list.add(output);
		
		ExportTool exporter = new ExportTool();
		Sqoop sqoop = new Sqoop(exporter);
		sqoop.setConf(conf);
		String[] arg = new String[1];
		arg = list.toArray(new String[0]);
		int result = Sqoop.runSqoop(sqoop, arg);
		
	}
}
