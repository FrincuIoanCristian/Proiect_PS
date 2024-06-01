import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { User, UserService } from '../../service/user.service';
import { __param } from 'tslib';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrl: './principal.component.css'
})
export class PrincipalComponent implements OnInit {
  user: User | undefined;
  showDropdown = false;
  notificationCount = 0;

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const userId = +params['id']; 
      this.userService.getUserById(userId).subscribe(
        user => {
          this.user = user;
          console.log('Detalii utilizator:', this.user);
        },
        error => {
          console.error('Eroare la obținerea detalilor utilizatorului:', error);
        }
      );
    });
  }


  viewUserData(): void {
    const userId = this.user?.userId;
    this.router.navigate([`/profil/date/${userId}`]);
  }

  subscriptionUser(): void{
    const userId = this.user?.userId;
    this.router.navigate([`/profil/subscription/${userId}`]);
  }

  toggleDropdown(): void {
    this.showDropdown = !this.showDropdown;
  }
}
