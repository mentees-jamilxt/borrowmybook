package mentees.jamilxt.borrowmybook.mapper;

import mentees.jamilxt.borrowmybook.model.domain.Book;
import mentees.jamilxt.borrowmybook.model.domain.Role;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateBookRequest;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateRoleRequest;
import mentees.jamilxt.borrowmybook.persistence.entity.BookEntity;
import mentees.jamilxt.borrowmybook.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toDomain(RoleEntity bookEntity);
    RoleEntity toEntity(CreateRoleRequest request);
}
