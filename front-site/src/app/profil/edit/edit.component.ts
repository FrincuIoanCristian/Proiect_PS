import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User, UserService } from '../../service/user.service';
import { __param } from 'tslib';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrl: './edit.component.css'
})
export class EditComponent {
  user: User = { userId: 0, username: '', password: '', email: '', fullName: '', avatar: '', balance: 0 };
  newUser: User =  {userId: 0, username: '', password: '', email: '', fullName: '', avatar: '', balance: 0 };
  errorMessage: string = '';
  usernameExists: boolean = false;
  emailExists: boolean = false;

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const userId = +params['id']; 
      this.userService.getUserById(userId).subscribe(
        user => {
          this.user = user;
          console.log(this.user);
        },
        error => {
          console.error('Eroare la obținerea detalilor utilizatorului:', error);
        }
      );
    });
  }

  modify() {
    this.errorMessage = '';
    this.usernameExists = false;
    this.emailExists = false;
    if(this.newUser.fullName !== ''){
      this.user.fullName = this.newUser.fullName;
    }
    if(this.newUser.avatar !== ''){
      this.user.avatar = this.newUser.avatar;
    }
    if(this.newUser.password !== ''){
      if(this.newUser.password.length >= 8){
        this.user.password = this.newUser.password;
      }else{
        this.errorMessage = 'New password must be at least 8 characters long.';
        return;
      }
    }

    if(this.newUser.username !== ''){
      this.userService.getAllUsers().subscribe(users => {
        const usernameExists = users.some(u => u.username === this.newUser.username && u.username !== this.user.username);
        if (usernameExists) {
          this.errorMessage = 'Username already exists.';
          return;
        }else{
          console.log("new username")
          this.user.username = this.newUser.username;
        }
      });
    }

    if(this.newUser.email !== ''){
      this.userService.getAllUsers().subscribe(users => {
        const emailExists = users.some(u => u.email === this.newUser.email && u.email !== this.user.email);
        if (emailExists) {
          this.errorMessage = 'Email already exists.';
          return;
        }else{
          console.log("new email");
          this.user.email = this.newUser.email;
        }
      });
    }

    console.log("New user", this.user);
    this.userService.updateUser(this.user.userId,this.user).subscribe(
      response => {
        console.log('User registered successfully:', response);
        this.router.navigate([`/profil/date/${response.userId}`]);
      },
      error => {
        console.error('Error registering user:', error);
        this.errorMessage = 'Registration failed. Please try again.';
      }
    );

  }

  isModifyFormValid(): boolean {
    return this.newUser.username === '' &&
           this.newUser.email === '' &&
           this.newUser.fullName === '' &&
           this.newUser.avatar == '' &&
           this.newUser.password == '';
  }
}
