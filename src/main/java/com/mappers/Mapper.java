package com.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.yugandhar.dto.Book;
import com.yugandhar.entity.Books;

public class Mapper {

  
    public static List<Book> map(List<Books> books) {

        List<Book> userDto = books.stream().
                map(p -> new Book(p.getBookid(),p.getBookname(),p.getAuthor(),p.getPrice()))
                .collect(Collectors.toList());

        return userDto;

    }
    
    public static Books mapTo(Book p) {

    	Books books = new Books(p.getBookid(),p.getBookname(),p.getAuthor(),p.getPrice());

        return books;

    }
}