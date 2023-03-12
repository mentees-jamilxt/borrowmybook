package mentees.jamilxt.borrowmybook.persistence.repository;

import java.awt.print.Pageable;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mentees.jamilxt.borrowmybook.persistence.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
	Page<UserEntity> findAll(Pageable pageable);
}
