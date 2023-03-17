package mentees.jamilxt.borrowmybook.persistence.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import mentees.jamilxt.borrowmybook.constant.EntityConstant;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = EntityConstant.USER)
public class UserEntity extends BaseEntity {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean isEnable;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<RoleEntity> roles = new HashSet<>();

}
