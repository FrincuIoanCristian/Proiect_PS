import { Component, OnInit } from '@angular/core';
import { ImageService, Image } from '../service/image.service';
import { News, NewsService } from '../service/news.service';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrl: './news.component.css'
})
export class NewsComponent implements OnInit{
  images: Image[] = [];
  news: News = { newsId: 0, category: { categoryId: 0, categoryName: '', subscriptionCost: 0, logo: '' }, title: '', content: '', publishedAt: ''};


  constructor(
    private newsService: NewsService,
    private imageService: ImageService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const newsId = +params['id']; 
      this.newsService.getNewsById(newsId).subscribe(
        news => {
          this.news = news;
          console.log(news);
        },
        error => {
          console.error('Eroare la obținerea detalilor utilizatorului:', error);
        }
      );
    });
    this.imageService.getAllImages().subscribe({
      next: (images) => {
        this.images = images;
        console.log(images);
      },
      error: (err) => {
        console.error('Error loading images', err);
      }
    });
  }

}
