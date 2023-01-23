package project.dahlia.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.dahlia.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByUsername(String username);

    UserEntity findByUsernameAndPassword(String username, String password);

    Boolean existsByUsername(String username);

}
