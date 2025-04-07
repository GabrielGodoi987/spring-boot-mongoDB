package com.gabrielgodoi.springbootmongodb.config;

import com.gabrielgodoi.springbootmongodb.domain.Post;
import com.gabrielgodoi.springbootmongodb.domain.User;
import com.gabrielgodoi.springbootmongodb.dto.AuthorDto;
import com.gabrielgodoi.springbootmongodb.dto.CommentDto;
import com.gabrielgodoi.springbootmongodb.repository.PostRepository;
import com.gabrielgodoi.springbootmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
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

        User gabriel = new User(null, "Gabriel Godoi", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        User john = new User(null, "John Macarktney", "john@gmail.com");

        this.userRepository.saveAll(Arrays.asList(gabriel, alex, bob, john));


        Post post1 = new Post(null, simpleDateFormat.parse("21/03/2018").toInstant(), "Partiu viagem", "viagem de negócios para bhali", new AuthorDto(gabriel));
        Post post2 = new Post(null, simpleDateFormat.parse("25/03/2018").toInstant(), "Reunião", "Hoje fizemos um contrato histórico, um milhão de reais hahaha", new AuthorDto(gabriel));
        CommentDto commentDto1 = new CommentDto("Boa Viagem", simpleDateFormat.parse("21/03/2018").toInstant(), new AuthorDto(alex));
        CommentDto commentDto2 = new CommentDto("Vamos pra cima na reunião de hoje meu caro!", simpleDateFormat.parse("25/03/2018").toInstant(), new AuthorDto(bob));
        post1.getCommentDto().addAll(Arrays.asList(commentDto1, commentDto2));

        CommentDto commentDto3 = new CommentDto("Parabéns por mais uma conquista", simpleDateFormat.parse("25/03/2018").toInstant(), new AuthorDto(bob));
        CommentDto commentDto4 = new CommentDto("Parabéns meu caro!", simpleDateFormat.parse("25/03/2018").toInstant(), new AuthorDto(john));
        post2.getCommentDto().addAll(Arrays.asList(commentDto3, commentDto4));
        this.postRepository.saveAll(Arrays.asList(post1, post2));

        gabriel.getPosts().addAll(Arrays.asList(post1, post2));
        this.userRepository.save(gabriel);
    }
}
