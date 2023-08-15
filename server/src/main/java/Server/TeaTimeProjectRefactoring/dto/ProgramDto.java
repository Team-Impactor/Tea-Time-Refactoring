package Server.TeaTimeProjectRefactoring.dto;

import Server.TeaTimeProjectRefactoring.entity.Program;
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
                entity.getDateEnd()
            );
        }
    }
}
