//package mentees.jamilxt.borrowmybook.security;
//
//import mentees.jamilxt.borrowmybook.exception.custom.NotFoundException;
//import mentees.jamilxt.borrowmybook.persistence.entity.UserEntity;
//import mentees.jamilxt.borrowmybook.persistence.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//public class PortalUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws NotFoundException {
//        UserEntity userEntity = userRepository.getUserByEmail(email);
//        if (userEntity != null) {
//            return new PortalUserDetails(userEntity);
//        }
//        throw new NotFoundException("Could not find user with email: " + email);
//    }
//}
