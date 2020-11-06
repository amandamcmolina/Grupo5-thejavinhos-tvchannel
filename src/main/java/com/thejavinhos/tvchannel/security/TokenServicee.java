package com.thejavinhos.tvchannel.security;

import com.thejavinhos.tvchannel.entity.MyUserDetails;
import com.thejavinhos.tvchannel.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServicee {

  @Value("${tv.jwt.expiration}")
  private String expiration;

  @Value("${tv.jwt.secret}")
  private String secret;

  public String gerarToken(Authentication authentication) {
//        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
    MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
    Date hoje = new Date();
    Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
    return Jwts.builder()
        .setIssuer("API da TV")
        .setSubject(myUserDetails.getId().toString())
        .setIssuedAt(hoje)
        .setExpiration(dataExpiracao)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public boolean isTokenValido(String token) {
    try {
      Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }

  }

  public Long getIdUser(String token) {
    Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    return Long.parseLong(claims.getSubject());
  }
}
