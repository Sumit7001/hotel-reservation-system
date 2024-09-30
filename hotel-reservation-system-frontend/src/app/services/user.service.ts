import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private username: string | null = null;

  setUsername(username: string): void {
    this.username = username;
  }

  getUsername(): string | null {
    return this.username;
  }
  private apiUrl = 'http://localhost:8087/user';

  constructor(private http: HttpClient) { }

  // Register a new user
  register(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

  // Login user with username and password
  login(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, user);
  }

  // Find user by username
  findUserByUsername(username: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login/${username}`,{});
  }
}
