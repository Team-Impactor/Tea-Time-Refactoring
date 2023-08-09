package Server.TeaTimeProjectRefactoring.service;

import Server.TeaTimeProjectRefactoring.dto.PostDto;
import Server.TeaTimeProjectRefactoring.entity.Member;
import Server.TeaTimeProjectRefactoring.entity.Post;
import Server.TeaTimeProjectRefactoring.repository.MemberRepository;
import Server.TeaTimeProjectRefactoring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    public Post createPostLogic(PostDto.Post data, Long memberId) {
        Member member = memberService.findVerifyMemberByMemberId(memberId);

        Post post = Post.createOf(
            data.getTitle(),
            data.getContent(),
            member.getMemberId()
        );

        return postRepository.save(post);
    }
}
