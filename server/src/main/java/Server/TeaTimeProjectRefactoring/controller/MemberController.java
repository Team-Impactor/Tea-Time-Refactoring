package Server.TeaTimeProjectRefactoring.controller;

import Server.TeaTimeProjectRefactoring.dto.MemberDto;
import Server.TeaTimeProjectRefactoring.dto.MultiResponseDto;
import Server.TeaTimeProjectRefactoring.entity.Member;
import Server.TeaTimeProjectRefactoring.service.MemberService;
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

/**
 * @author : 이대겸
 * @desc : Member 컨트롤러
 */
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     *
     * @param data : email, password, userName
     * @return : MemberDto.Response
     * @desc : 유저 등록
     */
    @PostMapping
    public ResponseEntity<MemberDto.Response> postMember(
        @RequestBody MemberDto.Post data) {

        return new ResponseEntity<>(
            MemberDto.Response.fromEntity(memberService.createMemberLogic(data)),
            HttpStatus.CREATED
        );
    }


    /**
     *
     * @param data : email, userName
     * @param memberId
     * @return : MemberDto.Response
     * @desc : 유저 정보 수정
     */
    @PatchMapping("/{member-id}")
    public ResponseEntity<MemberDto.Response> patchMember(
        @RequestBody MemberDto.Patch data,
        @PathVariable("member-id") @Positive Long memberId) {

        return new ResponseEntity<>(
            MemberDto.Response.fromEntity(memberService.updateMemberLogic(data, memberId)),
            HttpStatus.OK
        );
    }

    /**
     *
     * @param memberId
     * @return : MemberDto.Response
     * @desc : 유저 정보 개별 조회
     */
    @GetMapping("/lookup/{member-id}")
    public ResponseEntity<MemberDto.Response> findMember(
        @PathVariable("member-id") @Positive Long memberId) {

        return new ResponseEntity<>(
            MemberDto.Response.fromEntity(memberService.findVerifyMemberByMemberId(memberId)),
            HttpStatus.OK
        );
    }

    /**
     *
     * @param memberId
     * @return HttpStatus.NO_CONTENT
     * @desc : 유저 정보 개별 삭제
     */
    @DeleteMapping("/delete/{member-id}")
    public ResponseEntity deleteMember(
        @PathVariable("member-id") @Positive Long memberId) {

        memberService.deleteMemberLogic(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/lookup/list")
    public ResponseEntity findAllMember(
        @RequestParam(defaultValue = "1") @Positive int page,
        @RequestParam(defaultValue = "10") @Positive int size
    ) {

        Page<Member> memberPage = memberService.getAllMemberLogic(
            page -1,
            size
        );

        List<Member> memberList = memberPage.getContent();
        List<MemberDto.MemberPageResponse> responseList = MemberDto.MemberPageResponse.fromAllMemberEntity(
            memberList
        );

        return new ResponseEntity<>(
            new MultiResponseDto<>(responseList, memberPage),
            HttpStatus.OK
        );
    }
}