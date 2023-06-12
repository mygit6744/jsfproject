package com.yugandhar.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.yugandhar.util.JdbcUtils;

@ManagedBean(name = "addBook")
@RequestScoped
public class AddBookMBean {
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
	public String submit() {
		JdbcUtils jdbcUtil = new JdbcUtils();
		int result = jdbcUtil.save(bookid, bookname, author, price);
		if (result != 0)
			return "booksList?faces-redirect=true";
		else
			return "addBook?faces-redirect=true";
	}
	
	/*
	 * public String editBook() { System.out.println("in edit"); FacesContext
	 * context = FacesContext.getCurrentInstance(); Map<String, String>
	 * requestParams = context.getExternalContext().getRequestParameterMap(); String
	 * bookidd = (String) requestParams.get("bookid"); bookid =
	 * Integer.valueOf(bookidd); setBookid(bookid); return "addBook"; }
	 */

}
