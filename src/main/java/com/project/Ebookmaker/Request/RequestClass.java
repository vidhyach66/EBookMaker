package com.project.Ebookmaker.Request;

import javax.persistence.Column;
import javax.persistence.Lob;

public class RequestClass {
	
	private String bookTitle;
	private String bookDescription;
	private String fileName;
	private String fileType;
	private String status;
	private Long authorId;
	public RequestClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RequestClass(String bookTitle, String bookDescription, String fileName, String fileType, String status,
			Long authorId) {
		super();
		this.bookTitle = bookTitle;
		this.bookDescription = bookDescription;
		this.fileName = fileName;
		this.fileType = fileType;
		this.status = status;
		this.authorId = authorId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookDescription() {
		return bookDescription;
	}
	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	
	

}
