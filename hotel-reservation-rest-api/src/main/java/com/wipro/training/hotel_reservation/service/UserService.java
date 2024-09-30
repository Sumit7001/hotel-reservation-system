package com.wipro.training.hotel_reservation.service;
/**
*Author  :Sumit Kumar Pal
*Date    :17-Sept-2024
*Time    :9:02:44 pm
*Project :
*/


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.wipro.training.hotel_reservation.model.User;
import com.wipro.training.hotel_reservation.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

   
    public User registerUser(User user) {
       
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public Optional<User> findById(Long id) {
    	return userRepository.findById(id);
    }
}
