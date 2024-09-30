package com.wipro.training.hotel_reservation.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*Author  :Sumit Kumar Pal
*Date    :17-Sept-2024
*Time    :10:52:37 pm
*Project :
*/
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Room {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	
	    private String type; // e.g., "Single", "Double", "Suite"


	    private String description;

	    
	    private double price;

	  
	    private String imageUrl;
	    
	    //@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	   // @JsonManagedReference
	   // private List<Reservation> reservations;
}

