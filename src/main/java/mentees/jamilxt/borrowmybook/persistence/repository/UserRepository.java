//package mentees.jamilxt.borrowmybook.persistence.repository;
//
//import mentees.jamilxt.borrowmybook.persistence.entity.RoleEntity;
//import mentees.jamilxt.borrowmybook.persistence.entity.UserEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.UUID;
//
//@Repository
//public interface UserRepository extends JpaRepository<UserEntity, UUID> {
//    @Query("Select u from UserEntity u Where u.email = :email")
//    UserEntity getUserByEmail(@Param("email") String email);
//}
