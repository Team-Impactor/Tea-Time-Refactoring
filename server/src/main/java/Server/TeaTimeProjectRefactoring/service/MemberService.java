package Server.TeaTimeProjectRefactoring.service;

import Server.TeaTimeProjectRefactoring.dto.MemberDto;
import Server.TeaTimeProjectRefactoring.entity.Member;
import Server.TeaTimeProjectRefactoring.global.error.BusinessException;
import Server.TeaTimeProjectRefactoring.global.error.ErrorCode;
import Server.TeaTimeProjectRefactoring.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member createMember(MemberDto.Post data) {
        /*
        정적 팩토리 메서드 패턴을 활용하여 생성자 생성
         */
        Member member = Member.createOf(
            data.getEmail(),
            data.getPassword(),
            data.getUserName()
        );

        return memberRepository.save(member);
    }


    public Member updateMember(MemberDto.Patch data, Long id) {
        // getReferenceById, getId 차이 확인 후 블로깅 필요
        Member member = memberRepository.getReferenceById(id);

        /*
        update 엔티티 유틸 함수 활용
        임시로 email, userName만 정적으로 수정할 수 있도록 함
        추후, 변경 필수
         */
        member.update(data.getEmail(), data.getUserName());

        return memberRepository.save(member);
    }

    public Member findVerifyMemberByMemberId(Long id) {

        Optional<Member> optionalMember = memberRepository.findById(id);

        Member findMember = optionalMember.orElseThrow(
            () -> new BusinessException(ErrorCode.TEST)
        );

        return findMember;
    }
}