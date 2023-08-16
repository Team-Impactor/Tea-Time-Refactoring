package Server.TeaTimeProjectRefactoring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : 이대겸
 * @desc : Program 엔티티
 */
@Entity
@Getter
@NoArgsConstructor
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programId;

    private String title;
    private String content;
    private int userMax;
    private int userCount = 0;
    private int cost;
    private String image;
    private String announce;
    private String zoomLink;
    private String dateStart;
    private String dateEnd;
    private Long memberId;

    public Program(String title, String content, int userMax, int cost, String image, String announce, String zoomLink, String dateStart, String dateEnd, Long memberId) {
        this.title = title;
        this.content = content;
        this.userMax = userMax;
        this.cost = cost;
        this.image = image;
        this.announce = announce;
        this.zoomLink = zoomLink;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.memberId = memberId;
    }


    public static Program createOf(
        String title, String content, int userMax, int cost, String image, String announce, String zoomLink, String dateStart, String dateEnd, Long memberId
    ) {
        return new Program(title, content, userMax, cost, image, announce, zoomLink, dateStart,
            dateEnd, memberId);
    }

    public void update(
        String title, String content, int userMax, int cost, String image, String announce, String zoomLink, String dateStart, String dateEnd
    ) {
        this.title = title;
        this.content = content;
        this.userMax = userMax;
        this.cost = cost;
        this.image = image;
        this.announce = announce;
        this.zoomLink = zoomLink;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }
}
