package com.thejavinhos.tvchannel.controller;

import com.thejavinhos.tvchannel.entity.LoginForm;
import com.thejavinhos.tvchannel.security.TokenServicee;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private TokenServicee tokenService;

  @PostMapping
  @Transactional

  @ApiOperation(value = "Login     -     (permit: all)")
  public ResponseEntity<TokenDto> autenticar(@RequestBody @Validated LoginForm form) {
    UsernamePasswordAuthenticationToken dadosLogin = form.converter();

    try {
      Authentication authentication = authManager.authenticate(dadosLogin);
      String token = tokenService.gerarToken(authentication);
      return ResponseEntity.ok(new TokenDto(token, "Bearer"));
    } catch (AuthenticationException e) {
      return ResponseEntity.badRequest().build();
    }
  }

}
