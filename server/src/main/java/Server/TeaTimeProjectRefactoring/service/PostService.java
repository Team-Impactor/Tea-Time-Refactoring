package Server.TeaTimeProjectRefactoring.service;

import Server.TeaTimeProjectRefactoring.dto.PostDto;
import Server.TeaTimeProjectRefactoring.entity.Member;
import Server.TeaTimeProjectRefactoring.entity.Post;
import Server.TeaTimeProjectRefactoring.global.error.BusinessException;
import Server.TeaTimeProjectRefactoring.global.error.ErrorCode;
import Server.TeaTimeProjectRefactoring.repository.PostRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    public Post createPostLogic(PostDto.Post data) {
        Member member = memberService.findVerifyMemberByMemberId(data.getMemberId());

        Post post = Post.createOf(
            data.getTitle(),
            data.getContent(),
            member.getMemberId()
        );

        return postRepository.save(post);
    }

    public Post updatePostLogic(PostDto.Patch data, Long postId) {
        Post post = postRepository.getReferenceById(postId);

        post.update(
            post.getPostId(),
            data.getTitle(),
            data.getContent()
        );

        return postRepository.save(post);
    }


    public Post findVerifyPostByPostId(Long postId) {

        Optional<Post> optionalPost = postRepository.findById(postId);

        Post findPost = optionalPost.orElseThrow(
            () -> new BusinessException(ErrorCode.TEST)
        );
        return findPost;
    }
}
