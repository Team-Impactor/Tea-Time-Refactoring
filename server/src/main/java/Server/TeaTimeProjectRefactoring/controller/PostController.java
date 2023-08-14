package Server.TeaTimeProjectRefactoring.controller;

import Server.TeaTimeProjectRefactoring.dto.MultiResponseDto;
import Server.TeaTimeProjectRefactoring.dto.PostDto;
import Server.TeaTimeProjectRefactoring.entity.Post;
import Server.TeaTimeProjectRefactoring.service.PostService;
import java.util.List;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     *
     * @param data : title, content
     * @return : PostDto.Response
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

    /**
     *
     * @param data : title, content
     * @param postId
     * @return : PostDto.Response
     * @desc : 게시물 수정
     */
    @PatchMapping("/patch/{post-id}")
    public ResponseEntity<PostDto.Response> patchPost(
        @RequestBody PostDto.Patch data,
        @PathVariable("post-id") @Positive Long postId) {

        return new ResponseEntity<>(
            PostDto.Response.fromEntityOfPatch(postService.updatePostLogic(data, postId)),
            HttpStatus.OK
        );
    }

    /**
     *
     * @param postId
     * @return : PostDto.Response
     * @desc : 게시물 개별 조회
     */
    @GetMapping("/lookup/{post-id}")
    public ResponseEntity<PostDto.Response> findPost(
        @PathVariable("post-id") @Positive Long postId
    ) {

        return new ResponseEntity<>(
            PostDto.Response.fromEntity(postService.findVerifyPostByPostId(postId)),
            HttpStatus.OK
        );
    }

    @GetMapping("/lookup/list")
    public ResponseEntity findAllPost(
        @RequestParam(defaultValue = "1") @Positive int page,
        @RequestParam(defaultValue = "10") @Positive int size
    ) {

        Page<Post> postPage = postService.getAllPostLogic(page-1, size);

        List<Post> postList = postPage.getContent();
        List<PostDto.PostPageResponse> responseList = PostDto.PostPageResponse.fromAllPostEntity(
            postList
        );

        return new ResponseEntity<>(
            new MultiResponseDto<>(postList, postPage),
            HttpStatus.OK
        );
    }

    /**
     *
     * @param postId
     * @return : HttpStatus.NO_CONTENT
     * @desc : 게시물 개별 삭제
     */
    @DeleteMapping("/delete/{post-id}")
    public ResponseEntity deletePost(
        @PathVariable("post-id") @Positive Long postId
    ) {

        postService.deletePostLogic(postId);

        return new ResponseEntity<>(
            HttpStatus.NO_CONTENT
        );
    }
}
