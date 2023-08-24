package Server.TeaTimeProjectRefactoring.controller;

import Server.TeaTimeProjectRefactoring.dto.CounselorDto;
import Server.TeaTimeProjectRefactoring.dto.MultiResponseDto;
import Server.TeaTimeProjectRefactoring.entity.Counselor;
import Server.TeaTimeProjectRefactoring.service.CounselorService;
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
@RequestMapping("/api/counselors")
@RequiredArgsConstructor
public class CounselorController {

    private final CounselorService counselorService;

    /**
     *
     * @param data : email, password, counselorName, birth, graduated, profile, career, introduce, expertiseField
     * @return : CounselorDto.Response
     * @desc : 상담사 등록
     */
    @PostMapping("/post")
    public ResponseEntity postCounselor(
        @RequestBody CounselorDto.Post data
    ) {

        return new ResponseEntity<>(
            CounselorDto.Response.fromEntity(counselorService.createCounselorLogic(data)),
            HttpStatus.CREATED
        );
    }

    /**
     *
     * @param data : email, password, counselorName, birth, graduated, profile, career, introduce, expertiseField
     * @param counselorId
     * @return : CounselorDto.Response
     * @desc : 상담사 정보 수정
     */
    @PatchMapping("/patch/{counselor-id}")
    public ResponseEntity patchCounselor(
        @RequestBody CounselorDto.Patch data,
        @PathVariable("counselor-id") @Positive Long counselorId
    ) {
        return new ResponseEntity<>(
            CounselorDto.Response.fromEntity(
                counselorService.updateCounselorLogic(data, counselorId)),
            HttpStatus.OK
        );
    }

    /**
     *
     * @param counselorId
     * @return : CounselorDto.Response
     * @desc : 상담사 개별 조회
     */
    @GetMapping("/lookup/{counselor-id}")
    public ResponseEntity getCounselor(
        @PathVariable("counselor-id") @Positive Long counselorId
    ) {
        return new ResponseEntity<>(
            CounselorDto.Response.fromEntity(
                counselorService.findVerifyCounselorByCounselorId(counselorId)),
            HttpStatus.OK
        );
    }

    /**
     *
     * @param page
     * @param size
     * @return : MultiResponseDto
     * @desc : 상담사 전체 조회
     */
    @GetMapping("/lookup/list")
    public ResponseEntity getAllCounselor(
        @RequestParam(defaultValue = "1") @Positive int page,
        @RequestParam(defaultValue = "10") @Positive int size
    ) {
        Page<Counselor> counselorPage = counselorService.getAllCounselorLogic(page - 1, size);

        List<Counselor> counselorList = counselorPage.getContent();

        List<CounselorDto.CounselorPageResponse> responseList = CounselorDto.CounselorPageResponse.fromAllCounselorEntity(
            counselorList);
        return new ResponseEntity<>(
            new MultiResponseDto<>(responseList, counselorPage),
            HttpStatus.OK
        );
    }

    /**
     *
     * @param counselorId
     * @return : HttpStatus.NO_CONTENT
     * @desc : 상담사 개별 삭제
     */
    @DeleteMapping("/delete/{counselor-id}")
    public ResponseEntity deleteCounselor(
        @PathVariable("counselor-id") @Positive Long counselorId
    ) {
        counselorService.deleteCounselorLogic(counselorId);

        return new ResponseEntity<>(
            HttpStatus.NO_CONTENT
        );
    }
}
