package Server.TeaTimeProjectRefactoring.controller;

import Server.TeaTimeProjectRefactoring.dto.PostDto;
import Server.TeaTimeProjectRefactoring.service.PostService;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     *
     * @param data : title, content
     * @param memberId
     * @return : title, content, createdAt
     * @desc : 게시물 등록
     */
    @PostMapping("/{member-id}")
    public ResponseEntity<PostDto.Response> postPost(
        @RequestBody PostDto.Post data,
        @PathVariable("member-id") @Positive Long memberId) {

        return new ResponseEntity<>(
            PostDto.Response.fromEntityOfPost(postService.createPostLogic(data, memberId)),
            HttpStatus.CREATED);
    }
}
