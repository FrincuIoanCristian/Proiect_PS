  import { Injectable } from '@angular/core';
  import { HttpClient } from '@angular/common/http';
  import { Observable } from 'rxjs';
  import { Subscription } from './subscription.service';

  export interface User {
    userId: number;
    username: string;
    password: string;
    email: string;
    fullName: string;
    avatar: string;
    balance: number;
    subscriptions?: Subscription[];
  }

  @Injectable({
    providedIn: 'root'
  })
  export class UserService {
    private baseUrl = 'http://localhost:8080/users';

    constructor(private http: HttpClient) { }

    // Metodă pentru login
    login(user: User): Observable<User | null> {
      return this.http.post<User>(`${this.baseUrl}/login`, user);
    }

    // Metodă pentru obținerea tuturor utilizatorilor
    getAllUsers(): Observable<User[]> {
      return this.http.get<User[]>(this.baseUrl);
    }

    // Metodă pentru obținerea unui utilizator după ID
    getUserById(id: number): Observable<User> {
      return this.http.get<User>(`${this.baseUrl}/${id}`);
    }

    // Metodă pentru crearea unui utilizator nou
    createUser(user: User): Observable<User> {
      return this.http.post<User>(this.baseUrl, user);
    }

    // Metodă pentru actualizarea unui utilizator existent
    updateUser(id: number, user: User): Observable<User> {
      return this.http.put<User>(`${this.baseUrl}/${id}`, user);
    }

    // Metodă pentru ștergerea unui utilizator după ID
    deleteUser(id: number): Observable<void> {
      return this.http.delete<void>(`${this.baseUrl}/${id}`);
    }
  }