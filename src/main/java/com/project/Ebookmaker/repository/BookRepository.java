package com.project.Ebookmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Ebookmaker.entity.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Long>{

}
