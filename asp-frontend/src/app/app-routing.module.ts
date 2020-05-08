import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { RegisterComponent } from "./admin/register/register.component";
import { LoginComponent } from "./login/login.component";
import { HomeComponent } from "./home/home.component";
import { UsersComponent} from "./admin/users/users.component"
import {ProjectsComponent} from "./projects/projects.component"
import { ProjectComponent } from './project/project.component';
import {ProjectAllTasksComponent} from './project-all-tasks/project-all-tasks.component';

const routes: Routes = [
  {
    path: "home",
    component: HomeComponent,
  },
  {
    path: "project/:id",
    component: ProjectComponent,
  },
  {
    path: "projectall/:id",
    component: ProjectAllTasksComponent,
  },
  {
    path: "projects",
    component: ProjectsComponent,
  },
  {
    path: "users",
    component: UsersComponent,
  },
  
  {
    path: "auth/login",
    component: LoginComponent,
  },
  {
    path: "signup",
    component: RegisterComponent,
  },
  {
    path: "",
    redirectTo: "home",
    pathMatch: "full",
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
