package mentees.jamilxt.borrowmybook.service;



import mentees.jamilxt.borrowmybook.entity.Role;
import mentees.jamilxt.borrowmybook.entity.User;
import mentees.jamilxt.borrowmybook.repositories.RoleRepository;
import mentees.jamilxt.borrowmybook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    // Get All Users
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    // Save Users
    public User saveUser(User user) {
        encodePassword(user);
        return userRepository.save(user);
    }

    // Get a single User by an id
    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    public User getUserByEmail(String s) {
        return userRepository.getUserByEmail(s);
    }

    // Update User information
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // delete User by their id
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }




    public boolean isEmailUnique(Integer id, String email) {
        User userByEmail = userRepository.getUserByEmail(email);
        if (userByEmail == null) {
            return true;
        }
        if (id == null) {
            return false;
        } else {
            return userByEmail.getId().equals(id);
        }
    }



    // Method of password encoder using User object
    private void encodePassword(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
    }

    // Method of password encoder using String
    public String encodePasswordUsingString(String password) {
        return passwordEncoder.encode(password);
    }



}