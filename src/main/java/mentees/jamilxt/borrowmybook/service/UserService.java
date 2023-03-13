package mentees.jamilxt.borrowmybook.service;

import java.util.UUID;

import mentees.jamilxt.borrowmybook.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.exception.custom.NotFoundException;
import mentees.jamilxt.borrowmybook.mapper.UserMapper;
import mentees.jamilxt.borrowmybook.model.domain.User;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateUserRequest;
import mentees.jamilxt.borrowmybook.persistence.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
	private static final String USER_NOT_FOUND = "User not found";
	private final UserMapper userMapper;
	private final UserRepository userRepository;
	
	public Page<User> getUsers(Pageable pageable) {
		return userRepository.findAll(pageable).map(userMapper::toDomain);
	}
	
	public User getUserById(UUID id) {
		var userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
		return userMapper.toDomain(userEntity);
	}
	
	public void createUser(CreateUserRequest request) {
		var userEntity = userMapper.toEntity(request);
		userRepository.save(userEntity);
	}

	public void updateUser(CreateUserRequest request) {
		var userEntity = userRepository.findById(request.getId()).orElseThrow(()-> new NotFoundException(USER_NOT_FOUND));
		userEntity.setFirstName(request.getFirstName());
		userEntity.setLastName(request.getLastName());
		userEntity.setEmail(request.getEmail());
		userRepository.save(userEntity);
	}
	
	public void deleteUser(UUID id) {
		userRepository.deleteById(id);
	}
}
