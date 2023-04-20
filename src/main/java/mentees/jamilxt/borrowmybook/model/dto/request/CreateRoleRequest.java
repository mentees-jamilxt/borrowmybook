package mentees.jamilxt.borrowmybook.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateRoleRequest {
    private UUID id;
    
    @NotEmpty(message = "Role name is required.")
    @Size(min = 4, max = 15, message = "Field must be between 4 to 15 characters.")
    private String name;
    
    @NotEmpty(message = "Please enter a description.")
    @Size(min = 10, max = 1000, message = "Description must be between 10 to 1000 characters.")
    private String description;
}
