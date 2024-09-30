import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { ReservationService } from '../../services/reservation.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-reservation',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './view-reservation.component.html',
  styleUrl: './view-reservation.component.css'
})
export class ViewReservationComponent {

  reservations: any[] = [];
  username: string | null = '';
  errorMessage: string = '';

  constructor(
    private authService: UserService,
    private reservationService: ReservationService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.username = this.authService.getUsername();
    if (this.username) {
      this.loadReservations();
    } else {
      this.errorMessage = 'Username not found.';
    }
  }

  loadReservations(): void {
    if (this.username) {
      this.reservationService.getUserReservations(this.username).subscribe(
        data => this.reservations = data,
        error => this.errorMessage = 'An error occurred while retrieving reservations.'
      );
    }
  }

  updateReservation(reservation: any): void {
    // Navigate to update component or show update form
    // e.g., this.router.navigate(['/reservations/update', reservation.id]);
    this.router.navigate(['/update-reservation', reservation.id]);
  }

  cancelReservation(id: number): void {
    if (confirm('Are you sure you want to cancel this reservation?')) {
      this.reservationService.deleteReservation(id).subscribe(
        () => this.loadReservations(), // Reload reservations after successful cancellation
        error => this.errorMessage = 'An error occurred while canceling the reservation.'
      );
    }
  }
}
