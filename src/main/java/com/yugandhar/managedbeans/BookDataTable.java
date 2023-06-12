package com.yugandhar.managedbeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.mappers.Mapper;
import com.yugandhar.dao.BookService;
import com.yugandhar.dto.Book;
import com.yugandhar.dto.SortableList;
import com.yugandhar.entity.Books;

@ManagedBean(name = "bookTable")
@SessionScoped
public class BookDataTable extends SortableList {

	public List<Book> bookList = new ArrayList<>();

	@ManagedProperty(value = "#{bookService}")
	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public List<Book> getBooksList() {
		System.out.println("bookList size before:" + this.bookList.isEmpty());
		if (!oldSort.equals(sortColumnName) || oldAscending != ascending) {

			sort();
			oldSort = sortColumnName;
			oldAscending = ascending;
		}
		if (this.bookList.isEmpty()) {
			List<Books> booksList = bookService.getBooksList();
			bookList = Mapper.map(booksList);
		}
		return bookList;
	}
	
	public String editBook() {
	
		return "addBook";
	}

	// dataTableColumn Names
	private static final String defaultSortColumn = "Book ID";
	private static final String bookidColumnName = "Book ID";
	private static final String booknameColumnName = "Book Name";
	private static final String authorColumnName = "Author";
	private static final String priceColumnName = "Price";

	public BookDataTable() {
		super(defaultSortColumn);
	}

	public String getBookidColumnname() {
		return bookidColumnName;
	}

	public String getBooknameColumnname() {
		return booknameColumnName;
	}

	public String getAuthorColumnname() {
		return authorColumnName;
	}

	public String getPriceColumnname() {
		return priceColumnName;
	}

	@Override
	protected void sort() {
		// TODO Auto-generated method stub

		if (this.bookList.isEmpty()) {
			List<Books> booksList = bookService.getBooksList();
			this.bookList = Mapper.map(booksList);
		}
		if (sortColumnName != null) {

			if (sortColumnName.equals(bookidColumnName)) {
				System.out.println("ascending: " + ascending);

				Collections.sort(bookList, (p1, p2) -> {
					return ascending ? p1.getBookid().compareTo(p2.getBookid())
							: p2.getBookid().compareTo(p1.getBookid());
				});
			}
			if (sortColumnName.equals(booknameColumnName)) {
				System.out.println("ascending: " + ascending);
				Collections.sort(bookList, (p1, p2) -> {
					return ascending ? p1.getBookname().toLowerCase().compareTo(p2.getBookname().toLowerCase())
							: p2.getBookname().toLowerCase().compareTo(p1.getBookname().toLowerCase());
				});
			}
			if (sortColumnName.equals(authorColumnName)) {
				System.out.println("ascending: " + ascending);
				Collections.sort(bookList, (p1, p2) -> {
					return ascending ? p1.getAuthor().toLowerCase().compareTo(p2.getAuthor().toLowerCase())
							: p2.getAuthor().toLowerCase().compareTo(p1.getAuthor().toLowerCase());
				});
			}
			if (sortColumnName.equals(priceColumnName)) {
				System.out.println("ascending:" + ascending);
				Collections.sort(bookList, (p1, p2) -> {
					return ascending ? p1.getPrice().compareTo(p2.getPrice()) : p2.getPrice().compareTo(p1.getPrice());
				});
			}
		}

	}

	@Override
	protected boolean isDefaultAscending(String sortColumn) {
		return true;
	}

	
}