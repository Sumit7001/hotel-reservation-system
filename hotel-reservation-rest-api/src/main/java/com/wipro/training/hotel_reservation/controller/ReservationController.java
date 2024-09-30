package com.wipro.training.hotel_reservation.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.training.hotel_reservation.model.Reservation;
import com.wipro.training.hotel_reservation.model.Room;
import com.wipro.training.hotel_reservation.model.User;
import com.wipro.training.hotel_reservation.repository.RoomRepository;
import com.wipro.training.hotel_reservation.repository.UserRepository;
import com.wipro.training.hotel_reservation.service.ReservationService;
import com.wipro.training.hotel_reservation.service.RoomService;

/**
*Author  :Sumit Kumar Pal
*Date    :17-Sept-2024
*Time    :6:01:57 pm
*Project :
*/
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/reserve")
public class ReservationController {
	
	  @Autowired
	  private ReservationService reservationService;
	  @Autowired
	  private RoomRepository roomrepo;
	  
	  @Autowired
	  private UserRepository userRepository;
	   
	 /* @PostMapping
	  public ResponseEntity<Reservation> createReservation(@Validated @RequestBody Reservation reservation) {
	      // Validate and save the reservation
	      Reservation createdReservation = reservationService.save(reservation);
	      return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
	  }*/
	  @PostMapping
	  public ResponseEntity<Reservation> createReservation(@Validated @RequestBody Reservation reservation) {
	      User user = userRepository.findByUsername(reservation.getUser().getUsername());
	      if (user == null) {
	          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	      }

	      Optional<Room> room = roomrepo.findById(reservation.getRoomId());
	      
	      reservation.setUser(user);
	      reservation.setRoomType(room.get().getType()); 
	      reservation.setStatus("BOOKED");
	      

	      Reservation savedReservation = reservationService.save(reservation);
	      return ResponseEntity.status(HttpStatus.CREATED).body(savedReservation);
	  }
	  
	  @GetMapping("/{id}")
	  public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
	    return ResponseEntity.ok(reservationService.getReservation(id));
	  }

	  @PutMapping("/{id}")
	  public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
	    return ResponseEntity.ok(reservationService.updateReservation(id, reservation));
	  }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<Void> cancelReservation(@PathVariable Long id) throws Exception {
	    reservationService.cancelReservation(id);
	    return ResponseEntity.noContent().build();
	  }

	  @GetMapping("/{username}/reservations")
	  public ResponseEntity<List<Reservation>> getUserReservations(@PathVariable String username) {
		  User user = userRepository.findByUsername(username);
	    return ResponseEntity.ok(reservationService.getUserReservations(user.getId()));
	  }
	}

