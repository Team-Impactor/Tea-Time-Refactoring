package Server.TeaTimeProjectRefactoring.dto;

import Server.TeaTimeProjectRefactoring.entity.Notice;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class NoticeDto {

    @Getter
    public static class Post {
        private String title;
        private String content;
        private Long memberId;
    }

    @Getter
    public static class Patch {
        private String title;
        private String content;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private Long noticeId;
        private String title;
        private String content;
        private int views;
        private Long memberId;

        public static NoticeDto.Response fromEntity(Notice entity) {
            return new NoticeDto.Response(
                entity.getNoticeId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getViews(),
                entity.getMemberId()
            );
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class NoticePageResponse {
        private Long noticeId;
        private String title;
        private String content;
        private int views;
        private Long memberId;

        public static NoticeDto.NoticePageResponse fromNoticeEntity(Notice entity) {
            return new NoticeDto.NoticePageResponse(
                entity.getNoticeId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getViews(),
                entity.getMemberId()
            );
        }

        public static List<NoticeDto.NoticePageResponse> fromAllNoticeEntity(List<Notice> entities) {
            List<NoticeDto.NoticePageResponse> noticePageResponseList = new ArrayList<>();

            for (Notice notice : entities) {
                noticePageResponseList.add(
                    NoticeDto.NoticePageResponse.fromNoticeEntity(notice)
                );
            }

            return noticePageResponseList;
        }
    }
}
