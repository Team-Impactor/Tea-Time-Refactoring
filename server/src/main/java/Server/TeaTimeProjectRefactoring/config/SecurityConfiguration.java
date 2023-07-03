package Server.TeaTimeProjectRefactoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    /*
    HTTP 보안 구성 기본
    HttpSecutiry를 통해 HTTP 요청에 대한 보안 설정을 구성
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .headers().frameOptions().sameOrigin()
            .and()
            .csrf().disable()   // CSRF(Cross-Site Request Forgery) 공격에 대한 Spring Security에 대한 설정을 비활성화
            .formLogin()    // 기본적인 인증 방법을 폼 로그인 방식으로 지정
            .loginPage("/auths/login-form") // loginPage()의 파라미터인 "/auths/login-form"로 AuthController의 loginForm() 핸들러 메서드에 요청을 전송
            .loginProcessingUrl("/process-login")   // 로그인 인증 요청을 수행할 요청 URL을 지정
            .failureUrl("/auths/login-form?error")  // 로그인 인증에 실패할 경우 리다이렉트될 URL 지정
            .and()  // Spring Security 보안 설정을 메서드 체인 형태로 구성
            // 로그아웃 설정
            .logout()   // 로그아웃 설정을 위한 LogoutConfigurer를 리턴
            .logoutUrl("/logout")   // 로그아웃을 수행하기 위한 request URL을 지정
            .logoutSuccessUrl("/")
            .and()
            // 권한이 없는 사용자가 특정 request URI에 접근할 경우 발생하는 403(Forbidden) 에러를 처리하기 위한 페이지를 설정
            .exceptionHandling().accessDeniedPage("/auths/access-denied")
            .and()
            // 사용자의 Role 별로 request URI에 접근 권한이 부여
            .authorizeHttpRequests(authorize -> authorize
                .antMatchers("/orders/**").hasRole("ADMIN")
                .antMatchers("/members/my-page").hasRole("USER")
                // 더 구체적인 URL 경로부터 접근 권한을 부여한 다음 덜 구체적인 URL 경로에 접근 권한을 부여
                .antMatchers("/**").permitAll()
            );

        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
