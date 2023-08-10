package Server.TeaTimeProjectRefactoring.controller;

import Server.TeaTimeProjectRefactoring.dto.PostDto;
import Server.TeaTimeProjectRefactoring.service.PostService;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     *
     * @param data : title, content
     * @return : title, content, createdAt
     * @desc : 게시물 등록
     */
    @PostMapping("/post")
    public ResponseEntity<PostDto.Response> postPost(
        @RequestBody PostDto.Post data) {
        // 로그인 된 회원 검증을 위한 파라미터를 data에 추가해야 함

        return new ResponseEntity<>(
            PostDto.Response.fromEntity(postService.createPostLogic(data)),
            HttpStatus.CREATED);
    }

    @PatchMapping("/patch/{post-id}")
    public ResponseEntity<PostDto.Response> patchPost(
        @RequestBody PostDto.Patch data,
        @PathVariable("post-id") @Positive Long postId) {

        return new ResponseEntity<>(
            PostDto.Response.fromEntityOfPatch(postService.updatePostLogic(data, postId)),
            HttpStatus.OK
        );
    }

    @GetMapping("/lookup/{post-id}")
    public ResponseEntity<PostDto.Response> findPost(
        @PathVariable("post-id") @Positive Long postId
    ) {

        return new ResponseEntity<>(
            PostDto.Response.fromEntity(postService.findVerifyPostByPostId(postId)),
            HttpStatus.OK
        );
    }
}
