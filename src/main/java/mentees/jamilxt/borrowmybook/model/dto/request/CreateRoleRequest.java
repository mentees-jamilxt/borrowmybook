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
    @Size(min = 4, max = 15, message = "Role name must be between 4 to 15 characters.")
    private String name;
    
    @NotEmpty(message = "Role description is required")
    @Size(message = "Role description must be between 10 to 1000 characters.")
    private String description;

}
