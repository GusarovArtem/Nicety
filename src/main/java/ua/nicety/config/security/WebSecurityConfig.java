package ua.nicety.config.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ua.nicety.service.UserService;

@EnableMethodSecurity
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private String loginMatcher = "/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers( "/oauth2/**", loginMatcher, "/logout", "/users/registration", "/static/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(login -> login
                        .loginPage(loginMatcher)
                        .defaultSuccessUrl("/main"))
                .rememberMe()
                .and()
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl(loginMatcher)
                        .deleteCookies("JSESSIONID"))
                .oauth2Login(config -> config
                        .loginPage(loginMatcher)
                        .defaultSuccessUrl("/main")
                        .userInfoEndpoint(userInfo -> userInfo.oidcUserService(userService))
                );

    }

}