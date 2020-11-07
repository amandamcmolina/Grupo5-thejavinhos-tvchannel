package com.thejavinhos.tvchannel.controller;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.CreateProducer;
import com.thejavinhos.tvchannel.entity.Producer;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.entity.ReturnProducer;
import com.thejavinhos.tvchannel.entity.ReturnReserve;
import com.thejavinhos.tvchannel.service.ActorService;
import com.thejavinhos.tvchannel.service.ProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/producer")
@Api(value = "API REST producer")
public class ProducerController {

  @Autowired
  private ProducerService producerService;

  @PostMapping
  @ApiOperation(value = "Create new Producer    -      (permit: all)")
  public ResponseEntity<ReturnProducer> createProducer(@RequestBody CreateProducer producer) {
    return ResponseEntity.ok(producerService.saveProducer(producer));
  }

  @GetMapping("/{username}")
  @ApiOperation(value = "Return the producer's reserves   -      (permit: hole_admin and logged user)")
  public ResponseEntity<List<ReturnReserve>> listById(@PathVariable String username) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null &&
        auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
      return ResponseEntity.ok(producerService.reserveList(username));
    }
    throw new IllegalArgumentException("You are not a admin");
  }

}
