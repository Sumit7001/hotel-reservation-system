package com.wipro.training.hotel_reservation.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.training.hotel_reservation.model.Room;
import com.wipro.training.hotel_reservation.service.RoomService;

/**
*Author  :Sumit Kumar Pal
*Date    :17-Sept-2024
*Time    :10:58:55 pm
*Project :
*/
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public Room add(@Validated @RequestBody Room room) {
    	return roomService.add(room);
    	}
    @GetMapping("/availability")
    public ResponseEntity<?> checkRoomAvailability(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate,
            @RequestParam("roomType") String roomType) {
        List<Room> availableRooms = roomService.findAvailableRooms(startDate, endDate, roomType);
        if(availableRooms.size()>0) {
        	 return ResponseEntity.ok(availableRooms);
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No rooms available  within these date.");
        }
       
    }
    @GetMapping
    public List<Room> getAll(){
    	return roomService.getAllRoom();
    }
     
    @GetMapping("/{id}")
    public Optional<Room> getById(@PathVariable long id) {
        return roomService.getByRoomId(id);
    }
}
/*
 * import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.training.hotelres.model.Room;
import com.wipro.training.hotelres.service.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
  
  @Autowired
  private RoomService roomService;
  
  @PostMapping("/create")
  public Room createRoom(@RequestBody Room room) {
    return roomService.createRoom(room);
  }
  
  @GetMapping("/availability")
  public ResponseEntity<List<Room>> getAvailableRooms(
      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
      @RequestParam String roomType
  ) {
      return ResponseEntity.ok(roomService.getAvailableRooms(startDate, endDate, roomType));
  }
}


 */


