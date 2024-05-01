package com.br.varsolutions.infraestructure.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.io.Serializable;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;


        public static final long JWT_TOKEN_EXPIRES = 120000;
    private String secret = "7c1fdd0350056ef0d2052364aa7d826c035bed93c2461a250339c05ba99f69aaf9302ab7dd1bc358eca2b8f57cdc201b117109d7a3b57fda0bb49fb7851eea34";

    //retorna o username do token do  jwt
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    //Retorna varios objetos possiveis de varios tipos possiveis
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
//retorna expiration fate do token jwt
public Date getExpirationFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
}

//Para retornar qualquer informação do token, e pra isso nos vamos precsar do secret
private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
}

private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationFromToken(token);
        return expiration.before(new Date());
}

//gerar Token
public String generateToken(String clientId){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims,clientId);
}

    private String doGenerateToken(Map<String, Object> claims, String clientId) {
        return Jwts.builder().setClaims(claims).setSubject(clientId).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_EXPIRES))
                .signWith(SignatureAlgorithm.HS512,secret).compact();
    }

    //Valida o Token
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }


}
