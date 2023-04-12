package mentees.jamilxt.borrowmybook.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import mentees.jamilxt.borrowmybook.constant.EntityConstant;
import mentees.jamilxt.borrowmybook.model.domain.Book;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = EntityConstant.BOOK_CATEGORY)
public class BookCategoryEntity extends BaseEntity{
    @Column(length = 100)
    private String name;

    @Column(length = 1000)
    private String description;

    @OneToMany(targetEntity = BookEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId",referencedColumnName = "id")
    private List<Book> books ;
}
