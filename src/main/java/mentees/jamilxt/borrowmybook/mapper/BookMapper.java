package mentees.jamilxt.borrowmybook.mapper;

import mentees.jamilxt.borrowmybook.model.domain.Book;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateBookRequest;
import mentees.jamilxt.borrowmybook.persistence.entity.BookEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BookMapper {
    Book entityToDomain(BookEntity bookEntity);

    BookEntity domainToResponse(CreateBookRequest request);
}