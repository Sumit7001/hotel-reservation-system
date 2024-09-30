import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private apiUrl = 'http://localhost:8087/reserve';
  constructor(private http: HttpClient) { }

  createReservation(reservation: any): Observable<any> {
    
    return this.http.post(this.apiUrl, reservation);
  }

  getReservation(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  updateReservation(id: number, reservation: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, reservation);
  }

  deleteReservation(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  getUserReservations(username: string): Observable<any> {
    return this.http.get(`http://localhost:8087/reserve/${username}/reservations`);
  }
}
