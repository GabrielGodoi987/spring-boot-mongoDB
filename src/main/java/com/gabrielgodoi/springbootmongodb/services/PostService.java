package com.gabrielgodoi.springbootmongodb.services;

import com.gabrielgodoi.springbootmongodb.domain.Post;
import com.gabrielgodoi.springbootmongodb.repository.PostRepository;
import com.gabrielgodoi.springbootmongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    public Post findById(String id) {
        Optional<Post> post = this.postRepository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Object wasn't found"));
    }

    public List<Post> findByContainingTitle(String text) {
        return this.postRepository.findByTitle(text);
    }

}
