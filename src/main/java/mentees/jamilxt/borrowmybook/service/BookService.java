package mentees.jamilxt.borrowmybook.service;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.exception.custom.NotFoundException;
import mentees.jamilxt.borrowmybook.mapper.BookMapper;
import mentees.jamilxt.borrowmybook.model.domain.Book;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateBookRequest;
import mentees.jamilxt.borrowmybook.persistence.repository.BookRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookService {
    public static final String BOOK_NOT_FOUND = "Book not found";
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookMapper::toDomain);
    }

    public Book getBook(UUID id) {
        var bookEntity = bookRepository.findById(id).orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND));
        return bookMapper.toDomain(bookEntity);
    }

    public void createBook(CreateBookRequest request) {
        var bookEntity = bookMapper.toEntity(request);
        bookRepository.save(bookEntity);
    }

    public void updateBook(CreateBookRequest request) {
        var bookEntity = bookRepository.findById(request.getId()).orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND));
        bookEntity.setName(request.getName());
        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setPrice(request.getPrice());
        bookEntity.setPublisher(request.getPublisher());
        bookEntity.setEdition(request.getEdition());
        bookEntity.setStatus(request.getStatus());
        bookEntity.setIsbnNumber(request.getIsbnNumber());
        bookRepository.save(bookEntity);
    }

    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }
}
