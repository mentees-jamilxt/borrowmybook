package mentees.jamilxt.borrowmybook.model.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Role {
    private UUID id;
    private String name;
    private String description;
}
