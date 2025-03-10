package com.gabrielgodoi.springbootmongodb.dto;

import java.io.Serializable;
import java.time.Instant;

public class CommentDto implements Serializable {
    private String text;
    private Instant date;
    private AuthorDto authorDto;

    public CommentDto(){}

    public CommentDto(String text, Instant date, AuthorDto authorDto) {
        this.text = text;
        this.date = date;
        this.authorDto = authorDto;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public void setAuthorDto(AuthorDto authorDto) {
        this.authorDto = authorDto;
    }
}
