package com.DigitalBooks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DigitalBooks.models.Author;
import com.DigitalBooks.models.Book;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	Optional<Author> findByUsername(String username);
	
	

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
