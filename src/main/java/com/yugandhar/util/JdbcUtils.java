package com.yugandhar.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcUtils {
	
	 Connection connection;  
	 public Connection getConnection(){  
		   try{  
		   Class.forName("org.postgresql.Driver");     
		   connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres","postgres","pass1234");  
		   }catch(Exception e){  
		   System.out.println(e);  
		   }  
		   return connection;  
		   }  
	   public int save(Integer bookid,String bookname,String author,Integer price){  
		   int result = 0;  
		   try{  
		   connection = getConnection();  
		   PreparedStatement stmt = connection.prepareStatement(  
		   "insert into books(bookid,bookname,author,price) values(?,?,?,?)");  
		   stmt.setInt(1, bookid);  
		   stmt.setString(2, bookname);  
		   stmt.setString(3, author);  
		   stmt.setInt(4, price);  
		   result = stmt.executeUpdate();  
		   connection.close();  
		   }catch(Exception e){  
		   System.out.println(e);  
		   }  
		   
		   return result;
		 
}
	
}
