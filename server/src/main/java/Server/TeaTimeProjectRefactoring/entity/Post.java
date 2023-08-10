package Server.TeaTimeProjectRefactoring.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : 이대겸
 * @desc : Post 엔티티
 */
@Entity
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    private String content;
    private int views = 0;
    private final LocalDateTime createAt = LocalDateTime.now();
    private LocalDateTime modifiedAt = LocalDateTime.now();
    private Boolean deleteAt = false;

    private Long memberId;

    public Post(String title, String content, Long memberId) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }

    public static Post createOf(String title, String content, Long memberId) {
        return new Post(title, content, memberId);
    }

    public void update(Long postId, String title, String content) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.modifiedAt = LocalDateTime.now();
    }
}
