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
 * @desc : Member 엔티티
 */
@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String email;
    private String password;
    private String userName;

    private final LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = null;
    private Boolean deletedAt = false;

    public Member(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    /* 정적 팩토리 메소드 패턴 */
    /**
     * 빌더랑 유사함
     * 파라미터를 정해놓고 이름을 지정하여 생성자를 생성할 수 있음
     */
    public static Member createOf(String email, String password, String userName) {
        return new Member(email, password, userName);
    }

    /* 엔티티 유틸 함수 */
    /**
     * update 함수
     */
    public void update(String email, String username) {
        this.email = email;
        this.userName = username;
        this.modifiedAt = LocalDateTime.now();
    }
}