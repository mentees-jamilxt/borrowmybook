package mentees.jamilxt.borrowmybook.model.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import mentees.jamilxt.borrowmybook.persistence.entity.RoleEntity;

@Getter
@Setter
public class User {
	private UUID id;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean isEnabled;
	private Boolean isVerified;
	private String password;
	private Set<RoleEntity> roles = new HashSet<>();

}
