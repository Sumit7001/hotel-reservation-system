import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { ReservationService } from '../../services/reservation.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RoomService } from '../../services/room.service';

@Component({
  selector: 'app-reservation',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule,FormsModule],
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css'] // Fixed typo (styleUrl -> styleUrls)
})
export class ReservationComponent implements OnInit {
  reservationForm: FormGroup;
  submission: boolean = false;
  roomId: any | null = '';
  errorMessage: string = '';
room:any="";
  constructor(
    private fb: FormBuilder,
    private reservationService: ReservationService,
    private router: Router,
    private route: ActivatedRoute,
    private roomservice:RoomService

  ) {
    // Initialize the reservation form with form controls
    this.reservationForm = this.fb.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      address: ['', Validators.required],
      checkInDate: ['', Validators.required],
      checkOutDate: ['', Validators.required],
      //roomType:['',Validators.required],
      status: ['BOOKED']
    });
  }

  ngOnInit(): void {
    // Capture the roomId from route parameters (if necessary)
    this.route.paramMap.subscribe(params => {
      this.roomId = params.get('id');
    });
    if (this.roomId) {
      this.roomservice.getRoomById(this.roomId).subscribe({
        next: (roomdata) => {
          this.room = roomdata; // Assign the room data to the component's 'room' property
        },
        error: (err) => {
          console.error('Error fetching room data: ', err); // Handle any errors that occur
        },
        complete: () => {
          console.log('Room data fetched successfully!'); // Optional: log a message when fetching is complete
        }
      });
    }
  
}


  onSubmit(): void {
    // Check if the form is valid before submission
    if (this.reservationForm.valid) {
      const formData = this.reservationForm.value;

      // Construct the reservation object with form data
      const reservation = {
        user: {
          name: formData.name,
          username: formData.username,
          phone: formData.phone,
          email: formData.email,
          address: formData.address
        },
        //roomType: "any",
        roomId: this.roomId ? +this.roomId : 1, // Use roomId if provided, else default to 1
        checkInDate: formData.checkInDate,
        checkOutDate: formData.checkOutDate,
        status: formData.status
      };

      // Call service to create the reservation
      this.reservationService.createReservation(reservation).subscribe(
        response => {
          console.log('Reservation successful', response);
          this.submission = true;
          // Navigate to update/delete page with the username in the URL
          this.router.navigate(['/view-reservation']);
        },
        error => {
          console.error('Error making reservation', error);
          this.errorMessage = 'Error occurred while making the reservation. Please try again.';
          this.submission = false;
        }
      );
    } else {
      this.errorMessage = 'Please fill out the form correctly before submitting.';
    }
  }
}
