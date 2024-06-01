import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { News } from './news.service';

// Definirea interfeței Image
export interface Image {
  imageId: number;
  news: News;
  url: string;
}

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private baseUrl = 'http://localhost:8080/images';

  constructor(private http: HttpClient) { }

  // Metodă pentru obținerea tuturor imaginilor
  getAllImages(): Observable<Image[]> {
    return this.http.get<Image[]>(this.baseUrl);
  }

  // Metodă pentru obținerea unei imagini după ID
  getImageById(id: number): Observable<Image> {
    return this.http.get<Image>(`${this.baseUrl}/${id}`);
  }

  // Metodă pentru crearea unei imagini noi
  createImage(image: Image): Observable<Image> {
    return this.http.post<Image>(this.baseUrl, image);
  }

  // Metodă pentru actualizarea unei imagini existente
  updateImage(id: number, image: Image): Observable<Image> {
    return this.http.put<Image>(`${this.baseUrl}/${id}`, image);
  }

  // Metodă pentru ștergerea unei imagini după ID
  deleteImage(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  // Metodă pentru obținerea imaginilor după ID-ul știrii
  getImagesByNewsId(id: number): Observable<Image[]> {
    return this.http.get<Image[]>(`${this.baseUrl}/getImagesByNewsId/${id}`);
  }
}
