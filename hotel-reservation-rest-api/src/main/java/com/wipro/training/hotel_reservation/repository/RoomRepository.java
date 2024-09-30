package com.wipro.training.hotel_reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.training.hotel_reservation.model.Room;

/**
*Author  :Sumit Kumar Pal
*Date    :17-Sept-2024
*Time    :10:55:11 pm
*Project :
*/
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByType(String type);
}

/*
 * import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wipro.training.hotelres.model.Room;

public interface RoomRepository extends MongoRepository<Room, String> {

	List<Room> findByRoomType(String roomType);
}
 */
