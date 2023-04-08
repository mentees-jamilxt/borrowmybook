package mentees.jamilxt.borrowmybook.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import mentees.jamilxt.borrowmybook.model.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class CreateBookCategoryRequest {
    private UUID categoryId;
    private String categoryName;
    private String categoryDesc;
    private List<Book> books = new ArrayList<>();

}
