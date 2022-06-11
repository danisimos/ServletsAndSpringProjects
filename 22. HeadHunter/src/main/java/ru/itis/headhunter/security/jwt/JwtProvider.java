package ru.itis.headhunter.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.itis.headhunter.exceptions.AccountNotFoundException;
import ru.itis.headhunter.models.Account;
import ru.itis.headhunter.repositories.AccountsRepository;
import ru.itis.headhunter.security.details.AccountUserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private Long expired;

    private final AccountsRepository accountsRepository;

    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        String email = decodedJWT.getSubject();

        Account account = accountsRepository.findByEmail(email).orElseThrow(() -> new AccountNotFoundException("no such account"));
        AccountUserDetails accountUserDetails = new AccountUserDetails(account);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(token, accountUserDetails, accountUserDetails.getAuthorities());

        return usernamePasswordAuthenticationToken;
    }

    public String getToken(HttpServletRequest request) {
        String tokenHeader = request.getHeader("Authorization");
        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            return tokenHeader.substring("Bearer ".length());
        }

        return null;
    }

    public boolean validate(String token) {
        DecodedJWT decodedJWT;

        try {
            decodedJWT = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        } catch(JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }

        return decodedJWT.getExpiresAt().after(new Date());
    }

    public String createToken(AccountUserDetails accountUserDetails) {
        return JWT.create()
                .withSubject(accountUserDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expired))
                .sign(Algorithm.HMAC256(secret));
    }
}
