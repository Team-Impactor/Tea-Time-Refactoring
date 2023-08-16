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
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    private String title;
    private String content;
    private int views = 0;

    private Long memberId;

    public Notice(String title, String content, Long memberId) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }

    public static Notice createOf(String title, String content, Long memberId) {
        return new Notice(title, content, memberId);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
