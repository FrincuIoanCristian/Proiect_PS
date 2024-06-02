import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User, UserService } from '../../service/user.service';
import { __param } from 'tslib';
import { Subscription, SubscriptionService } from '../../service/subscription.service';
import { Category, CategoryService } from '../../service/category.service';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrl: './principal.component.css'
})
export class PrincipalComponent implements OnInit {
  user: User = {userId: 0, username: '', password: '', email: '', fullName: '', avatar: '', balance: 0 };
  newUser: User =  {userId: 0, username: '', password: '', email: '', fullName: '', avatar: '', balance: 0 };
  newSubscription: Subscription = {subscriptionId: 0, user: {userId: 0, username: '', password: '', email: '', fullName: '', avatar: '', balance: 0 }, category: {categoryId: 0, categoryName: '', subscriptionCost: 0, logo: ''}, startDate: '', amountPaid: 0};
  categories: Category[] = [];
  subscriptionsUser: Subscription[] = []; 
  showDropdown = false;
  notificationCount = 0;
  type: string = '';
  errorMessage: string = '';
  usernameExists: boolean = false;
  emailExists: boolean = false;

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService, private subscriptionService: SubscriptionService, private categoryService: CategoryService) {}

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
      this.subscriptionService.getSubscriptionsByUserId(userId).subscribe(
        subscriptions =>{
          this.subscriptionsUser = subscriptions;
          console.log(subscriptions);
        },
        error =>{
          console.error('Eroare la obținerea abonamentelor utilizatorului:', error);
        }
      );
    });
    this.categoryService.getAllCategories().subscribe(
      categories =>{
        this.categories = categories;
        console.log(categories);
      },
      error =>{
        console.error('Eroare la obținerea categoriilor:', error);
      }
    );
  }

  loadSubscription(){
    this.route.params.subscribe(params => {
      const userId = +params['id']; 
      this.subscriptionService.getSubscriptionsByUserId(userId).subscribe(
        subscriptions =>{
          this.subscriptionsUser = subscriptions;
          console.log(subscriptions);
        },
        error =>{
          console.error('Eroare la obținerea abonamentelor utilizatorului:', error);
        }
      );
    });
  }

  subscriptionUser(): void{
    const userId = this.user?.userId;
    this.router.navigate([`/profil/subscription/${userId}`]);
  }

  setType(type: string) {
    this.type = type;
    switch(type){
      case 'NewAbonament':{
        this.loadSubscription();
        this.categories = this.getAvailableCategories();
        break;
      }
      case 'Abonamente':{
        this.loadSubscription();
        break;
      }
      default:
        console.log('Type is not recognized.');
        break;
    }
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

  getAvailableCategories(): Category[] {
    this.categories
    const subscribedCategoryIds = this.subscriptionsUser.map(subscription => subscription.category.categoryId);
    return this.categories.filter(category => !subscribedCategoryIds.includes(category.categoryId));
  }

  addSubscription(category: Category){
    this.route.params.subscribe(params => {
      const userId = +params['id']; 
      this.newSubscription.user = this.user;
      this.newSubscription.category = category;
      this.subscriptionService.createSubscription(this.newSubscription).subscribe(
        subscription =>{
          this.loadSubscription();
          this.getAvailableCategories();
          this.setType('');
          console.log("new subscription", subscription);
        },
        error =>{
          console.error('Eroare la obținerea abonamentelor utilizatorului:', error);
        }
      );
    });
  }
  
  deleteSubscription(id: number){
    this.subscriptionService.deleteSubscription(id).subscribe({
      next: () => {
        this.loadSubscription();
        this.categories = this.getAvailableCategories(); 

      },
      error: (err) => {
        console.error('Error deleting category', err);
      }
    });
  }

  goToStiri(id: number){
    console.log("aici", id);
    this.router.navigate([`/stiri/${id}`]);
  }

}
