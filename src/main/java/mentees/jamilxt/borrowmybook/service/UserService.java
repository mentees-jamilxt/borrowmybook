//package mentees.jamilxt.borrowmybook.service;
//
//import mentees.jamilxt.borrowmybook.persistence.entity.UserEntity;
//import mentees.jamilxt.borrowmybook.persistence.repository.RoleRepository;
//import mentees.jamilxt.borrowmybook.persistence.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//
//@Service
//public class UserService {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//
//    // Get All Users
//    public List<UserEntity> getAllUser() {
//        return userRepository.findAll();
//    }
//
//    // Save Users
//    public UserEntity saveUser(UserEntity user) {
//        encodePassword(user);
//        return userRepository.save(user);
//    }
//
//    // Get a single User by an id
//    public UserEntity getUserById(Integer id) {
//        return userRepository.findById(id).get();
//    }
//
//
//    // Update User information
//    public User updateUser(User user) {
//        return userRepository.save(user);
//    }
//
//    // delete User by their id
//    public void deleteUserById(Integer id) {
//        userRepository.deleteById(id);
//    }
//
//
//    // Method of password encoder using User object
//    private void encodePassword(UserEntity user) {
//        String encodePassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodePassword);
//    }
//
//
//}