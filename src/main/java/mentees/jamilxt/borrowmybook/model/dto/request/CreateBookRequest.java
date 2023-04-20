package mentees.jamilxt.borrowmybook.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import mentees.jamilxt.borrowmybook.model.enums.BookStatus;
import mentees.jamilxt.borrowmybook.persistence.entity.BookCategoryEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CreateBookRequest {
    private UUID id;
    @NotEmpty(message = "Please enter a book name.")
    private String name;
    @NotEmpty(message = "Please enter Author name.")
    private String author;
    @NotNull(message = "Please enter Book price.")
    private BigDecimal price;
    @NotEmpty(message = "Publisher name required.")
    private String publisher;
    @NotEmpty(message = "Book Edition must be need to provide.")
    private String edition;
    @NotNull(message = "Select Book Status.")
    private BookStatus status;
    @NotEmpty(message = "Please enter Book ISBN Number.")
    private String isbnNumber;
    @NotNull(message = "Select Book Category.")
    private BookCategoryEntity bookCategory;
}
