package com.github.hpdias.base.api.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.github.hpdias.base.api.security.services.UserPrinciple;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtProvider {
	
	@Autowired
    private HttpServletRequest requestC;

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${hpdias.api.jwtSecret}")
    private String jwtSecret;

    @Value("${hpdias.api.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {
    	

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
        
        return Jwts.builder()
		                .setSubject((userPrincipal.getUsername()))
		                .setId(userPrincipal.getId().toString())
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
		                .signWith(SignatureAlgorithm.HS512, jwtSecret)
		                .compact();
    }
    
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Assinatura JWT inválida -> Mensagem: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("JWT token inválido -> Mensagem: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("JWT token expirado -> Mensagem: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token não suportado -> Mensagem: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims está vazia -> Mensagem: {}", e);
        }
        
        return false;
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(jwtSecret)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }
    
    public long getIdFromJwtToken() {
    	
    	String token = "";
    	
    	String authHeader = requestC.getHeader("Authorization");
    	
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
        	token = authHeader.replace("Bearer ","");
        }
    	
    	String id = Jwts.parser()
		                .setSigningKey(jwtSecret)
		                .parseClaimsJws(token)
		                .getBody().getId();
    	
    	return Long.parseLong(id);
    }
    
}