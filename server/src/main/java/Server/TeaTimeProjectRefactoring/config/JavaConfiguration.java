package Server.TeaTimeProjectRefactoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class JavaConfiguration {

    @Bean
    public MemberService dbMemberService(PasswordEncoder passwordEncoder) {
        return new DBMemberService(passwordEncoder);
    }

}
