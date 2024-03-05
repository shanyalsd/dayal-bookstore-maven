package com.jsrss.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsrss.springboot.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
