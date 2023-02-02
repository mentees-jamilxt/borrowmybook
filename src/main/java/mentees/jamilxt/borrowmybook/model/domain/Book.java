package mentees.jamilxt.borrowmybook.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookName;
    private String bookAuthor;
    private Double bookPrice;
    private String bookPublisher;
    private String bookEdition;
    private String bookStatus;
    @Column(name = "book_isbn_number")
    private String bookISBNNumber;
}
