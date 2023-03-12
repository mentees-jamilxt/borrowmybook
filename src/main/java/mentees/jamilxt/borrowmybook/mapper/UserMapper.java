package mentees.jamilxt.borrowmybook.mapper;

import org.mapstruct.Mapper;
import mentees.jamilxt.borrowmybook.model.domain.User;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateUserRequest;
import mentees.jamilxt.borrowmybook.persistence.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	User toDomain(UserEntity userEntity);

	UserEntity toEntity(CreateUserRequest request);
}
