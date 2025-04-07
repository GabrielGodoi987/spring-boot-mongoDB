package com.gabrielgodoi.springbootmongodb.resources;

import com.gabrielgodoi.springbootmongodb.domain.Post;
import com.gabrielgodoi.springbootmongodb.resources.util.URL;
import com.gabrielgodoi.springbootmongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        List<Post> postList = this.postService.findAll();
        return ResponseEntity.ok().body(postList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = this.postService.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByContainingTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> postList = this.postService.findByContainingTitle(text);
        return ResponseEntity.ok().body(postList);
    }


    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate
    ) {
        text = URL.decodeParam(text);
        Date min = URL.dateConversor(minDate, new Date(0L));
        Date max = URL.dateConversor(maxDate, new Date());
        List<Post> postList = this.postService.fullSearch(text, min, max);
        return ResponseEntity.ok().body(postList);
    }
}
