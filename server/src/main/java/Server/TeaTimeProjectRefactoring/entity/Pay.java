package Server.TeaTimeProjectRefactoring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payId;
    private String cardOwner;
    private String cardNum;
    private String cvvNum;
    private String expirationTime;
    private Long memberId;

    public Pay(String cardOwner, String cardNum, String cvvNum, String expirationTime, Long memberId) {
        this.cardOwner = cardOwner;
        this.cardNum = cardNum;
        this.cvvNum = cvvNum;
        this.expirationTime = expirationTime;
        this.memberId = memberId;
    }

    public static Pay createOf(String cardOwner, String cardNum, String cvvNum, String expirationTime, Long memberId) {
        return new Pay(cardOwner, cardNum, cvvNum, expirationTime, memberId);
    }

    public void update(String cardOwner, String cardNum, String cvvNum, String expirationTime) {
        this.cardOwner = cardOwner;
        this.cardNum = cardNum;
        this.cvvNum = cvvNum;
        this.expirationTime = expirationTime;
    }
}