package com.project.Ebookmaker.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import org.springframework.core.serializer.Deserializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;



@Entity
@Table(name = "BOOKS")
public class Books implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long BookId;
	@Column(name="book_title")
	private String bookTitle;
	@Column(name="book_description")
	private String bookDescription;
	@Column(name="file_name")
	private String fileName;
	@Column(name="file_type")
	private String fileType;
	@Column(name="status")
	private String status;
	
	/*@Lob
	@Column(name="data")
	
	 @JsonIgnore
	private byte[] Data;*/
	
	 @Lob
	   private byte[] Data;

	   @JsonIgnore
	   public byte[] Data() {
	       return Data;
	   }
	
	@JsonIgnoreProperties("books")
	@ManyToOne(fetch=FetchType.EAGER,targetEntity = Users.class)
	private Users author;
	
	public Books(){
		super();
		// TODO Auto-generated constructor stub
	}

	public Books(Long bookId, String bookTitle, String bookDescription, String fileName, String fileType, String status,
			byte[] data, Users author) {
		super();
		BookId = bookId;
		this.bookTitle = bookTitle;
		this.bookDescription = bookDescription;
		this.fileName = fileName;
		this.fileType = fileType;
		this.status = status;
		Data = data;
		this.author = author;
	}

	public Long getBookId() {
		return BookId;
	}

	public void setBookId(Long bookId) {
		BookId = bookId;
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

	public byte[] getData() {
		return Data;
	}

	public void setData(byte[] data) {
		Data = data;
	}

	public Users getAuthor() {
		return author;
	}

	public void setAuthor(Users author) {
		this.author = author;
	}


	

}
