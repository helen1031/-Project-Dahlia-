package project.dahlia.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dahlia.model.PostEntity;
import project.dahlia.persistence.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<PostEntity> create(final PostEntity entity) {
        validate(entity);

        repository.save(entity);
        log.info("PostEntity ID : {} is saved.", entity.getId());

        return repository.findByUserId(entity.getUserId());
    }

    public List<PostEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }

    public List<PostEntity> update(final PostEntity entity) {
        validate(entity);

        final Optional<PostEntity> original = repository.findById(entity.getId());

        original.ifPresent(post -> {
            post.setFlowerName(entity.getFlowerName());
            post.setFlowerColor1(entity.getFlowerColor1());
            post.setFlowerColor2(entity.getFlowerColor1());
            post.setFlowerColor3(entity.getFlowerColor1());
            post.setFlowerFeature(entity.getFlowerFeature());
            post.setFlowerImg1(entity.getFlowerImg1());
            post.setFlowerImg2(entity.getFlowerImg2());
            post.setFlowerImg3(entity.getFlowerImg3());
            post.setFlowerImg4(entity.getFlowerImg4());
            post.setFlowerImg5(entity.getFlowerImg5());
            post.setFlowerQuantity(entity.getFlowerQuantity());
            post.setDisclosureScope(entity.getDisclosureScope());
        });

        log.info("PostEntity ID : {} is modified.", entity.getId());

        return retrieve(entity.getUserId());
    }

    public List<PostEntity> delete(final PostEntity entity) {
        validate(entity);

        try {
            repository.delete(entity);
        } catch (Exception e) {
            log.error("error deleting PostEntity " + entity.getId());
        }
        return retrieve(entity.getUserId());
    }

    private void validate(PostEntity entity) {
        if(entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }

        log.info("PostEntity ID : {} is Validated.", entity.getId());
    }
}
