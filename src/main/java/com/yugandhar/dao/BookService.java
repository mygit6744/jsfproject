package com.yugandhar.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yugandhar.dto.Product;
import com.yugandhar.entity.Books;

@Service
public class BookService {
	
	public List<Product> productsList;
	
	public List<Books> booksList;
	
	@Autowired
	private BookDAO bookDAO;

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
		return bookDAO.findAll();
	}
}
