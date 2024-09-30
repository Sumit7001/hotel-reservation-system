import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoomService {
  private apiUrl = 'http://localhost:8087/rooms';

  constructor(private http: HttpClient) { }

  checkAvailability(startDate: string, endDate: string, roomType: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/availability?startDate=${startDate}&endDate=${endDate}&roomType=${roomType}`);
  }
  getRoom(): Observable<any> {
    return this.http.get(`${this.apiUrl}`);
  }
  getRoomById(id:number):Observable<any>{
    return this.http.get(this.apiUrl+'/'+id);
  }
}

