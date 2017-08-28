package com.mall.mapreduce;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsOperater {

	public static void deletedir(String filename) throws IOException, URISyntaxException {
		Configuration conf = new Configuration();
		FileSystem hdfs = FileSystem.get(new URI(filename),conf);
		Path f = new Path(filename);
		if (hdfs.exists(f)) {
			hdfs.delete(f, true);
		}
	}
}
