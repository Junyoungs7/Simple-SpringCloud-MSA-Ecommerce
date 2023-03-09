package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserServiceImpl;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {

    private final UserServiceImpl userService;
    private final Greeting greeting;

    @GetMapping("/health_check")
    public String status(){
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public String createUser(@RequestBody RequestUser requestUser){

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(requestUser, UserDto.class);
        userService.createUser(userDto);

        return "Create user method is called";
    }
}
