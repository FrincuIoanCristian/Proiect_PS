import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User, UserService } from '../../service/user.service';
import { __param } from 'tslib';
import { Subscription, SubscriptionService } from '../../service/subscription.service';
import { Category, CategoryService } from '../../service/category.service';
import { forkJoin } from 'rxjs';
import { executionAsyncResource } from 'async_hooks';

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
  
  parolaGresita: boolean = false;
  parolaLipsa: boolean = false;
  campuriGoale: boolean = false;
  usernameExists: boolean = false;
  emailExists: boolean = false;

  showPassword: boolean = false;

  parolaCurenta: string = '';
  parolaNoua: string = '';
  parolaNoua2: string = '';
  mesajParola: string = '';


  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService, private subscriptionService: SubscriptionService, private categoryService: CategoryService) {}

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
      this.subscriptionService.getSubscriptionsByUserId(userId).subscribe(
        subscriptions =>{
          this.subscriptionsUser = subscriptions;
        },
        error =>{
          console.error('Eroare la obținerea abonamentelor utilizatorului:', error);
        }
      );
    });
    this.loadCategory();
  }

  loadSubscription(){
    this.route.params.subscribe(params => {
      const userId = +params['id']; 
      this.subscriptionService.getSubscriptionsByUserId(userId).subscribe(
        subscriptions =>{
          this.subscriptionsUser = subscriptions;
          this.getAvailableCategories();
        },
        error =>{
          console.error('Eroare la obținerea abonamentelor utilizatorului:', error);
        }
      );
    });
  }

  loadCategory(){
    this.categoryService.getAllCategories().subscribe(
      categories =>{
        this.categories = categories;
      },
      error =>{
        console.error('Eroare la obținerea categoriilor:', error);
      }
    );
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
        this.loadCategory();
        this.categories = this.getAvailableCategories();
        break;
      }
      case 'Abonamente':{
        this.loadSubscription();
        break;
      }
      case 'Edit':{
        this.usernameExists = false;
        this.emailExists = false;
        this.parolaLipsa = false;
        this.parolaGresita = false;
        this.campuriGoale = false;
        this.newUser.username = this.user.username;
        this.newUser.email = this.user.email;
        this.newUser.fullName = this.user.fullName;
        this.newUser.avatar = this.user.avatar;
        break;
      }
      case 'ChangePassword':{
        this.parolaCurenta = '';
        this.parolaNoua = '';
        this.parolaNoua2 = '';
        this.mesajParola = '';
        break;
      }
      default:
        console.log('Type is not recognized.');
        break;
    }
  }

  getAvailableCategories(): Category[] {
    const subscribedCategoryIds = this.subscriptionsUser.map(subscription => subscription.category.categoryId);
    return this.categories.filter(category => !subscribedCategoryIds.includes(category.categoryId));
  }

  addSubscription(category: Category){
    if (this.subscriptionsUser.some(sub => sub.category.categoryId === category.categoryId)) {
      console.log('You are already subscribed to this category.');
      return;
    }
    if (this.user.balance < category.subscriptionCost) {
      console.log('You do not have enough balance to subscribe to this category.');
      return;
    }

    this.route.params.subscribe(params => {
      const userId = +params['id']; 
      this.newSubscription.user = this.user;
      this.newSubscription.category = category;
      this.subscriptionService.createSubscription(this.newSubscription).subscribe(
        subscription =>{
          this.user = subscription.user;
          this.loadSubscription();
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

      },
      error: (err) => {
        console.error('Error deleting category', err);
      }
    });
  }

  goToStiri(id: number){
    this.router.navigate([`/stiri/${id}`]);
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  modify(): void {
    this.errorMessage = '';
    this.usernameExists = false;
    this.emailExists = false;
    this.parolaLipsa = false;
    this.parolaGresita = false;
    this.newUser.balance = this.user.balance;

    if(this.newUser.password === ''){
      this.parolaLipsa = true;
      return;
    }
    if(this.newUser.password !== this.user.password){
      this.parolaGresita = true;
      return;
    }

    if(this.newUser.fullName === ''){
      this.newUser.fullName = this.user.fullName;
    }
    if(this.newUser.avatar === ''){
      this.newUser.avatar = this.user.avatar;
    }

    const observables = [];
    if(this.newUser.username !== ''){
      observables.push(this.userService.getAllUsers());
    } else {
      this.newUser.username = this.user.username;
    }

    if(this.newUser.email !== ''){
      observables.push(this.userService.getAllUsers());
    } else {
      this.newUser.email = this.user.email;
    }

    if (observables.length > 0) {
      forkJoin(observables).subscribe(results => {
        if (this.newUser.username !== '') {
          const usersForUsernameCheck = results[0];
          const usernameExist = usersForUsernameCheck.some(u => u.username === this.newUser.username && u.username !== this.user.username);
          if (usernameExist) {
            this.usernameExists = true;
            this.errorMessage = 'Username already exists.';
          }
        }

        if (this.newUser.email !== '') {
          const usersForEmailCheck = results.length > 1 ? results[1] : results[0];
          const emailExist = usersForEmailCheck.some(u => u.email === this.newUser.email && u.email !== this.user.email);
          if (emailExist) {
            this.emailExists = true;
            this.errorMessage = 'Email already exists.';
          }
        }

        console.log(this.emailExists, this.usernameExists);
        if(this.emailExists || this.usernameExists){
          console.log("error");
        }else{
          console.log("User", this.user);
          console.log("New user", this.newUser);

          this.userService.updateUser(this.user.userId,this.newUser).subscribe(
            response => {
              this.user = response;
              this.setType('');
              console.log('User registered successfully:', response);
            },
            error => {
              console.error('Error registering user:', error);
              this.errorMessage = 'Registration failed. Please try again.';
            }
          );
        }
      });
    } else {
      console.log(this.emailExists, this.usernameExists);
      if(this.emailExists || this.usernameExists){
        console.log("error");
      }else{
        console.log("User", this.user);
        console.log("New user", this.newUser);

        this.userService.updateUser(this.user.userId,this.newUser).subscribe(
          response => {
            this.user = response;
            this.setType('');
            console.log('User registered successfully:', response);
          },
          error => {
            console.error('Error registering user:', error);
            this.errorMessage = 'Registration failed. Please try again.';
          }
        );
      }
    }
  }

  schimbaParola(){
    console.log("parola curenta", this.parolaCurenta);
    console.log("parola noua", this.parolaNoua);
    console.log("parola nous2", this.parolaNoua2);
    this.mesajParola = '';

    if(this.parolaCurenta !== this.user.password){
      this.mesajParola = 'parolaIncorenta';
      return;
    }
    if(this.parolaCurenta === this.parolaNoua){
      this.mesajParola = 'same';
      return;
    }
    if(this.parolaNoua.length < 8){
      this.mesajParola = 'preaScurta';
      return;
    }
    if(this.parolaNoua !== this.parolaNoua2){
      this.mesajParola = 'different';
      return;
    }
    this.user.password = this.parolaNoua;
    this.userService.updateUser(this.user.userId, this.user).subscribe(
      response =>{
        this.user = response;
        console.log("Password change");
        this.setType('');
      },
      error =>{
        console.error('Error change password:', error);
        this.errorMessage = 'Registration failed. Please try again.';
      }
    )
  }

  stergeCont(): void {
    if (confirm('Esti sigur ca vrei sa stergi contul? Aceasta actiune este ireversibila.')) {
      this.userService.deleteUser(this.user.userId).subscribe(
        () => {
          alert('Contul a fost sters cu succes.');
          this.router.navigate(['/login']);
        },
        error => {
          console.error('Eroare la stergerea contului:', error);
          this.errorMessage = 'stergerea contului a esuat. Te rugam sa incerci din nou.';
        }
      );
    }
  }
}