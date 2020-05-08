import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";

import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./admin/register/register.component";
import { HomeComponent } from "./home/home.component";
import { httpInterceptorProviders } from "./auth/auth-interceptor";
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { UsersComponent } from './admin/users/users.component';
import { ProjectsComponent } from './projects/projects.component';
import { ProjectComponent } from './project/project.component';
import { FooterComponent } from './footer/footer.component';
import { ProjectAllTasksComponent } from './project-all-tasks/project-all-tasks.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    NavBarComponent,
    UsersComponent,
    ProjectsComponent,
    ProjectComponent,
    FooterComponent,
    ProjectAllTasksComponent,
  ],
  imports: [
    BrowserModule, 
    AppRoutingModule, 
    FormsModule, 
    HttpClientModule, 
    CommonModule,
    NgbModule,
    ReactiveFormsModule
      ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent],
})
export class AppModule {}
