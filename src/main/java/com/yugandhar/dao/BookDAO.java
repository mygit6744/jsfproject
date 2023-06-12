package com.yugandhar.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yugandhar.entity.Books;

@Repository
public class BookDAO {
    public List<Books> findAll() {
		return DatabaseOperations.getAllBooks();
    	
    }
}
