package com.wipro.training.hotel_reservation.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wipro.training.hotel_reservation.model.Reservation;

/**
*Author  :Sumit Kumar Pal
*Date    :17-Sept-2024
*Time    :6:05:57 pm
*Project :
*/

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	 List<Reservation> findByUserId(Long userId);
	 /*List<Reservation> findByRoomIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
	            Long roomId, LocalDate endDate, LocalDate startDate);*/

	 @Query("SELECT r FROM Reservation r WHERE (r.roomType = :roomType AND r.checkInDate BETWEEN :startDate AND :endDate) OR (r.roomType = :roomType AND r.checkOutDate BETWEEN :startDate AND :endDate)")
	 List<Reservation> findByRoomTypeAndCheckInDateBetweenOrCheckOutDateBetween(
	     @Param("roomType") String roomType, 
	     @Param("startDate") LocalDate startDate, 
	     @Param("endDate") LocalDate endDate
	 );


}
