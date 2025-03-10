package com.gabrielgodoi.springbootmongodb.config;

import com.gabrielgodoi.springbootmongodb.domain.Post;
import com.gabrielgodoi.springbootmongodb.domain.User;
import com.gabrielgodoi.springbootmongodb.dto.AuthorDto;
import com.gabrielgodoi.springbootmongodb.repository.PostRepository;
import com.gabrielgodoi.springbootmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        this.userRepository.deleteAll();
        this.postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        this.userRepository.saveAll(Arrays.asList(maria, alex, bob));


        Post post1 = new Post(null, simpleDateFormat.parse("21/03/2018").toInstant(), "Partiu viagem", "viagem de negócios para bhali", new AuthorDto(maria));
        Post post2 = new Post(null, simpleDateFormat.parse("25/03/2018").toInstant(), "Reunião", "Hoje fizemos um contrato histórico, um milhão de reais hahaha", new AuthorDto(maria));

        this.postRepository.saveAll(Arrays.asList(post1, post2));
    }
}
