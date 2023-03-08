package mentees.jamilxt.borrowmybook.persistence.repository;

import mentees.jamilxt.borrowmybook.persistence.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {
}
