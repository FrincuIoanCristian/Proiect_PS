import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user.service';
import { Category } from './category.service';

// Definirea interfeței Subscription
export interface Subscription {
  subscriptionId?: number;
  user: User;
  category: Category;
  startDate: string; // LocalDate este reprezentat ca string în JSON
  amountPaid: number;
}



@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {
  private baseUrl = 'http://localhost:8080/subscriptions';

  constructor(private http: HttpClient) { }

  // Metodă pentru obținerea tuturor abonamentelor
  getAllSubscriptions(): Observable<Subscription[]> {
    return this.http.get<Subscription[]>(this.baseUrl);
  }

  // Metodă pentru obținerea unui abonament după ID
  getSubscriptionById(id: number): Observable<Subscription> {
    return this.http.get<Subscription>(`${this.baseUrl}/${id}`);
  }

  // Metodă pentru crearea unui abonament nou
  createSubscription(subscription: Subscription): Observable<Subscription> {
    return this.http.post<Subscription>(this.baseUrl, subscription);
  }

  // Metodă pentru actualizarea unui abonament existent
  updateSubscription(id: number, subscription: Subscription): Observable<Subscription> {
    return this.http.put<Subscription>(`${this.baseUrl}/${id}`, subscription);
  }

  // Metodă pentru ștergerea unui abonament după ID
  deleteSubscription(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  // Metodă pentru obținerea abonamentelor unui utilizator după ID-ul utilizatorului
  getSubscriptionsByUserId(userId: number): Observable<Subscription[]> {
    return this.http.get<Subscription[]>(`${this.baseUrl}/getSubscriptionsByUserId/${userId}`);
  }
}
