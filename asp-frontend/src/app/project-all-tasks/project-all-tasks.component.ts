import { Component, OnInit, ɵALLOW_MULTIPLE_PLATFORMS } from '@angular/core';
import {ProjectService} from '../services/project.service';
import {UserService} from "../services/user.service"
import {Project} from '../model/project'
import { ActivatedRoute } from "@angular/router";
import { TokenStorageService } from "../auth/token-storage.service";
import { Task } from '../model/Task';
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { User } from '../model/user';


@Component({
  selector: 'app-project-all-tasks',
  templateUrl: './project-all-tasks.component.html',
  styleUrls: ['./project-all-tasks.component.css']
})
export class ProjectAllTasksComponent implements OnInit {
 
    constructor(
      private route: ActivatedRoute,
      private tokenStorage: TokenStorageService,
      projectService : ProjectService,
      userService: UserService,
      private modalService: NgbModal) 
      {this.projectService = projectService, this.userService = userService}
      projectService:ProjectService;
      userService:UserService;
    ngOnInit(): void {
      this.route.params.subscribe((params) => {
        this.projectId = +params["id"];
        if (this.tokenStorage.getToken()) {
          this.username = this.tokenStorage.getUsername();
        
        }
        
        this.projectService.getProjectById( this.projectId).subscribe(t => this.project = t);
        this.userService.getNames().subscribe(t => this.users = t);
        
   
      });
    }
    project : Project;
    newTask: Task;
    closeResult: string;
    username: string;
    projectId: number;
    selected: User;
    users: User[];
    
    //új feladat hozzáadása
    addTask(content) {       
       this.newTask = new Task();
       this.modalService.open(content);
     }
     //feladat mentése
     onSave(){
     
      this.projectService.addTask(this.newTask, this.projectId).subscribe( t=>{this.project.tasks.unshift(t)});

      this.modalService.dismissAll();
    }
    //modal bezárása
    close(){
      this.modalService.dismissAll();
      this.newTask = undefined;
    }
    //feladat törlése
    deleteTask(taskId: number){
      this.projectService.deleteTask(taskId, this.project.id).subscribe(
        t => {
          var dt = this.project.tasks.find(a => a.id == taskId);
          const index = this.project.tasks.indexOf(dt, 0);
          if (index > -1) {
            this.project.tasks.splice(index, 1);
          }
        });
    }
    //törlés megerősítése
    confirmDelete(id: number) {
      if(confirm("Biztos hogy törlöd a feladatot?")) {
        this.deleteTask(id);
      }
    }
    //taskhoz user rendelése
    dataChanged(){
      this.newTask.userName = this.selected.username;
      this.newTask.userId = this.selected.id;
      this.newTask.done = false;
    }
    
  }