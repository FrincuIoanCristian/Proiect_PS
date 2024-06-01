import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-panel',
  templateUrl: './panel.component.html',
  styleUrl: './panel.component.css'
})
export class PanelComponent {
  username: string = '';
  password: string = '';
  type: string = '';

  constructor(private router: Router) {}
  
  goToHome() {
    this.router.navigate(['/home']);
  }

  login() {
    if (this.username === 'admin' && this.password === '12') {
      this.router.navigate(['/admin']);
    } else {
      if(this.username !== 'admin'){
        this.type = 'Username';
      }else if(this.password !== 'password123'){
        this.type = 'Password';
      }
    }
  }

  
}
