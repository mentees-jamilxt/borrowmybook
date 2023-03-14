package mentees.jamilxt.borrowmybook.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateUserRequest {
	private UUID id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean isEnable;
}
