package mentees.jamilxt.borrowmybook.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import mentees.jamilxt.borrowmybook.constant.EntityConstant;
import mentees.jamilxt.borrowmybook.model.enums.BookStatus;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = EntityConstant.BOOK)
public class BookEntity extends BaseEntity {
    private String name;
    private String author;
    private BigDecimal price;
    private String publisher;
    private String edition;
    private BookStatus status;
    private String isbnNumber;
}
