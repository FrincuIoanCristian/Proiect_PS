import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { __param } from 'tslib';
import { Category, CategoryService } from '../../service/category.service';
import { News, NewsService } from '../../service/news.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-date',
  templateUrl: './date.component.html',
  styleUrl: './date.component.css'
})
export class DateComponent implements OnInit{
  category: Category = {categoryId: 0, categoryName: '', subscriptionCost: 0, logo: ''};
  newsList: News[] = [];


  constructor(private location: Location, private router: Router, private route: ActivatedRoute, private newsService: NewsService,private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const categoryId = +params['id']; 
      this.categoryService.getCategoryById(categoryId).subscribe(
        category => {
          this.category = category;
          console.log('Detalii categorie:', this.category);
          this.newsService.getNewsByCategoryName(category.categoryName).subscribe(
            news => {
              this.newsList = news;
              console.log("Stiri: ", news);
            },
            error =>{
              console.error('Eroare la obținerea stirilor:', error);
            }
          )
        },
        error => {
          console.error('Eroare la obtinerea detaliilor despre categorie:', error);
        }
      );
    });  
  }

  goToBack(){
    this.location.back();
  }

  
}
