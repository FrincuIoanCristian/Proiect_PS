import { Component, OnInit } from '@angular/core';
import { NewsService, News } from '../../service/news.service';
import { ImageService, Image } from '../../service/image.service';
import { CategoryService, Category } from '../../service/category.service';
import { Router } from '@angular/router';
import { User, UserService } from '../../service/user.service';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  newsList: News[] = [];
  images: Image[] = [];
  categories: Category[] = [];
  users: User[] = [];

  user: User = { userId: 0, username: '', password: '', email: '', fullName: '', avatar: '', balance: 0 };
  category: Category = { categoryId: 0, categoryName: '', subscriptionCost: 0, logo: '' };
  image: Image = { imageId: 0, news: { newsId: 0, category: { categoryId: 0, categoryName: '', subscriptionCost: 0, logo: '' }, title: '', content: '', publishedAt: '', image: '' }, url: '' };
  news: News = { newsId: 0, category: { categoryId: 0, categoryName: '', subscriptionCost: 0, logo: '' }, title: '', content: '', publishedAt: '', image: '' };

  user2: User = { userId: 0, username: '', password: '', email: '', fullName: '', avatar: '', balance: 0 };
  category2: Category = { categoryId: 0, categoryName: '', subscriptionCost: 0, logo: '' };
  image2: Image = { imageId: 0, news: { newsId: 0, category: { categoryId: 0, categoryName: '', subscriptionCost: 0, logo: '' }, title: '', content: '', publishedAt: '', image: '' }, url: '' };
  news2: News = { newsId: 0, category: { categoryId: 0, categoryName: '', subscriptionCost: 0, logo: '' }, title: '', content: '', publishedAt: '', image: '' };

  showDropdown = false;
  notificationCount = 0;
  type: string = '';
  activateAdd = false;
  editFlag = false;

  constructor(
    private userService: UserService,
    private newsService: NewsService,
    private imageService: ImageService,
    private categoryService: CategoryService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadNews();
    this.loadImage();
    this.loadCategory();
    this.loadUser();
  }

  loadNews() {
    this.newsService.getAllNews().subscribe({
      next: (news) => {
        this.newsList = news;
      },
      error: (err) => {
        console.error('Error loading news', err);
      }
    });
  }

  loadImage() {
    this.imageService.getAllImages().subscribe({
      next: (images) => {
        this.images = images;
      },
      error: (err) => {
        console.error('Error loading images', err);
      }
    });
  }

  loadCategory() {
    this.categoryService.getAllCategories().subscribe({
      next: (categories) => {
        this.categories = categories;
      },
      error: (err) => {
        console.error('Error loading images', err);
      }
    });
  }

  loadUser() {
    this.userService.getAllUsers().subscribe({
      next: (users) => {
        this.users = users;
      },
      error: (err) => {
        console.error('Error loading images', err);
      }
    });
  }

  setType(type: string) {
    this.type = type;
    this.activateAdd = false;
    this.editFlag = false;
  }

  editNews(id: number) {
    alert('Edit news with ID ' + id);
  }

  deleteNews(id: number) {
    alert('Delete news with ID ' + id);
  }

  logout() {
    this.router.navigate(['/home']);
  }

  activateButton() {
    this.activateAdd = true;
    this.user = { userId: 0, username: '', password: '', email: '', fullName: '', avatar: '', balance: 0 };
    this.category = { categoryId: 0, categoryName: '', subscriptionCost: 0, logo: '' };
    this.image = { imageId: 0, news: { newsId: 0, category: { categoryId: 0, categoryName: '', subscriptionCost: 0, logo: '' }, title: '', content: '', publishedAt: '', image: '' }, url: '' };
    this.news = { newsId: 0, category: { categoryId: 0, categoryName: '', subscriptionCost: 0, logo: '' }, title: '', content: '', publishedAt: '', image: '' };
  }

  adaugaInBD() {
    switch (this.type) {
      case 'Category': {
        console.log("Categorie: ", this.category);
        const nameExist = this.categories.some(c => c.categoryName === this.category.categoryName);
        if (nameExist) {
          console.log("Name Existing");
        } else {
          this.categoryService.createCategory(this.category).subscribe(
            response => {
              console.log("Category created: ", response);
              this.loadCategory();
              this.activateAdd = false;

            },
            error => {
              console.error('Error registering category:', error)
            }
          );
        }
        break;
      }
      case 'News': {
        console.log("News: ", this.news);
        this.newsService.createNews(this.news).subscribe(
          response => {
            console.log("News created: ", response);
            this.loadNews();
            this.activateAdd = false;
          },
          error => {
            console.error('Error registering news:', error)
          }
        );
        break;
      }

      case 'Image': {
        console.log("Image: ", this.image);
        this.imageService.createImage(this.image).subscribe(
          response => {
            console.log("Image created: ", response);
            this.loadImage();
            this.activateAdd = false;
          },
          error => {
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

  delete(id: number) {
    switch (this.type) {
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
            this.loadImage();
          },
          error: (err) => {
            console.error('Error deleting category', err);
          }
        });
        break;
      case 'User':
        this.userService.deleteUser(id).subscribe({
          next: () => {
            this.loadUser();
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

  editButtonNews(news: News) {
    this.editFlag = true;
    this.news2 = news;
  }

  editButtonUser(user: User) {
    this.editFlag = true;
    this.user2 = user;
  }
  editButtonCategory(category: Category) {
    this.editFlag = true;
    this.category2 = category;
  }
  editButtonImage(image: Image) {
    this.editFlag = true;
    this.image2 = image;
  }

  editeaza() {
    switch (this.type) {
      case 'Category': {
        this.categoryService.updateCategory(this.category2.categoryId, this.category2).subscribe(
          response =>{
            this.loadCategory();
            this.setType('Category');
          },
          error =>{
            console.error('Error change password:', error);
          }
        );
        console.log(this.category2);
        break;
      }
      case 'News': {
        this.newsService.updateNews(this.news2.newsId, this.news2).subscribe(
          response =>{
            this.loadNews();
            this.setType('News');
          },
          error =>{
            console.error('Error change password:', error);
          }
        );
        console.log(this.news2);
        break;
      }
      case 'Image': {
        this.imageService.updateImage(this.image2.imageId, this.image).subscribe(
          response =>{
            this.loadImage();
            this.setType('Image');
          },
          error =>{
            console.error('Error change password:', error);
          }
        );
        console.log(this.image2);
        break;

      }
      case 'User': {
        this.userService.updateUser(this.user2.userId, this.user2).subscribe(
          response =>{
            this.loadUser();
            this.setType('User');
          },
          error =>{
            console.error('Error change password:', error);
          }
        );
        console.log(this.image2);
        console.log(this.user2);
        break;
      }
      default:

    }
  }

}
