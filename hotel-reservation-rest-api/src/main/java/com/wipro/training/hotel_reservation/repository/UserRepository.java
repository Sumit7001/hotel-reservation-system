package com.wipro.training.hotel_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.training.hotel_reservation.model.User;

/**
*Author  :Sumit Kumar Pal
*Date    :17-Sept-2024
*Time    :9:02:28 pm
*Project :
*/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}