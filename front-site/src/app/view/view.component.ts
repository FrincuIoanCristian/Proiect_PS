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

  gotoHome(){
    this.router.navigate(['/home']);
  }
}
