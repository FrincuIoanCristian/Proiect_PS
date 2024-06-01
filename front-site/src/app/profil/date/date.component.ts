import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User, UserService } from '../../service/user.service';
import { __param } from 'tslib';

@Component({
  selector: 'app-date',
  templateUrl: './date.component.html',
  styleUrl: './date.component.css'
})
export class DateComponent implements OnInit{
  user: User = {userId: 0, username: '', password: '', email: '', fullName: '', avatar: '', balance: 0 };

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const userId = +params['id']; 
      this.userService.getUserById(userId).subscribe(
        user => {
          this.user = user;
        },
        error => {
          console.error('Eroare la obținerea detalilor utilizatorului:', error);
        }
      );
    });
  }

  goToEdit(){
    const userId = this.user?.userId;
    this.router.navigate([`/profil/edit/${userId}`]);
  }
}
