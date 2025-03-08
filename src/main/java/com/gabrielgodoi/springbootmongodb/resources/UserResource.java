package com.gabrielgodoi.springbootmongodb.resources;

import com.gabrielgodoi.springbootmongodb.domain.User;
import com.gabrielgodoi.springbootmongodb.dto.UserDto;
import com.gabrielgodoi.springbootmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        List<User> userList = this.userService.findAll();
        List<UserDto> userDtoList = userList.stream().map(UserDto::new).toList();
        return ResponseEntity.ok().body(userDtoList);
    }
}
