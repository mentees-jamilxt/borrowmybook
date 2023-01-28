package mentees.jamilxt.borrowmybook.service;

import mentees.jamilxt.borrowmybook.model.domain.Book;
import mentees.jamilxt.borrowmybook.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> fetchAllBooks(){
        return bookRepository.findAll();
    }

    public Book fetchBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> saveBooks(List<Book> books){
        return bookRepository.saveAll(books);
    }

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteById(Long id){
         bookRepository.deleteById(id);
    }
}
