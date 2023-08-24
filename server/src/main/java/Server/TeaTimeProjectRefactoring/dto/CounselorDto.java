package Server.TeaTimeProjectRefactoring.dto;

import Server.TeaTimeProjectRefactoring.entity.Counselor;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CounselorDto {

    @Getter
    public static class Post {
        private String email;
        private String password;
        private String counselorName;
        private String birth;
        private String graduated;
        private String profile;
        private String career;
        private String introduce;
        private String expertiseField;
    }

    @Getter
    public static class Patch {
        private String email;
        private String password;
        private String counselorName;
        private String birth;
        private String graduated;
        private String profile;
        private String career;
        private String introduce;
        private String expertiseField;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private Long counselorId;
        private String email;
        private String counselorName;
        private String birth;
        private String graduated;
        private String profile;
        private String career;
        private String introduce;
        private String expertiseField;

        public static CounselorDto.Response fromEntity(Counselor entity) {
            return new CounselorDto.Response(
                entity.getCounselorId(),
                entity.getEmail(),
                entity.getCounselorName(),
                entity.getBirth(),
                entity.getGraduated(),
                entity.getProfile(),
                entity.getCareer(),
                entity.getIntroduce(),
                entity.getExpertiseField()
            );
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CounselorPageResponse {
        private Long counselorId;
        private String email;
        private String counselorName;
        private String birth;
        private String graduated;
        private String profile;
        private String career;
        private String introduce;
        private String expertiseField;

        public static CounselorDto.CounselorPageResponse fromCounselorEntity(Counselor entity) {
            return new CounselorDto.CounselorPageResponse(
                entity.getCounselorId(),
                entity.getEmail(),
                entity.getCounselorName(),
                entity.getBirth(),
                entity.getGraduated(),
                entity.getProfile(),
                entity.getCareer(),
                entity.getIntroduce(),
                entity.getExpertiseField()
            );
        }

        public static List<CounselorDto.CounselorPageResponse> fromAllCounselorEntity(List<Counselor> entities) {
            List<CounselorDto.CounselorPageResponse> counselorPageResponseList = new ArrayList<>();

            for(Counselor counselor : entities) {
                counselorPageResponseList.add(
                    CounselorDto.CounselorPageResponse.fromCounselorEntity(counselor)
                );
            }

            return counselorPageResponseList;
        }
    }
}
