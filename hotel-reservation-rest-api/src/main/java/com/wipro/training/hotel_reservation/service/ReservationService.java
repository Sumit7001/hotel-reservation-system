package com.wipro.training.hotel_reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.training.hotel_reservation.model.Reservation;
import com.wipro.training.hotel_reservation.repository.ReservationRepository;

/**
*Author  :Sumit Kumar Pal
*Date    :17-Sept-2024
*Time    :6:04:27 pm
*Project :
*/
@Service
public class ReservationService {
  
  @Autowired
  private ReservationRepository rrepo;
  
  public Reservation save(Reservation reservation) {
    return rrepo.save(reservation);
  }

  public Reservation getReservation(Long id) {
    return rrepo.findById(id).orElse(null);
  }

  public Reservation updateReservation(Long id, Reservation reservation) {
	  Reservation existingReservation = rrepo.findById(id)
              .orElseThrow(() -> new RuntimeException("Reservation not found"));

      // Update the details of the existing reservation with the new values
      existingReservation.setRoomType(reservation.getRoomType());
      existingReservation.setCheckInDate(reservation.getCheckInDate());
      existingReservation.setCheckOutDate(reservation.getCheckOutDate());
      existingReservation.setStatus(reservation.getStatus());

      // Save the updated reservation
      return rrepo.save(existingReservation); 
  }

  public void cancelReservation(Long id) throws Exception {
    Optional<Reservation> reservationOptional = rrepo.findById(id);
        
        // Check if reservation exists
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            
            // Update reservation status to 'canceled' (assuming you have a status field)
            reservation.setStatus("canceled");
            
            // Save the updated reservation
            rrepo.save(reservation);
        } else {
            throw new Exception("Reservation with ID " + id + " not found.");
        }
  }

  public List<Reservation> getUserReservations(Long userId) {
    return rrepo.findByUserId(userId);
  }


}
