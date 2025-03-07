package com.gabrielgodoi.springbootmongodb.resources;

import com.gabrielgodoi.springbootmongodb.domain.User;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        User maria = new User("1001", "Maria Brown", "maria@gmail.com");
        User alex = new User("1002", "Alex Green", "alex@gmail.com");
        return ResponseEntity.ok().body(new ArrayList<>(Arrays.asList(maria, alex)));
    }
}
