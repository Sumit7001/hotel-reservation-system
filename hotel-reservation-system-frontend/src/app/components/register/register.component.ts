import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink, RouterModule } from '@angular/router';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule,CommonModule,RouterModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registrationForm: FormGroup;
  errorMessage: string = '';
  message: string | null = null;
submission:boolean=false;
submitted:boolean=false;
  constructor(private fb: FormBuilder, private userService: UserService,private router:Router) {
    this.registrationForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required,Validators.email]],
      phoneNumber: ['', Validators.required],
      username: ['',[Validators.required,Validators.minLength(6)]],
      
      address: ['', Validators.required]
      // Exclude cardType, bankName, accountNumber, ifscCode if not needed for registration
    });
  }

  onSubmit(): void {   
    this.submitted = true;
    if (this.registrationForm.valid) {
      this.userService.register(this.registrationForm.value).subscribe(
        response => {
          console.log('User registered successfully!', response);
          this.message = 'registration successfull!.Redirecting ...';
          this.submission=true;
          setTimeout(() => {
            this.router.navigate(['/login']); // Navigate to login component
          }, 3000);
        },
        error => {
          console.error('Error registering user:', error);
          this.errorMessage = 'An error occurred during registration. Please try again.';
        }
      );
    }
  }
  }
