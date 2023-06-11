package com.yugandhar.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.yugandhar.dto.Product;
import com.yugandhar.entity.Books;
import com.yugandhar.util.JdbcUtils;

@ManagedBean (name="userDAO")
public class UserDAO {
	
	public List<Product> productsList;
	
	public List<Books> booksList;

	public List<Product> getProductList() {
		productsList = new ArrayList<Product>();
		productsList.add(new Product(1, "HP Laptop", 25000f));
		productsList.add(new Product(2, "Dell Laptop", 30000f));
		productsList.add(new Product(3, "Lenevo Laptop", 28000f));
		productsList.add(new Product(4, "Sony Laptop", 28000f));
		productsList.add(new Product(5, "Apple Laptop", 90000f));
		return productsList;
	}

	
	public List<Books> getBooksList() {
		JdbcUtils jdbcUtil = new JdbcUtils();
		try {
			booksList = jdbcUtil.getBooks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return booksList;
	}
}
