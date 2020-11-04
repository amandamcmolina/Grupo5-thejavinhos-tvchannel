package com.thejavinhos.tvchannel.controller;


import com.thejavinhos.tvchannel.entity.LoginForm;
import com.thejavinhos.tvchannel.entity.User;
import com.thejavinhos.tvchannel.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/user")
@Api(value="API REST User")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ApiOperation(value= "Create new geral user")
    public ResponseEntity<User> createNewUser(@RequestBody User user){
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }
}
