package mentees.jamilxt.borrowmybook.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import mentees.jamilxt.borrowmybook.constant.EntityConstant;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = EntityConstant.ROLE)
public class RoleEntity extends BaseEntity{
    private String name;
    
    @Column(length = 1000)
    private String description;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<UserEntity> users = new HashSet<>();
}
