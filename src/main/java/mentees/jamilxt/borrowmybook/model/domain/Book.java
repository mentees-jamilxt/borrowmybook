package mentees.jamilxt.borrowmybook.model.domain;

import lombok.Getter;
import lombok.Setter;
import mentees.jamilxt.borrowmybook.model.enums.BookStatus;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class Book {
    private UUID id;
    private String name;
    private String author;
    private BigDecimal price;
    private String publisher;
    private String edition;
    private BookStatus status;
    private String isbnNumber;
    private BookCategory bookCategory;
}
