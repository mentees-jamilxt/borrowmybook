package mentees.jamilxt.borrowmybook.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
public class CreateBookCategoryRequest {
    private UUID id;
    
    @NotEmpty(message = "Category name is required.")
    @Size(min = 4, max = 50, message = "Category name must be between 4 to 50 characters.")
    private String name;
    
    @NotEmpty(message = "Category description is required.")
    @Size(min = 10, max = 1000, message = "Category description must be between 10 to 1000 characters.")
    private String description;

}
