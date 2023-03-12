package mentees.jamilxt.borrowmybook.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import mentees.jamilxt.borrowmybook.constant.EntityConstant;

@Setter
@Getter
@Entity
@Table(name = EntityConstant.USER)
public class UserEntity extends BaseEntity {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
