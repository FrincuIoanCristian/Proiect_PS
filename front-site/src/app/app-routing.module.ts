import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component'; 
import { SignUpComponent} from './sign-up/sign-up.component'; 
import { PanelComponent } from './admin/panel/panel.component';
import { PrincipalComponent } from './profil/principal/principal.component';
import { DateComponent } from './profil/date/date.component';
import { AdminComponent } from './admin/admin/admin.component';
import { ViewComponent } from './view/view.component';
import { NewsComponent } from './news/news.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'sign-up', component: SignUpComponent},
  { path: 'login', component: LoginComponent },
  { path: 'admin-panel', component: PanelComponent},
  { path: 'admin', component: AdminComponent},
  { path: 'profil/:id', component: PrincipalComponent},
  { path: 'stiri/:id', component: DateComponent},
  { path: 'view', component: ViewComponent},
  { path: 'news/:id', component: NewsComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', redirectTo: '/home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
