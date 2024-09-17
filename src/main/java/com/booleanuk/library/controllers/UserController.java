package com.booleanuk.library.controllers;

import com.booleanuk.library.models.Loan;
import com.booleanuk.library.repository.LoanRepository;
import com.booleanuk.library.models.User;
import com.booleanuk.library.repository.UserRepository;
import com.booleanuk.library.DTO.UserDTO;
import com.booleanuk.library.DTO.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    UserMapper userMapper;

    @PostMapping
    public UserDTO addUser(@Valid @RequestBody UserDTO userDTO, BindingResult result){

        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please verify that all the required fields are entered." + result.getAllErrors());
        }

        User user = userMapper.toEntity(userDTO);
        User savedUser = this.userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        List<UserDTO> userDTOS = new ArrayList<>();
        this.userRepository.findAll()
                .forEach(user ->
                        userDTOS.add(userMapper.toDTO(user)));

        return userDTOS;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable (name = "id") int id){

        User user = this.userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));

        return userMapper.toDTO(user);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable (name = "id") int id, @RequestBody UserDTO userDTO){

        User updatedUser = userMapper.toEntity(userDTO);

        return this.userRepository.findById(id).map(user -> {
            if (updatedUser.getUsername() != null)
                user.setUsername(updatedUser.getUsername());

            User savedUser = this.userRepository.save(user);

            return userMapper.toDTO(savedUser);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with the provided id does not exist"));
    }

    @DeleteMapping("/{id}")
    public UserDTO deleteUser(@PathVariable (name = "id") int id){
        return this.userRepository.findById(id).map(user -> {
            this.userRepository.delete(user);
            return userMapper.toDTO(user);
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video game with the provided id does not exist"));
    }

    @GetMapping("{id}/loans")
    public List<Loan> getRentedVideoGames(@PathVariable (name = "id") int id) {
        User user = this.userRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "No users with the provided ID were found."));

        return this.loanRepository.findLoansByUser(user);
    }
}
