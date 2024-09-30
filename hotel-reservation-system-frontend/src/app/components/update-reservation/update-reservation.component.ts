import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReservationService } from '../../services/reservation.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-reservation',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './update-reservation.component.html',
  styleUrl: './update-reservation.component.css'
})
export class UpdateReservationComponent {

  reservation: any = {};
  errorMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private reservationService: ReservationService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const reservationId = this.route.snapshot.paramMap.get('id');
    if (reservationId) {
      this.reservationService.getReservation(+reservationId).subscribe(
        data => this.reservation = data,
        error => this.errorMessage = 'An error occurred while retrieving the reservation.'
      );
    }
  }

  updateReservation(id:number): void {
    this.reservationService.updateReservation(id,this.reservation).subscribe(
      response => {
        alert('Reservation updated successfully.');
        this.router.navigate(['/view-reservation']);
      },
      error => this.errorMessage = 'An error occurred while updating the reservation.'
    );

}
}
