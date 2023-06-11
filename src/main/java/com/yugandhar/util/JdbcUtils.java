package com.yugandhar.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yugandhar.entity.Books;

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
	   
	   public List<Books> getBooks() throws SQLException{  
		   int result = 0;  
		   Statement tsmt = null;
		   List<Books> list = new ArrayList<>();
		   try{  
		   connection = getConnection();  
		   tsmt = connection.createStatement();
		   ResultSet rs = tsmt.executeQuery("Select * from Books");
		   while (rs.next()) {
			   Books books = new Books();
			   books.setBookid(rs.getInt("bookid"));
			   books.setBookname(rs.getString("bookname"));
			   books.setAuthor(rs.getString("author"));
			   books.setPrice(rs.getInt("price"));
		        list.add(books);
		       
		      }
		   connection.close();  
		   }catch(Exception e){  
		   System.out.println(e);  
		   }  
		   finally {
			   tsmt.close();
		   }
		   
		   return list;
		 
}

}
