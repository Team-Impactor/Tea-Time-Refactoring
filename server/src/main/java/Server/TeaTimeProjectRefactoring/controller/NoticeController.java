package Server.TeaTimeProjectRefactoring.controller;

import Server.TeaTimeProjectRefactoring.dto.MultiResponseDto;
import Server.TeaTimeProjectRefactoring.dto.NoticeDto;
import Server.TeaTimeProjectRefactoring.entity.Notice;
import Server.TeaTimeProjectRefactoring.service.NoticeService;
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
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    /**
     *
     * @param data : title, content, memberId
     * @return : NoticeDto.Response
     * @desc : 공지사항 등록
     */
    @PostMapping("/post")
    public ResponseEntity<NoticeDto.Response> postNotice(
        @RequestBody NoticeDto.Post data
    ) {
        return new ResponseEntity<>(
            NoticeDto.Response.fromEntity(noticeService.createNoticeLogic(data)),
            HttpStatus.CREATED
        );
    }

    /**
     *
     * @param data : title, content
     * @param noticeId
     * @return : NoticeDto.Response
     * @desc : 공지사항 수정
     */
    @PatchMapping("/patch/{notice-id}")
    public ResponseEntity<NoticeDto.Response> patchNotice(
        @RequestBody NoticeDto.Patch data,
        @PathVariable("notice-id") @Positive Long noticeId
    ) {
        return new ResponseEntity<>(
            NoticeDto.Response.fromEntity(noticeService.updateNoticeLogic(data, noticeId)),
            HttpStatus.OK
        );
    }

    /**
     *
     * @param noticeId
     * @return : NoticeDto.Response
     * @desc : 공지사항 개별 조회
     */
    @GetMapping("/lookup/{notice-id}")
    public ResponseEntity<NoticeDto.Response> findNotice(
        @PathVariable("notice-id") @Positive Long noticeId
    ) {
        return new ResponseEntity<>(
            NoticeDto.Response.fromEntity(noticeService.findVerifyNoticeByNoticeId(noticeId)),
            HttpStatus.OK
        );
    }

    /**
     *
     * @param page
     * @param size
     * @return : MultiResponseDto
     * @desc : 공지사항 전체 조회
     */
    @GetMapping("/lookup/list")
    public ResponseEntity<MultiResponseDto> findAllNotice(
        @RequestParam(defaultValue = "1") @Positive int page,
        @RequestParam(defaultValue = "10") @Positive int size
    ) {
        Page<Notice> noticePage = noticeService.getAllNoticeLogic(page - 1, size);

        List<Notice> noticeList = noticePage.getContent();

        List<NoticeDto.NoticePageResponse> responseList = NoticeDto.NoticePageResponse.fromAllNoticeEntity(
            noticeList);

        return new ResponseEntity<>(
            new MultiResponseDto<>(responseList, noticePage),
            HttpStatus.OK
        );
    }

    /**
     *
     * @param noticeId
     * @return : HttpStatus.NO_CONTENT
     * @desc : 공지사항 개별 삭제
     */
    @DeleteMapping("/delete/{notice-id}")
    public ResponseEntity deleteNotice(
        @PathVariable("notice-id") @Positive Long noticeId
    ) {

        noticeService.deleteNoticeLogic(noticeId);

        return new ResponseEntity<>(
            HttpStatus.NO_CONTENT
        );
    }
}
