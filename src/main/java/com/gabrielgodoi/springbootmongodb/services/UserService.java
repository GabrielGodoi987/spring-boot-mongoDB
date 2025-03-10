package com.gabrielgodoi.springbootmongodb.services;

import com.gabrielgodoi.springbootmongodb.domain.User;
import com.gabrielgodoi.springbootmongodb.dto.UserDto;
import com.gabrielgodoi.springbootmongodb.repository.UserRepository;
import com.gabrielgodoi.springbootmongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    public User findById(String id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User user){
        return this.userRepository.insert(user);
    }

    public User fromDto(UserDto userDto){
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
    }

    public void deleteUser(String id){
        this.findById(id);
        this.userRepository.deleteById(id);
    }
}
