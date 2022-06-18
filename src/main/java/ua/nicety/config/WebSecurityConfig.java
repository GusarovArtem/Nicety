package ua.nicety.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/users/login", "/users/logout", "/users/registration", "/static/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(login -> login
                        .loginPage("/users/login"))
                .rememberMe()
                .and()
                .logout(logout -> logout
                        .logoutUrl("/users/logout")
                        .logoutSuccessUrl("/users/login")
                        .deleteCookies("JSESSIONID"));
    }

}