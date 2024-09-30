package com.wipro.training.hotel_reservation.model;



import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *Author  :Sumit Kumar Pal
 *Date    :17-Sept-2024
 *Time    :5:56:49 pm
 *Project :
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   //@ManyToOne
   // @JoinColumn(name = "room_id", nullable = false)
  // @JsonBackReference
   // private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference// This is the foreign key column
    private User user;

    private long roomId;
    private String roomType;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status;
	// e.g., 'BOOKED', 'CANCELLED'

	// Getters and Setters
	

}

