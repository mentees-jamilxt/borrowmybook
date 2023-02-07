package mentees.jamilxt.borrowmybook.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import mentees.jamilxt.borrowmybook.model.enums.BookStatus;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateBookRequest {
    private String name;
    private String author;
    private BigDecimal price;
    private String publisher;
    private String edition;
    private BookStatus status;
    private String isbnNumber;
}
