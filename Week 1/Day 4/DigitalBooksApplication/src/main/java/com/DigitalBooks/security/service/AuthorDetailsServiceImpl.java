package com.DigitalBooks.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DigitalBooks.models.Author;
import com.DigitalBooks.models.Book;
import com.DigitalBooks.models.Payment;
import com.DigitalBooks.repository.BookRepository;
import com.DigitalBooks.repository.PaymentRepository;
import com.DigitalBooks.repository.AuthorRepository;



@Service
public class AuthorDetailsServiceImpl implements UserDetailsService {
	@Autowired
	AuthorRepository userRepository;
	
	@Autowired
	private BookRepository authorCreateBookRepository;
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Author user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return AuthorDetailsImpl.build(user);
	}
	
	
	public Book createbookservice(Book authorCreateBook) {
		
		return authorCreateBookRepository.save(authorCreateBook);
		
	}


	public List<Book> getAllBooksService() {
		
		return authorCreateBookRepository.findAll();
	}


	public Book updateBookDetails(Book authorCreateBook) {
		return authorCreateBookRepository.save(authorCreateBook);
				
	}

	public List<Book> findByCatagoryAndAuthorAndPriceAndPublisher(String category, String author, Integer priceInt,
            String publisher) {
        
        return authorCreateBookRepository.findByCatagoryAndAuthorAndPriceAndPublisher( category,  author,  priceInt,
                 publisher);
    }
	
	


//	public Map<String, String> readBookservice(String email, int bookid) {
//		Boolean isuseremail=userRepository.existsByEmail(email);
//		Map<String, String> map=new HashMap<String,String>();
//		if(isuseremail) {
//			Book book=authorCreateBookRepository.FindByBookid(bookid);
//			map.put("catagory", book.getCategory());
//			map.put("author",book.getAuthor());
//			map.put("catagory",book.getCategory());
//			map.put("publisheddate",book.getPublisheddate());
//			map.put("publisher",book.getPublisher());
//			map.put("title",book.getTitle());
//			map.put("price",String.valueOf(book.getPrice()));
//			
//		}
//		return map;
//	}
//


	
	public Payment bookPayment(Payment pay)
    {
		return paymentRepository.save(pay);
    }


	public Book readBook(int bookid) {
		return authorCreateBookRepository.findById(bookid).get();
	}
}
