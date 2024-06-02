import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from './category.service';

// Definirea interfeței News
export interface News {
  newsId: number;
  category: Category;
  title: string;
  content: string;
  publishedAt: string;
  image: string;
  // Alte câmpuri relevante
}

@Injectable({
  providedIn: 'root'
})
export class NewsService {
  private baseUrl = 'http://localhost:8080/news';

  constructor(private http: HttpClient) { }

  // Metodă pentru obținerea tuturor știrilor
  getAllNews(): Observable<News[]> {
    return this.http.get<News[]>(this.baseUrl);
  }

  // Metodă pentru obținerea unei știri după ID
  getNewsById(id: number): Observable<News> {
    return this.http.get<News>(`${this.baseUrl}/${id}`);
  }

  // Metodă pentru crearea unei știri noi
  createNews(news: News): Observable<News> {
    return this.http.post<News>(this.baseUrl, news);
  }

  // Metodă pentru actualizarea unei știri existente
  updateNews(id: number, news: News): Observable<News> {
    return this.http.put<News>(`${this.baseUrl}/${id}`, news);
  }

  // Metodă pentru ștergerea unei știri după ID
  deleteNews(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  // Metodă pentru obținerea știrilor după numele categoriei
  getNewsByCategoryName(categoryName: string): Observable<News[]> {
    return this.http.get<News[]>(`${this.baseUrl}/getNewsByCategoryName`, {
      params: { categoryName }
    });
  }

  // Metodă pentru obținerea utilizatorilor după ID-ul știrii
  getUsersByNewsId(id: number): Observable<any[]> { // Definește un tip adecvat pentru User
    return this.http.get<any[]>(`${this.baseUrl}/getUsersByNewsId/${id}`);
  }
  getLatestNews(): Observable<News[]> {
    return this.http.get<News[]>(`${this.baseUrl}/latest-news`);
  }
}
