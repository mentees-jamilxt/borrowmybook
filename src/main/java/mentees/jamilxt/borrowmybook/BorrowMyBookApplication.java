package mentees.jamilxt.borrowmybook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import mentees.jamilxt.borrowmybook.mapper.BookMapper;
import mentees.jamilxt.borrowmybook.model.domain.Book;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateBookRequest;
import mentees.jamilxt.borrowmybook.persistence.entity.BookEntity;

@SpringBootApplication
public class BorrowMyBookApplication {
	public static void main(String[] args) {
		SpringApplication.run(BorrowMyBookApplication.class, args);
	}
}
