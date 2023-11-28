package com.project.Ebookmaker.service;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.project.Ebookmaker.Request.RequestClass;

import com.project.Ebookmaker.entity.Books;
import com.project.Ebookmaker.entity.Users;
import com.project.Ebookmaker.repository.BookRepository;
import com.project.Ebookmaker.repository.UserRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository bookRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public Books uploadBook(MultipartFile file, RequestClass requestClass)throws Exception {
		String FileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(FileName.contains("..")){
				throw new Exception("FileName contains invalid Path sequence"+FileName);
			}
			Books book = new Books();
			Users user = userRepository.findById(requestClass.getAuthorId()).get();
			book.setBookTitle(requestClass.getBookTitle());
			book.setBookDescription(requestClass.getBookDescription());
			book.setFileName(FileName);
			book.setAuthor(user);
			book.setFileType(file.getContentType());
			book.setStatus(requestClass.getStatus());
			book.setData(file.getBytes());
			bookRepository.save(book);
			return book;
		}
		catch(Exception e) {
			throw new Exception("Could not save file:"+FileName);
		}
	}

	@Override
	public Books getBook(String bookId) throws Exception {
		try {
			return bookRepository
					.findById(Long.parseLong(bookId)).get();
		}
		catch(Exception e) {
			throw new Exception("File not found"+bookId);
		}
		
				
	}

	@Override
	@Transactional
	public Books getBookById(Long id) {
		Books book=bookRepository.findById(id).orElseThrow(()->new RuntimeException("book not found"));
		return book;
	}
   
    


	
	
    
}
