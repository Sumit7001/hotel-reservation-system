import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { RoomService } from '../../services/room.service';
import { ReservationService } from '../../services/reservation.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-rooms',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rooms.component.html',
  styleUrl: './rooms.component.css'
})
export class RoomsComponent {
  availabilityForm: FormGroup;
  availableRooms: any;

  constructor(private fb: FormBuilder, private roomService:RoomService,private reservationservice:ReservationService ,private route:ActivatedRoute,private router:Router) {
    this.availabilityForm = this.fb.group({
      startDate: [''],
      endDate: [''],
      roomType: ['']
    });
  }

  checkAvailability() {
    const { startDate, endDate, roomType } = this.availabilityForm.value;
    this.roomService.checkAvailability(startDate, endDate, roomType).subscribe(response => {
      this.availableRooms = response;
    });
  }
  
  reservations: any[] = []; // List of reservations should be an array
  reservation: any = {}; // Single reservation should be an object
  
  submissionError: boolean = false;

  

  ngOnInit(): void {
  
      this.roomService.getRoom().subscribe(data => {
        console.log(data);
        this.reservation = data; // Assign the reservation object
      });
    }
    bookRoom(roomId: number) {
      // Navigate to the reservation page with the room ID
      this.router.navigate(['/reservation', roomId]);
    }


  }

