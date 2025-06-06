package mentees.jamilxt.borrowmybook.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import mentees.jamilxt.borrowmybook.constant.EntityConstant;
import mentees.jamilxt.borrowmybook.model.enums.BookStatus;

import javax.persistence.*;
import java.math.BigDecimal;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_category_id")
    private BookCategoryEntity bookCategory;
}
