package Server.TeaTimeProjectRefactoring.dto;

import Server.TeaTimeProjectRefactoring.entity.Pay;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class PayDto {

    @Getter
    public static class Post {
        private String cardOwner;
        private String cardNum;
        private String cvvNum;
        private String expirationTime;
        private Long memberId;
    }

    @Getter
    public static class Patch {
        private String cardOwner;
        private String cardNum;
        private String cvvNum;
        private String expirationTime;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private Long payId;
        private String cardOwner;
        private String cardNum;
        private String cvvNum;
        private String expirationTime;
        private Long memberId;

        public static PayDto.Response fromEntity(Pay entity) {
            return new PayDto.Response(
                entity.getPayId(),
                entity.getCardOwner(),
                entity.getCardNum(),
                entity.getCvvNum(),
                entity.getExpirationTime(),
                entity.getMemberId()
            );
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class PayPageResponse {
        private Long payId;
        private String cardOwner;
        private String cardNum;
        private String cvvNum;
        private String expirationTime;
        private Long memberId;

        public static PayDto.PayPageResponse fromPayEntity(Pay entity) {
            return new PayDto.PayPageResponse(
                entity.getPayId(),
                entity.getCardOwner(),
                entity.getCardNum(),
                entity.getCvvNum(),
                entity.getExpirationTime(),
                entity.getMemberId()
            );
        }

        public static List<PayDto.PayPageResponse> fromAllPayEntity(List<Pay> entities) {
            List<PayDto.PayPageResponse> payPageResponseList = new ArrayList<>();

            for(Pay pay : entities) {
                payPageResponseList.add(
                    PayDto.PayPageResponse.fromPayEntity(pay)
                );
            }

            return payPageResponseList;
        }
    }
}
