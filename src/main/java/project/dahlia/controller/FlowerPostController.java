package project.dahlia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import project.dahlia.dto.PostDTO;
import project.dahlia.dto.ResponseDTO;
import project.dahlia.model.FlowerPostEntity;
import project.dahlia.service.FlowerPostService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("flower-post")
public class FlowerPostController {

    @Autowired
    private FlowerPostService service;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@AuthenticationPrincipal String userId, @RequestBody PostDTO dto){
        try {
            //String temporaryUserId = "temporary-user";

            FlowerPostEntity entity = PostDTO.toEntity(dto);

            entity.setId(null);

            entity.setUserId(userId);

            List<FlowerPostEntity> entities = service.create(entity);

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
    public ResponseEntity<?> retrievePostList(@AuthenticationPrincipal String userId) {
        //String temporaryUserId = "temporary-user";

        List<FlowerPostEntity> entities = service.retrieve(userId);

        List<PostDTO> dtos = entities.stream().map(PostDTO::new).collect(Collectors.toList());

        ResponseDTO<PostDTO> response = ResponseDTO.<PostDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updatePost(@AuthenticationPrincipal String userId, @RequestBody PostDTO dto) {
        //String temporaryUserId = "temporary-user";

        FlowerPostEntity entity = PostDTO.toEntity(dto);
        entity.setUserId(userId);

        List<FlowerPostEntity> entities = service.update(entity);

        List<PostDTO> dtos = entities.stream().map(PostDTO::new).collect(Collectors.toList());

        ResponseDTO<PostDTO> response = ResponseDTO.<PostDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePost(@AuthenticationPrincipal String userId, @RequestBody PostDTO dto) {
        try {
            //String temporaryUserId = "temporary-user";

            FlowerPostEntity entity = PostDTO.toEntity(dto);
            entity.setUserId(userId);

            List<FlowerPostEntity> entities = service.delete(entity);

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
