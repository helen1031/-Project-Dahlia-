package project.dahlia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dahlia.dto.PostDTO;
import project.dahlia.dto.ResponseDTO;
import project.dahlia.model.PostEntity;
import project.dahlia.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDTO dto){
        try {
            String temporaryUserId = "temporary-user";

            PostEntity entity = PostDTO.toEntity(dto);
            entity.setId(null);

            entity.setUserId(temporaryUserId);

            List<PostEntity> entities = service.create(entity);

            List<PostDTO> dtos = entities.stream().map(PostDTO::new).collect(Collectors.toList());

            ResponseDTO<PostDTO> response = ResponseDTO.<PostDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<PostDTO> response = ResponseDTO.<PostDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrievePostList() {
        String temporaryUserId = "temporary-user";

        List<PostEntity> entities = service.retrieve(temporaryUserId);

        List<PostDTO> dtos = entities.stream().map(PostDTO::new).collect(Collectors.toList());

        ResponseDTO<PostDTO> response = ResponseDTO.<PostDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updatePost(@RequestBody PostDTO dto) {
        String temporaryUserId = "temporary-user";

        PostEntity entity = PostDTO.toEntity(dto);
        entity.setUserId(temporaryUserId);

        List<PostEntity> entities = service.update(entity);

        List<PostDTO> dtos = entities.stream().map(PostDTO::new).collect(Collectors.toList());

        ResponseDTO<PostDTO> response = ResponseDTO.<PostDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePost(@RequestBody PostDTO dto) {
        try {
            String temporaryUserId = "temporary-user";

            PostEntity entity = PostDTO.toEntity(dto);
            entity.setUserId(temporaryUserId);

            List<PostEntity> entities = service.delete(entity);

            List<PostDTO> dtos = entities.stream().map(PostDTO::new).collect(Collectors.toList());

            ResponseDTO<PostDTO> response = ResponseDTO.<PostDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<PostDTO> response = ResponseDTO.<PostDTO>builder().error(error).build();

            return ResponseEntity.badRequest().body(response);
        }
    }
}
