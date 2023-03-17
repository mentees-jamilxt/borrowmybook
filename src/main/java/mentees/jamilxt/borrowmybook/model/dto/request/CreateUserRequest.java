package mentees.jamilxt.borrowmybook.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import mentees.jamilxt.borrowmybook.persistence.entity.RoleEntity;

import java.util.HashSet;
import java.util.Set;
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
	private Set<RoleEntity> roles = new HashSet<>();
}
