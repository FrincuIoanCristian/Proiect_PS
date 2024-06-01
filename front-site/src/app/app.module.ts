import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; 
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { SignUpComponent } from './sign-up/sign-up.component'; 
import { LoginComponent } from './login/login.component';
import { PanelComponent } from './admin/panel/panel.component';
import { AdminComponent } from './admin/admin/admin.component';
import { DateComponent } from './profil/date/date.component';
import { PrincipalComponent } from './profil/principal/principal.component';
import { EditComponent } from './profil/edit/edit.component';
import { ViewComponent } from './view/view.component';
import { NewsComponent } from './news/news.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SignUpComponent,
    LoginComponent, 
    PanelComponent, 
    AdminComponent, 
    DateComponent, 
    PrincipalComponent, 
    EditComponent, 
    ViewComponent, NewsComponent, 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, 
    HttpClientModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
