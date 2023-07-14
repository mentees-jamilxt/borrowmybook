package mentees.jamilxt.borrowmybook.service;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.constant.AppUtils;
import mentees.jamilxt.borrowmybook.exception.custom.AlreadyExistsException;
import mentees.jamilxt.borrowmybook.exception.custom.NotFoundException;
import mentees.jamilxt.borrowmybook.mapper.UserMapper;
import mentees.jamilxt.borrowmybook.model.domain.User;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateUserRequest;
import mentees.jamilxt.borrowmybook.model.dto.request.UpdateUserRequest;
import mentees.jamilxt.borrowmybook.model.pagination.PaginationArgs;
import mentees.jamilxt.borrowmybook.persistence.entity.UserEntity;
import mentees.jamilxt.borrowmybook.persistence.repository.UserRepository;
import mentees.jamilxt.borrowmybook.persistence.specification.UserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private static final String USER_NOT_FOUND = "User not found";
    private static final String USER_ALREADY_EXISTS = "User already exists with email: ";
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDomain).toList();
    }

    public Page<User> getAllPaginatedUsers(PaginationArgs paginationArgs) {
        Pageable pageable = AppUtils.getPageable(paginationArgs);

        Page<UserEntity> userEntityPage;
        Map<String, Object> specificParameters = AppUtils.getSpecificParameters(paginationArgs.getParameters());
        if (!specificParameters.isEmpty()) {
            Specification<UserEntity> userSpecification = UserSpecification.getSpecification(specificParameters);
            userEntityPage = userRepository.findAll(userSpecification, pageable);
        }
        else {
            userEntityPage = userRepository.findAll(pageable);
        }

        List<User> users = userEntityPage.stream().map(userMapper::toDomain).toList();
        return new PageImpl<>(users, pageable, userEntityPage.getTotalElements());
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDomain);
    }

    public User getUserById(UUID userId) {
        var userEntity = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return userMapper.toDomain(userEntity);
    }

    public User getUserByUsername(String username) {
        var userEntity = userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return userMapper.toDomain(userEntity);
    }

    public User getLoggedInUser(Principal principal) {
        String username = principal.getName();
        return getUserByUsername(username);
    }

    public UUID getLoggedInUserId(Principal principal) {
        String username = principal.getName();
        User user = getUserByUsername(username);
        return user.getId();
    }

    private Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private Boolean existsByEmailAndIdNot(String email, UUID userId) {
        return userRepository.existsByEmailAndIdNot(email, userId);
    }

    private User saveUser(UserEntity userEntity) {
        var savedUserEntity = userRepository.save(userEntity);
        return userMapper.toDomain(savedUserEntity);
    }

    public User createUser(CreateUserRequest request) {
        if (existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException(USER_ALREADY_EXISTS + request.getEmail());
        }

        var userEntity = userMapper.toEntity(request);
        String encodedPassword = encodePasswordUsingString(request.getPassword());
        userEntity.setPassword(encodedPassword);
        return saveUser(userEntity);
    }

    public void updateUser(UpdateUserRequest request) {
        var userEntity = userRepository.findById(request.getId()).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        if (existsByEmailAndIdNot(request.getEmail(), request.getId())) {
            throw new AlreadyExistsException(USER_ALREADY_EXISTS + request.getEmail());
        }

        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setEmail(request.getEmail());
        userEntity.setRoles(request.getRoles());
        userRepository.save(userEntity);
    }

    public void updateUserPassword(String userName, String password) {
        var userEntity = userRepository.findByEmail(userName).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        userEntity.setPassword(password);
        userRepository.save(userEntity);
    }

    public void deleteUser(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(()-> new NotFoundException(USER_NOT_FOUND));
        userRepository.delete(userEntity);
    }

    public String encodePasswordUsingString(String password) {
        return passwordEncoder.encode(password);
    }

    public long countTotalUser() {
        return userRepository.count();
    }
}
