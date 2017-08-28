package com.mall.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Random;

import com.mall.test.jx.TestEncryption;
import com.mall.tool.Encryption;
import com.mysql.jdbc.Driver;

public class InsertWarehouse {

	public static void main(String[] args) throws SQLException{
		
		insertPic();
	}
	
	public static void insertWareHouse() throws SQLException{
		new Driver();
		Connection connection=DriverManager.getConnection("jdbc:mysql://192.168.152.128:3306/store", "root", "0113");
		PreparedStatement st=connection.prepareStatement("insert into warehouse values (?,?,?)");
		int uid,cid;
		Random rd=new Random();
		for(int i=0;i<100;i++){
			st.setInt(1, rd.nextInt(5)+1);
			st.setInt(2, rd.nextInt(15)+1);
			int a=rd.nextInt(100);
			double r;
			if(a<2){
				r=rd.nextDouble();
			}else if(a<5){
				r=rd.nextDouble()+1;
			}else if(a<17){
				r=rd.nextDouble()+2;
			}else if(a<80){
				r=rd.nextDouble()+3;
			}else {
				r=rd.nextDouble()+4;
			}
			st.setDouble(3, r);
			
			System.out.println(st.execute());
			
		}
		st.close();
		connection.close();
	}
	
	static void insertPic() throws SQLException{
		new Driver();
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "sr107");
		PreparedStatement st=connection.prepareStatement(
				"insert into goods_pics(cid,minipic,pic1,pic2,pic3) values (?,?,?,?,?)");

		DecimalFormat df=new DecimalFormat("0000");
		String path="goodsPic/";
		for(int i=1;i<=15;i++){
			st.setInt(1, i);
			st.setString(2, path+df.format(i)+"_0.jpg");
			st.setString(3, path+df.format(i)+"_1.jpg");
			st.setString(4, path+df.format(i)+"_2.jpg");
			st.setString(5, path+df.format(i)+"_3.jpg");
			st.execute();
			System.out.println(i);
		}
		st.close();
		connection.close();
	}
}
