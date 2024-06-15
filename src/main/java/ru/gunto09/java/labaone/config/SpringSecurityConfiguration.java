package ru.gunto09.java.labaone.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.gunto09.java.labaone.model.UserAuthority;

@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(expressionInterceptUrlRegistry ->
                        expressionInterceptUrlRegistry
                                .requestMatchers("/registration", "/login").permitAll()
                                .requestMatchers(HttpMethod.POST,"/jokes").hasAuthority(UserAuthority.PLACE_JOKE.getAuthority())
                                .requestMatchers(HttpMethod.GET, "/jokes/**").hasAuthority(UserAuthority.PLACE_JOKE.getAuthority())
                                .requestMatchers(HttpMethod.PUT, "/jokes/**").hasAuthority(UserAuthority.MANAGE_JOKE.getAuthority())
                                .requestMatchers(HttpMethod.DELETE, "/jokes/**").hasAuthority(UserAuthority.MANAGE_JOKE.getAuthority())
                                .requestMatchers(HttpMethod.GET, "/call/**").hasAuthority(UserAuthority.PLACE_JOKE.getAuthority())
                                .anyRequest().hasAuthority(UserAuthority.PLACE_JOKE.getAuthority()))
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
