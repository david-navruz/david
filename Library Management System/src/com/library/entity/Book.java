package com.library.entity;

import java.util.Date;

public class Book {

	private int id;
	private String callno;
	private String bookName;
	private String author;
	private String publisher;
	private int quantity;
	private int issued;
	private Date addedDate;

	public Book() {

	}

	public Book(int id, String callno, String bookName, String author, String publisher, int quantity, int issued,
			Date addedDate) {
		super();
		this.id = id;
		this.callno = callno;
		this.bookName = bookName;
		this.author = author;
		this.publisher = publisher;
		this.quantity = quantity;
		this.issued = issued;
		this.addedDate = addedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCallno() {
		return callno;
	}

	public void setCallno(String callno) {
		this.callno = callno;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getIssued() {
		return issued;
	}

	public void setIssued(int issued) {
		this.issued = issued;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

}
