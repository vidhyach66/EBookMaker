package com.project.Ebookmaker.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.Ebookmaker.Request.RequestClass;
import com.project.Ebookmaker.ResponseBody.ResponseClass;
import com.project.Ebookmaker.entity.Books;
import com.project.Ebookmaker.entity.Users;

@Service
public interface BookService {

	public Books uploadBook(MultipartFile file, RequestClass requestClass) throws Exception;

	public Books getBook(String bookId) throws Exception;

	public Books getBookById(Long id);
	
	
	

}
