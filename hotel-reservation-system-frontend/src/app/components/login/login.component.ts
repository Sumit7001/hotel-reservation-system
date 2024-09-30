import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Route, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,FormsModule,RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  message:string='';
  errorMessage:string='';
  constructor(private authService: UserService, private router: Router) { }
  login(): void {
    // Ensure username is provided
    if (this.username) {
      this.authService.findUserByUsername(this.username).subscribe({
        next: (response) => {
          console.log(response);
          const userId = response.id;
          this.authService.setUsername(this.username);
          // Navigate to the reservation page with the user ID
          this.router.navigate(['/room-details']);
  
        
    
        
        },
        error: (err) => {
          console.error('Login error:', err);
          this.message="User not found. Please try again!";
        }
      });
    } else {
      this.errorMessage="Username is required";
    }
  }
}  