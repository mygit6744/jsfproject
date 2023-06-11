package com.yugandhar.managedbeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.mappers.Mapper;
import com.yugandhar.dao.DatabaseOperations;
import com.yugandhar.dao.UserDAO;
import com.yugandhar.dto.Book;
import com.yugandhar.dto.SortableList;
import com.yugandhar.entity.Books;
import com.yugandhar.util.JdbcUtils;

@ManagedBean(name = "bookTable")
@RequestScoped
public class DataTable extends SortableList {

	public List<Book> bookList = new ArrayList<>();
	
	private Integer bookid;
	private String bookname;
	private String author;
	private Integer price;
	
	
	public Integer getBookid() {
		return bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}



	@ManagedProperty("#{userDAO}")
	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public String editBook() {
		System.out.println("in edit");
		FacesContext context = FacesContext.getCurrentInstance();
	    Map<String,String> requestParams = context.getExternalContext().getRequestParameterMap();
	    String bookidd = (String) requestParams.get("bookid");
	    bookid = Integer.valueOf(bookidd);
	    setBookid(bookid);
		return "addBook";   
	   }
	
	public String submit2(){  
		Book book = new Book(bookid, bookname, author, price);
		Books books = Mapper.mapTo(book);
		DatabaseOperations.createNewBook(books);
		return "booksList?faces-redirect=true";  
	}
	public String submit(){  
		 JdbcUtils jdbcUtil = new JdbcUtils();
		 int result = jdbcUtil.save(bookid,bookname,author,price);
		  if(result != 0)  
			    return "booksList?faces-redirect=true";     
			   else return "addBook?faces-redirect=true";   
			   }  
	public List<Book> getBooksList() {
		System.out.println("bookList size before:" + this.bookList.isEmpty());
		 if (!oldSort.equals(sortColumnName) ||
	                oldAscending != ascending){
			
	             sort();
	             oldSort = sortColumnName;
	             oldAscending = ascending;
	        }
		 System.out.println("oldAscending: " + oldAscending);
		 System.out.println("oldSort: " + oldSort);
		 System.out.println("ascending: " + ascending);
		 if(this.bookList.isEmpty()) {
			 List<Books> booksList = DatabaseOperations.getAllBooks();
				bookList = Mapper.map(booksList);
				 System.out.println("bookList size inside:" + this.bookList.isEmpty());
		 }
		 System.out.println("bookList size after:" + this.bookList.isEmpty());
		return bookList;
	}
	
	 // dataTableColumn Names
    private static final String bookidColumnName = "bookid";
    private static final String booknameColumnName = "bookname";
    private static final String authorColumnName = "author";
    private static final String priceColumnName = "price";

    public DataTable() {
        super("bookid");
    }

	public  String getBookidColumnname() {
		return bookidColumnName;
	}

	public  String getBooknameColumnname() {
		return booknameColumnName;
	}

	public  String getAuthorColumnname() {
		return authorColumnName;
	}

	public  String getPriceColumnname() {
		return priceColumnName;
	}

	@Override
	protected void sort() {
		// TODO Auto-generated method stub
		
		if(this.bookList.isEmpty()) {
			List<Books> booksList = DatabaseOperations.getAllBooks();
			this.bookList = Mapper.map(booksList);
		}
		 if (sortColumnName != null) {
			 
			 if (sortColumnName.equals(bookidColumnName)) {
				 System.out.println("sortColumnName:" + sortColumnName);
				 System.out.println("bookidColumnName: " + ascending);
				 
				Collections.sort(bookList, (p1,p2) -> {
					 return ascending ?
							 p1.getBookid().compareTo(p2.getBookid()) :
							 p2.getBookid().compareTo(p1.getBookid());
				});
			 }
			 if (sortColumnName.equals(booknameColumnName)) {
				 System.out.println("sortColumnName:" + sortColumnName);
				 System.out.println("booknameColumnName: " + ascending);
					Collections.sort(bookList, (p1,p2) -> {
						 return ascending ?
								 p1.getBookname().toLowerCase().compareTo(p2.getBookname().toLowerCase()) :
								 p2.getBookname().toLowerCase().compareTo(p1.getBookname().toLowerCase());
					});
				 }
			 if (sortColumnName.equals(authorColumnName)) {
				 System.out.println("sortColumnName:" + sortColumnName);
				 System.out.println("authorColumnName: " + ascending);
					Collections.sort(bookList, (p1,p2) -> {
						 return ascending ?
								 p1.getAuthor().toLowerCase().compareTo(p2.getAuthor().toLowerCase()) :
								 p2.getAuthor().toLowerCase().compareTo(p1.getAuthor().toLowerCase());
					});
				 }
			 if (sortColumnName.equals(priceColumnName)) {
				 System.out.println("sortColumnName:" + sortColumnName);
				 System.out.println("priceColumnName:" + ascending);
					Collections.sort(bookList, (p1,p2) -> {
						 return ascending ?
								 p1.getPrice().compareTo(p2.getPrice()) :
								 p2.getPrice().compareTo(p1.getPrice());
					});
				 }
         }
		 
		
	}

	@Override
	protected boolean isDefaultAscending(String sortColumn) {
        return true;
    }
	
    
}