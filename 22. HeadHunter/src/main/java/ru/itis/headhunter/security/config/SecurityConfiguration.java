package ru.itis.headhunter.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itis.headhunter.repositories.AccountsRepository;
import ru.itis.headhunter.repositories.JwtTokenBlackListRepository;
import ru.itis.headhunter.security.details.AccountUserDetailsService;
import ru.itis.headhunter.security.filters.LogoutFilter;
import ru.itis.headhunter.security.filters.TokenAuthenticationFilter;
import ru.itis.headhunter.security.filters.TokenAuthorizationFilter;
import ru.itis.headhunter.security.jwt.JwtProvider;

@EnableWebSecurity
@RequiredArgsConstructor
@Profile(value = {"dev"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final AccountUserDetailsService accountUserDetailsService;
    private final AccountsRepository accountsRepository;
    private final JwtProvider jwtProvider;
    private final JwtTokenBlackListRepository jwtTokenBlackListRepository;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        TokenAuthenticationFilter tokenAuthenticationFilter =
                new TokenAuthenticationFilter(authenticationManagerBean(), objectMapper, accountsRepository, jwtProvider);
        tokenAuthenticationFilter.setFilterProcessesUrl("/api/signIn/");

        TokenAuthorizationFilter tokenAuthorizationFilter = new TokenAuthorizationFilter(objectMapper, jwtProvider,
                jwtTokenBlackListRepository);

        LogoutFilter logoutFilter = new LogoutFilter(jwtTokenBlackListRepository);

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(tokenAuthenticationFilter);
        http.addFilterBefore(tokenAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(logoutFilter, TokenAuthorizationFilter.class);

        http.authorizeRequests()
                .antMatchers("/api/signIn/").permitAll()
                .antMatchers("/api/signUp/").permitAll()
                .antMatchers("/api/headhunter/confirm/**").permitAll()
                .antMatchers("/api/**").authenticated();
    }
}
