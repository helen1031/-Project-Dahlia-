package project.dahlia.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.dahlia.model.FlowerPostEntity;

import java.util.List;

@Repository
public interface FlowerPostRepository extends JpaRepository<FlowerPostEntity, String> {

    List<FlowerPostEntity> findByUserId(String userId);
}
