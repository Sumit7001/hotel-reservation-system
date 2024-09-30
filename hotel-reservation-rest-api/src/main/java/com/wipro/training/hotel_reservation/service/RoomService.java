package com.wipro.training.hotel_reservation.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.training.hotel_reservation.model.Reservation;
import com.wipro.training.hotel_reservation.model.Room;
import com.wipro.training.hotel_reservation.repository.ReservationRepository;
import com.wipro.training.hotel_reservation.repository.RoomRepository;

/**
*Author  :Sumit Kumar Pal
*Date    :17-Sept-2024
*Time    :10:56:55 pm
*Project :
*/
@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
	  @Autowired
	    private ReservationRepository reservationRepository;

	  public List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate, String roomType) {
		    List<Room> rooms = roomRepository.findByType(roomType);
		    List<Room> availableRooms = new ArrayList<>();
		    
		    for (Room room : rooms) {
		      if (!isRoomBooked(room, startDate, endDate)) {
		        availableRooms.add(room);
		      }
		    }
		    
		    return availableRooms;
		  }
	  private boolean isRoomBooked(Room room, LocalDate startDate, LocalDate endDate) {
		    List<Reservation> reservations = reservationRepository.findByRoomTypeAndCheckInDateBetweenOrCheckOutDateBetween(
		      room.getType(), startDate, endDate
		    );
		    
		    return !reservations.isEmpty();
		  }
	    
	    public Room add(Room room) {
	    	return roomRepository.save(room);
	    }
	    
	    public List<Room> getAllRoom(){
	    	return roomRepository.findAll();
	    }
	    public Optional<Room> getByRoomId(long id) {
	    	return roomRepository.findById(id);
	    }
}
/*
 * import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.training.hotelres.model.Reservation;
import com.wipro.training.hotelres.model.Room;
import com.wipro.training.hotelres.repository.ReservationRepository;
import com.wipro.training.hotelres.repository.RoomRepository;

@Service
public class RoomService {
  
  @Autowired
  private RoomRepository roomRepository;
  @Autowired
  private ReservationRepository reservationRepository;
  
  public Room createRoom(Room room) {
      // Save the room to the database
      return roomRepository.save(room);
  }
  
  
  
  
}
 */


