package Server.TeaTimeProjectRefactoring.dto;

import Server.TeaTimeProjectRefactoring.entity.Member;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Post {
        private String email;
        private String password;
        private String userName;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Patch {
        private Long memberId;
        private String email;
        private String userName;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private Long memberId;
        private String email;
        private String password;
        private String userName;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private Boolean deletedAt;


        // 파라미터를 받아서 리턴할 값으로 변환하는 것
        public static MemberDto.Response fromEntity(Member entity) {
            return new MemberDto.Response(
                entity.getMemberId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getUserName(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getDeletedAt()
            );
        }
    }
}