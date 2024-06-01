import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Category {
  categoryId: number;
  categoryName: string;
  subscriptionCost: number;
  logo: string;
}

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private baseUrl = 'http://localhost:8080/categories';

  constructor(private http: HttpClient) { }

  // Metodă pentru obținerea tuturor categoriilor
  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.baseUrl);
  }

  // Metodă pentru obținerea unei categorii după ID
  getCategoryById(id: number): Observable<Category> {
    return this.http.get<Category>(`${this.baseUrl}/${id}`);
  }

  // Metodă pentru crearea unei categorii noi
  createCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(this.baseUrl, category);
  }

  // Metodă pentru actualizarea unei categorii existente
  updateCategory(id: number, category: Category): Observable<Category> {
    return this.http.put<Category>(`${this.baseUrl}/${id}`, category);
  }

  // Metodă pentru ștergerea unei categorii după ID
  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
