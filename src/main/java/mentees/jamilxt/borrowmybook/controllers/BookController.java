package mentees.jamilxt.borrowmybook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mentees.jamilxt.borrowmybook.entities.Book;
import mentees.jamilxt.borrowmybook.services.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping("/addBook")
	public Book addBook(Book book) {
		return bookService.addBook(book);
	}
		
	@GetMapping("/books")
	public List<Book> getBooks(){
		return bookService.getBooks();
	}
	
}
