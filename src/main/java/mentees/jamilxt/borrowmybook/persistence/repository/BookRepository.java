package mentees.jamilxt.borrowmybook.persistence.repository;

import mentees.jamilxt.borrowmybook.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
