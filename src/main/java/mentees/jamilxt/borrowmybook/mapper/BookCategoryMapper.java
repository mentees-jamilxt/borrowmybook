package mentees.jamilxt.borrowmybook.mapper;

import mentees.jamilxt.borrowmybook.model.domain.BookCategory;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateBookCategoryRequest;
import mentees.jamilxt.borrowmybook.persistence.entity.BookCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookCategoryMapper {
    BookCategory toDomain(BookCategoryEntity bookCategoryEntity);

    BookCategoryEntity toEntity(CreateBookCategoryRequest request);
}
