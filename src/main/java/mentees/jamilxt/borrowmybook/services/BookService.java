package mentees.jamilxt.borrowmybook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mentees.jamilxt.borrowmybook.entities.Book;
import mentees.jamilxt.borrowmybook.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepo;
	
	//To save a single product
	public Book addBook(Book book) {
		return bookRepo.save(book);
	}
	
	//To get all the books
	public List<Book> getBooks(){
		return bookRepo.findAll();
	}

}