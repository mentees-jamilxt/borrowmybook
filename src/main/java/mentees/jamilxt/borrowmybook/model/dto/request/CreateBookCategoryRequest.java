package mentees.jamilxt.borrowmybook.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CreateBookCategoryRequest {
    private UUID categoryId;
    private String categoryName;
    private String categoryDesc;
}
