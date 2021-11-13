package com.craffic.practice.dao;

import com.craffic.practice.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookDao extends MongoRepository<Book, Integer> {
    List<Book> findByAuthorContains(String author);

    Book findByNameEquals(String name);
}
