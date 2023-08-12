package Server.TeaTimeProjectRefactoring.dto;

import Server.TeaTimeProjectRefactoring.entity.Post;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Post {
        private String title;
        private String content;
        private Long memberId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Patch {
        private String title;
        private String content;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private Long memberId;
        private Long postId;
        private String title;
        private String content;
        private int views;
        private LocalDateTime createAt;
        private LocalDateTime modifiedAt;

        public Response(String title, String content, LocalDateTime modifiedAt) {
        }

        public static PostDto.Response fromEntity(
            Server.TeaTimeProjectRefactoring.entity.Post entity) {
            return new PostDto.Response(
                entity.getMemberId(),
                entity.getPostId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getViews(),
                entity.getCreateAt(),
                entity.getModifiedAt()
            );
        }

        public static PostDto.Response fromEntityOfPatch(
            Server.TeaTimeProjectRefactoring.entity.Post entity) {
            return new PostDto.Response(
                entity.getTitle(),
                entity.getContent(),
                entity.getModifiedAt()
            );
        }
    }
}