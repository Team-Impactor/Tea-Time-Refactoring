package Server.TeaTimeProjectRefactoring.service;

import Server.TeaTimeProjectRefactoring.dto.NoticeDto;
import Server.TeaTimeProjectRefactoring.entity.Member;
import Server.TeaTimeProjectRefactoring.entity.Notice;
import Server.TeaTimeProjectRefactoring.global.error.BusinessException;
import Server.TeaTimeProjectRefactoring.global.error.ErrorCode;
import Server.TeaTimeProjectRefactoring.repository.NoticeRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.IOTools;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberService memberService;

    public Notice createNoticeLogic(NoticeDto.Post data) {
        Member member = memberService.findVerifyMemberByMemberId(data.getMemberId());

        Notice notice = Notice.createOf(
            data.getTitle(),
            data.getContent(),
            member.getMemberId()
        );

        return noticeRepository.save(notice);
    }

    public Notice updateNoticeLogic(NoticeDto.Patch data, Long noticeId) {
        Notice notice = noticeRepository.getReferenceById(noticeId);

        notice.update(
            data.getTitle(),
            data.getContent()
        );

        return noticeRepository.save(notice);
    }

    public Notice findVerifyNoticeByNoticeId(Long noticeId) {

        Optional<Notice> optionalNotice = noticeRepository.findById(noticeId);

        Notice findNotice = optionalNotice.orElseThrow(
            () -> new BusinessException(ErrorCode.TEST)
        );

        return findNotice;
    }

    public Page<Notice> getAllNoticeLogic(int page, int size) {
        return noticeRepository.findAll(
            PageRequest.of(page, size, Sort.by("noticeId").descending())
        );
    }

    public void deleteNoticeLogic(Long noticeId) {
        Notice notice = noticeRepository.getReferenceById(noticeId);
        noticeRepository.delete(notice);
    }
}