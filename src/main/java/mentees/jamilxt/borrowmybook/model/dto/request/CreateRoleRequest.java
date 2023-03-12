package mentees.jamilxt.borrowmybook.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import mentees.jamilxt.borrowmybook.model.enums.BookStatus;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateRoleRequest {
    private String name;
    private String description;
}
