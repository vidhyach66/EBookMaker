package com.project.Ebookmaker.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.BindException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Ebookmaker.Request.RequestClass;
import com.project.Ebookmaker.ResponseBody.ResponseClass;
import com.project.Ebookmaker.entity.Books;
import com.project.Ebookmaker.entity.Users;
import com.project.Ebookmaker.repository.BookRepository;
import com.project.Ebookmaker.service.BookService;
import com.project.Ebookmaker.exception.*;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	BookService bookservice;
	@Autowired
	BookRepository bookRepository;
	
	
	
	@PostMapping(value="/uploadBook",consumes= {MediaType.APPLICATION_JSON_VALUE,
			                                    MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasRole('ROLE_AUTHOR')")
	public ResponseClass uploadBook(@RequestPart("Books") String Books,@RequestPart("file") MultipartFile file) throws Exception {
		Books book = new Books();
		String downloadURl = "";
		RequestClass requestClass = new RequestClass();
		ObjectMapper objectMapper = new ObjectMapper();
		requestClass=objectMapper.readValue(Books,RequestClass.class);
		book = bookservice.uploadBook(file,requestClass);
		downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/books/download/")
				.path(book.getBookId().toString())
				.toUriString();
		ResponseClass response = new ResponseClass();
		response.setFileName(book.getFileName());
		response.setFileSize(file.getSize());
		response.setDownloadURL(downloadURl);
		response.setFileType(file.getContentType());
		return response;
		
	}
	
	@GetMapping("/download/{BookId}")
	@PreAuthorize("hasRole('ROLE_AUTHOR')or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Resource> downloadFile(@PathVariable String BookId) throws Exception{
		Books book = new Books();
		book = bookservice.getBook(BookId);
		return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(book.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "book; filename=\"" + book.getFileName()
                + "\"")
                .body(new ByteArrayResource(book.getData()));
		
	}
	@GetMapping("/book/{id}")
	//@PreAuthorize("hasRole('ROLE_AUTHOR')")
	public ResponseEntity<Books> getBook(@PathVariable("id") Long id) {
		Books book = bookservice.getBookById(id);
		return ResponseEntity.ok(book);
	}
	
	@PutMapping("/book/{id}")
	public ResponseEntity<Books> updateBook(@PathVariable Long id, @RequestBody Books bookDetails) throws BindException{
		Books book = bookRepository.findById(id)
				.orElseThrow(() -> new BindException("Book not exist with id :" + id));
		
		book.setBookTitle(bookDetails.getBookTitle());
		book.setBookDescription(bookDetails.getBookDescription());
		book.setFileName(bookDetails.getFileName());
		book.setData(bookDetails.getData());
		book.setFileName(bookDetails.getFileName());
		book.setFileType(bookDetails.getFileType());
		book.setStatus(bookDetails.getStatus());
		
		Books updatedBook = bookRepository.save(book);
		return ResponseEntity.ok(updatedBook);
	}
	
	
	

}
