import { Component, OnInit } from '@angular/core';
import { News, NewsService } from '../service/news.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrl: './view.component.css'
})
export class ViewComponent implements OnInit{
  newsList: News[] = [];
  news: News = { newsId: 0, category: { categoryId: 0, categoryName: 'categoryName', subscriptionCost: 30, logo: 'https://th.bing.com/th/id/R.7e665da3a90b4c2184370ea5fcdbe883?rik=%2bN8Z7f2c0cPNAw&pid=ImgRaw&r=0' }, title: 'NewsTitle', content: 'content', publishedAt: '2024-06-03'};


  constructor(
    private newsService: NewsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.newsService.getLatestNews().subscribe({
      next: (news) =>{
        this.newsList = news;
      },
      error: (err) =>{
        console.log('Error loading news', err);
      }
    });
  }
}
