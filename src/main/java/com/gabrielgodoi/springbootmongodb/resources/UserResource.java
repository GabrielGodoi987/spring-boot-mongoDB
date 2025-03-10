package com.gabrielgodoi.springbootmongodb.resources;

import com.gabrielgodoi.springbootmongodb.domain.User;
import com.gabrielgodoi.springbootmongodb.dto.UserDto;
import com.gabrielgodoi.springbootmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/{id}")
    public UserDto findById(@PathVariable String id){
        User user = this.userService.findById(id);
        return new UserDto(user);
    }

    @PostMapping
    public ResponseEntity<UserDto> insert(@RequestBody UserDto userDto){
        User user = this.userService.fromDto(userDto);
        user = this.userService.insert(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
