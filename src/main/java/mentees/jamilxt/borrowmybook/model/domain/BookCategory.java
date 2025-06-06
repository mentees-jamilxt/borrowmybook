package mentees.jamilxt.borrowmybook.model.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class BookCategory {
    private UUID id;
    private String name;
    private String description;
}
