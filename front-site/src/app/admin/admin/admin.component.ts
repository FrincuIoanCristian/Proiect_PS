import { Component, OnInit } from '@angular/core';
import { NewsService, News } from '../../service/news.service';
import { ImageService, Image } from '../../service/image.service';
import { CategoryService, Category }  from '../../service/category.service';
import { Router } from '@angular/router';
import { getLocaleDateFormat } from '@angular/common';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  newsList: News[] = [];
  images: Image[] = [];
  categories: Category[] = [];

  category: Category = {categoryId: 0, categoryName: '', subscriptionCost: 0, logo: ''};
  image: Image = {
    imageId: 0,
    news: {
      newsId: 0,
      category: {categoryId: 0, categoryName: '', subscriptionCost: 0, logo: ''},
      title: '',
      content: '',
      publishedAt: '',
      image: ''
    },
    url: ''
  };
  news: News = { newsId: 0, category: { categoryId: 0, categoryName: '', subscriptionCost: 0, logo: '' }, title: '', content: '', publishedAt: '', image: ''};

  showDropdown = false;
  notificationCount = 0;
  type: string = '';
  activateAdd = false;

  constructor(
    private newsService: NewsService,
    private imageService: ImageService,
    private categoryService: CategoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadNews();
    this.loadImage();
    this.loadCategory();
  }

  loadNews(){
    this.newsService.getAllNews().subscribe({
      next: (news) => {
        this.newsList = news;
      },
      error: (err) => {
        console.error('Error loading news', err);
      }
    });
  }

  loadImage(){
    this.imageService.getAllImages().subscribe({
      next: (images) => {
        this.images = images;
      },
      error: (err) => {
        console.error('Error loading images', err);
      }
    });
  }

  loadCategory(){
    this.categoryService.getAllCategories().subscribe({
      next: (categories) => {
        this.categories = categories;
      },
      error: (err) => {
        console.error('Error loading images', err);
      }
    });
  }


  toggleDropdown(): void {
    this.showDropdown = !this.showDropdown;
  }

  setType(type: string) {
    this.type = type;
    this.activateAdd = false;
  }

  editNews(id: number) {
    alert('Edit news with ID ' + id);
  }

  deleteNews(id: number) {
    alert('Delete news with ID ' + id);
  }

  logout(){
    this.router.navigate(['/home']);
  }

  activateButton(){
    this.activateAdd = true;
    console.log(this.categories);
  }

  adaugaInBD(){
    switch (this.type){
      case 'Category':{
        console.log("Categorie: ", this.category);
        const nameExist = this.categories.some(c => c.categoryName ===  this.category.categoryName);
        if(nameExist){
          console.log("Name Existing");
        }else{
          this.categoryService.createCategory(this.category).subscribe(
            response =>{
              console.log("Category created: ", response);
              this.loadCategory();
              this.activateAdd = false;

            },
            error =>{
              console.error('Error registering category:', error)
            }
          );
        }
        break;
      }
      case 'News':{
        console.log("News: ", this.news);
        this.newsService.createNews(this.news).subscribe(
          response =>{
            console.log("News created: ", response);
            this.loadNews();
            this.activateAdd = false;
          },
          error =>{
            console.error('Error registering news:', error)
          }
        );
        break; 
      } 

      case 'Image':{
        console.log("Image: ", this.image);
        this.imageService.createImage(this.image).subscribe(
          response =>{
            console.log("Image created: ", response);
            this.loadImage();
            this.activateAdd = false;
          },
          error =>{
            console.error('Error registering image:', error)
          }
        );
        break; 
      }      

      default:
        // Dacă type nu este nici 'Category', nici 'News' sau 'Image'
        console.log('Type is not recognized.');
        break;  
    }
  }

  delete(id: number){
    switch (this.type){
      case 'Category':
        this.categoryService.deleteCategory(id).subscribe({
          next: () => {
            this.loadCategory(); 
          },
          error: (err) => {
            console.error('Error deleting category', err);
          }
        });
        break;

      case 'News':
        this.newsService.deleteNews(id).subscribe({
          next: () => {
            this.loadNews(); 
          },
          error: (err) => {
            console.error('Error deleting category', err);
          }
        });
        break;  

      case 'Image':
        this.imageService.deleteImage(id).subscribe({
          next: () => {
            this.loadNews(); 
          },
          error: (err) => {
            console.error('Error deleting category', err);
          }
        });
        break;         

      default:
        console.log('Type is not recognized.');
        break;  
    }
  }

}
