package mentees.jamilxt.borrowmybook.persistence.repository;

import mentees.jamilxt.borrowmybook.persistence.entity.BookCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookCategoryRepository extends JpaRepository<BookCategoryEntity, UUID> {
}
