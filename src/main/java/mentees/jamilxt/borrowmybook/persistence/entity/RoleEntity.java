package mentees.jamilxt.borrowmybook.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import mentees.jamilxt.borrowmybook.constant.EntityConstant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = EntityConstant.ROLE)
public class RoleEntity extends BaseEntity{
    private String name;
    
    @Column(length = 1000)
    private String description;
}
