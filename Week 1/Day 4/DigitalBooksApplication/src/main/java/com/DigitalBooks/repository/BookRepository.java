package com.DigitalBooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DigitalBooks.models.Book;
import com.DigitalBooks.models.Payment;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	 List<Book> findByCatagoryAndAuthorAndPriceAndPublisher(String category, String author, Integer priceInt, String publisher);

	// Book FindByBookid(int bookid);

}
