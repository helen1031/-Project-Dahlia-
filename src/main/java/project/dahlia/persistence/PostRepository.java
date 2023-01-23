package project.dahlia.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.dahlia.model.PostEntity;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {

    List<PostEntity> findByUserId(String userId);
}
