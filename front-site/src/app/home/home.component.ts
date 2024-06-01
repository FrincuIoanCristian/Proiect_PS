import { Component } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private router: Router) { }

  goToSignUp() {
    this.router.navigate(['/sign-up']);
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }

  goToAdmin() {
    this.router.navigate(['/admin-panel']);
  }

  goToView() {
    this.router.navigate(['/view']);
  }


}
