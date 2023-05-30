package mentees.jamilxt.borrowmybook.service;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.exception.custom.NotFoundException;
import mentees.jamilxt.borrowmybook.mapper.BookCategoryMapper;
import mentees.jamilxt.borrowmybook.model.domain.BookCategory;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateBookCategoryRequest;
import mentees.jamilxt.borrowmybook.model.dto.request.UpdateBookCategoryRequest;
import mentees.jamilxt.borrowmybook.persistence.repository.BookCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookCategoryService {

    public static final String BOOK_CATEGORY_NOT_FOUND = "Book category not found";
    private final BookCategoryMapper bookCategoryMapper;
    private final BookCategoryRepository bookCategoryRepository;

    public Page<BookCategory> getAll(Pageable pageable){
        return bookCategoryRepository.findAll(pageable).map(bookCategoryMapper::toDomain);
    }

    public BookCategory getOne(UUID id){
        var bookCategoryEntity = bookCategoryRepository.findById(id).orElseThrow(() -> new NotFoundException(BOOK_CATEGORY_NOT_FOUND));
        return bookCategoryMapper.toDomain(bookCategoryEntity);
    }

    public UUID createOne(CreateBookCategoryRequest request){
        var bookCategoryEntity = bookCategoryMapper.toEntity(request);
        bookCategoryEntity.setId(UUID.randomUUID());
        var savedCategory = bookCategoryRepository.save(bookCategoryEntity);
        return savedCategory.getId();
    }

    public void updateBookCategory(UpdateBookCategoryRequest request, UUID id){
        var bookCategoryEntity = bookCategoryRepository.findById(id).orElseThrow(() -> new NotFoundException(BOOK_CATEGORY_NOT_FOUND));
        bookCategoryEntity.setName(request.getName());
        bookCategoryEntity.setDescription(request.getDescription());
        bookCategoryRepository.save(bookCategoryEntity);
    }

    public void deleteBook(UUID id){
        bookCategoryRepository.deleteById(id);
    }
}
