import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user: User = {userId: 0, username: '', password: '', email: '', fullName: '', avatar: '', balance: 0 };
  message : string = '';

  constructor(private userService: UserService, private router: Router) {}

  login(): void {
    this.userService.login(this.user).subscribe(
      user => {
        if (user) {
          console.log(user);
          const userId = user.userId;
          this.router.navigate([`/profil/${userId}`]);
        } else {
          this.message = 'Autentificare eșuată';
        }
      },
      error => {
        console.error('Eroare la autentificare:', error);
        this.message = 'Autentificare eșuată';
      }
    );
  }

  goToSignUp() {
    this.router.navigate(['/sign-up']);
  }

  goToHome() {
    this.router.navigate(['/home']);
  }
}
