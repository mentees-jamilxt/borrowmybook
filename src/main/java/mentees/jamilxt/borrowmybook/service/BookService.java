package mentees.jamilxt.borrowmybook.service;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.exception.custom.NotFoundException;
import mentees.jamilxt.borrowmybook.mapper.BookMapper;
import mentees.jamilxt.borrowmybook.model.domain.Book;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateBookRequest;
import mentees.jamilxt.borrowmybook.model.dto.request.UpdateBookRequest;
import mentees.jamilxt.borrowmybook.persistence.entity.BookEntity;
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

    public Page<Book> getAll(Pageable pageable) {
        var entities = bookRepository.findAll(pageable);
        return entities.map(bookMapper::entityToDomain);
    }

    public Book getBook(UUID id) {
        var bookEntity = bookRepository.findById(id).orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND));
        return bookMapper.entityToDomain(bookEntity);
    }

    public UUID createOne(CreateBookRequest request) {
        var bookEntity = bookMapper.domainToResponse(request);
        bookEntity.setId(UUID.randomUUID());
        var savedBook = bookRepository.save(bookEntity);
        return savedBook.getId();
    }

    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }
    
    public long countTotalBook() {
        return bookRepository.count();
    }

    public void updateOne(UpdateBookRequest request, UUID id) {
        var bookEntity = bookRepository.findById(id).orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND));
        bookEntity.setName(request.getName());
        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setPrice(request.getPrice());
        bookEntity.setPublisher(request.getPublisher());
        bookEntity.setEdition(request.getEdition());
        bookEntity.setStatus(request.getStatus());
        bookEntity.setIsbnNumber(request.getIsbnNumber());
        bookRepository.save(bookEntity);
    }
}
