import { Component, OnInit } from '@angular/core';
import { ImageService, Image } from '../service/image.service';
import { News, NewsService } from '../service/news.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {
  images: Image[] = [];
  news: News = {
    newsId: 0,
    category: { categoryId: 0, categoryName: '', subscriptionCost: 0, logo: '' },
    title: '',
    content: '',
    publishedAt: '',
    image: ''
  };
  currentImageIndex: number = 0;
  imageExist = false;

  constructor(
    private location: Location,
    private newsService: NewsService,
    private imageService: ImageService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const newsId = +params['id'];
      console.log('News ID:', newsId);  // Debug: verifică ID-ul știrii
      if (!isNaN(newsId) && newsId > 0) {
        this.newsService.getNewsById(newsId).subscribe(
          news => {
            this.news = news;
            console.log(news);
          },
          error => {
            console.error('Eroare la obținerea detaliilor știrii:', error);
          }
        );
        this.imageService.getImagesByNewsId(newsId).subscribe({
          next: (images) => {
            if(images.length > 0){
              this.imageExist = true;
            }
            this.images = images;
            console.log(images);
          },
          error: (err) => {
            console.error('Error loading images', err);
          }
        });
      } else {
        console.error('Invalid newsId:', newsId);
        // Poți naviga la o pagină de eroare sau afișa un mesaj de eroare
      }
    });
  }

  previousImage(): void {
    if (this.currentImageIndex > 0) {
      this.currentImageIndex--;
    }
  }

  nextImage(): void {
    if (this.currentImageIndex < this.images.length - 1) {
      this.currentImageIndex++;
    }
  }

  goToImage(index: number): void {
    this.currentImageIndex = index;
  }

  goToBack(){
    this.location.back();
  }
  
}
