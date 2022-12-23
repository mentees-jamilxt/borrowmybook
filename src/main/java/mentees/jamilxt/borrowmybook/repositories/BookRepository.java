package mentees.jamilxt.borrowmybook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mentees.jamilxt.borrowmybook.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


}