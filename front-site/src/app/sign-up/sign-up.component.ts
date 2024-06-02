import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User, UserService } from '../service/user.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {
  user: User = {userId: 0, username: '', password: '', email: '', fullName: '', avatar: '', balance: 0 };
  formSubmitted: boolean = false; 
  errorMessage: string = '';
  usernameExists: boolean = false;
  emailExists: boolean = false;

  constructor(private userService: UserService, private router: Router) { }

  register() {
    this.errorMessage = '';
    this.usernameExists = false;
    this.emailExists = false;

    console.log("User = ", this.user);
    if (this.isFormValid()) {
      this.userService.getAllUsers().subscribe(users => {
        console.log(users);
        const usernameExists = users.some(u => u.username === this.user.username);
        const emailExists = users.some(u => u.email === this.user.email);

        if (usernameExists) {
          this.errorMessage = 'Username already exists.';
        } else if (emailExists) {
          this.errorMessage = 'Email already exists.';
        } else {
          this.userService.createUser(this.user).subscribe(
            response => {
              console.log('User registered successfully:', response);
              this.router.navigate([`/profil/${response.userId}`]);
            },
            error => {
              console.error('Error registering user:', error);
              this.errorMessage = 'Registration failed. Please try again.';
            }
          );
        }
      });
    } else {
      this.errorMessage = 'Please fill in all required fields correctly.';
    }
  }

  isFormValid(): boolean {
    return this.user.username !== '' &&
           this.user.password !== '' &&
           this.user.password.length >= 8 &&
           this.user.email !== '' &&
           this.user.fullName !== '' &&
           this.user.balance > 0;
  }

  goToHome() {
    this.router.navigate(['/']); 
  }

  goToLogin() {
    this.router.navigate(['/login']); 
  }
}
