package Server.TeaTimeProjectRefactoring.entity;

import Server.TeaTimeProjectRefactoring.entity.constant.Role;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Counselor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long counselorId;

    private String email;
    private String password;
    private String counselorName;
    private String birth;
    private String graduated;
    private String profile;
    private String career;
    private String introduce;
    private String expertiseField;
    private Role role;

    private final LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = null;
    private Boolean deletedAt = false;

    public Counselor(String email, String password, String counselorName,
        String birth, String graduated, String profile, String career, String introduce,
        String expertiseField) {
        this.email = email;
        this.password = password;
        this.counselorName = counselorName;
        this.birth = birth;
        this.graduated = graduated;
        this.profile = profile;
        this.career = career;
        this.introduce = introduce;
        this.expertiseField = expertiseField;
    }

    public static Counselor createOf(String email, String password, String counselorName,
        String birth, String graduated, String profile, String career, String introduce,
        String expertiseField) {
        return new Counselor(email, password, counselorName, birth, graduated, profile, career, introduce, expertiseField);
    }

    public void update(String email, String password, String counselorName,
        String birth, String graduated, String profile, String career, String introduce,
        String expertiseField) {
        this.email = email;
        this.password = password;
        this.counselorName = counselorName;
        this.birth = birth;
        this.graduated = graduated;
        this.profile = profile;
        this.career = career;
        this.introduce = introduce;
        this.expertiseField = expertiseField;
    }
}