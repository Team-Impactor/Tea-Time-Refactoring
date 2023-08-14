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

    public Program(String title, String content, int userMax, String content1, String image, String announce, String zoomLink, String dateStart, String dateEnd) {
    }

    public static Program createOf(
        String title, String content, int userMax, int cost, String image, String announce, String zoomLink, String dateStart, String dateEnd) {
        return new Program(title, content, userMax, content, image, announce, zoomLink, dateStart,
            dateEnd);
    }
}
