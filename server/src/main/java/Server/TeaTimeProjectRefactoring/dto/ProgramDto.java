package Server.TeaTimeProjectRefactoring.dto;

import Server.TeaTimeProjectRefactoring.entity.Program;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProgramDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Post {
        private String title;
        private String content;
        private int userMax;
        private int cost;
        private String image;
        private String announce;
        private String zoomLink;
        private String dateStart;
        private String dateEnd;
        private Long memberId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Patch {
        private String title;
        private String content;
        private int userMax;
        private int cost;
        private String image;
        private String announce;
        private String zoomLink;
        private String dateStart;
        private String dateEnd;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private Long programId;
        private String title;
        private String content;
        private int userMax;
        private int userCount;
        private int cost;
        private String image;
        private String announce;
        private String zoomLink;
        private String dateStart;
        private String dateEnd;
        private Long memberId;

        public static ProgramDto.Response fromEntity(Program entity) {
            return new ProgramDto.Response(
                entity.getProgramId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getUserMax(),
                entity.getUserCount(),
                entity.getCost(),
                entity.getImage(),
                entity.getAnnounce(),
                entity.getZoomLink(),
                entity.getDateStart(),
                entity.getDateEnd(),
                entity.getMemberId()
            );
        }

        public static ProgramDto.Response fromEntityOfPatch(Program entity) {
            return new ProgramDto.Response(
                entity.getProgramId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getUserMax(),
                entity.getUserCount(),
                entity.getCost(),
                entity.getImage(),
                entity.getAnnounce(),
                entity.getZoomLink(),
                entity.getDateStart(),
                entity.getDateEnd(),
                entity.getMemberId()
            );
        }
    }

    @Getter
    @AllArgsConstructor
    public static class ProgramPageResponse {
        private Long programId;
        private String title;
        private String content;
        private int userMax;
        private int userCount;
        private int cost;
        private String image;
        private String announce;
        private String dateStart;
        private String dateEnd;
        private Long memberId;

        public static ProgramDto.ProgramPageResponse fromProgramEntity(Program entity) {
            return new ProgramDto.ProgramPageResponse(
                entity.getProgramId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getUserMax(),
                entity.getUserCount(),
                entity.getCost(),
                entity.getImage(),
                entity.getAnnounce(),
                entity.getDateStart(),
                entity.getDateEnd(),
                entity.getMemberId()
            );
        }

        public static List<ProgramDto.ProgramPageResponse> fromAllProgramEntity(List<Program> entities) {
            List<ProgramDto.ProgramPageResponse> programPageResponseList = new ArrayList<>();
            for (Program program : entities) {
                programPageResponseList.add(
                    ProgramDto.ProgramPageResponse.fromProgramEntity(program));
            }

            return programPageResponseList;
        }
    }
}
