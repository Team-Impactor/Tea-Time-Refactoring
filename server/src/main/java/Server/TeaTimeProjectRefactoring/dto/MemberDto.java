package Server.TeaTimeProjectRefactoring.dto;

import Server.TeaTimeProjectRefactoring.entity.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
        private String memberName;
        private String nickName;
        private String birth;
        private String profile;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Patch {
        private Long memberId;
        private String email;
        private String password;
        private String memberName;
        private String nickName;
        private String birth;
        private String profile;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private Long memberId;
        private String email;
        private String password;
        private String memberName;
        private String nickName;
        private String birth;
        private String profile;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private Boolean deletedAt;


        // 파라미터를 받아서 리턴할 값으로 변환하는 것
        public static MemberDto.Response fromEntity(Member entity) {
            return new MemberDto.Response(
                entity.getMemberId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getMemberName(),
                entity.getNickName(),
                entity.getBirth(),
                entity.getProfile(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getDeletedAt()
            );
        }

    }

    @Getter
    @AllArgsConstructor
    public static class MemberPageResponse {
        private Long memberId;
        private String email;
        private String memberName;
        private String nickName;
        private String birth;
        private String profile;
        private LocalDateTime createdAt;

        public static MemberDto.MemberPageResponse fromMemberEntity(Member entity) {
            return new MemberDto.MemberPageResponse(
                entity.getMemberId(),
                entity.getEmail(),
                entity.getMemberName(),
                entity.getNickName(),
                entity.getBirth(),
                entity.getProfile(),
                entity.getCreatedAt()
            );
        }

        public static List<MemberDto.MemberPageResponse> fromAllMemberEntity(List<Member> entities) {

            List<MemberDto.MemberPageResponse> memberPageResponseList = new ArrayList<>(entities.size());
            for(Member member : entities) {
                memberPageResponseList.add(MemberDto.MemberPageResponse.fromMemberEntity(member));
            }

            return memberPageResponseList;
        }
    }
}