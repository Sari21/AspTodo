import { Component, OnInit } from '@angular/core';
import {ProjectService} from '../services/project.service';
import {Project} from '../model/project'
import { ActivatedRoute } from "@angular/router";
import { TokenStorageService } from "../auth/token-storage.service";
import { Task } from '../model/Task';
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private tokenStorage: TokenStorageService,
    projectService : ProjectService,
    private modalService: NgbModal) {this.projectService = projectService}
    projectService:ProjectService;
  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.projectId = +params["id"];
      if (this.tokenStorage.getToken()) {
        this.username = this.tokenStorage.getUsername();
      
      }
      this.projectService.getTasksByUserAndProject(this.username, this.projectId).subscribe
      (t => (this.project = t, console.log(t)));
 
    });
  }
  project : Project;
  newTask: Task;
  closeResult;
  username;
  projectId;
  failed = false;
  errorMessage = 'Nem sikerült hozzáadni a feladatot';
  
  addTask(content) {
     this.newTask = new Task();
     this.modalService.open(content);
 
   }
   onSave(){
    this.newTask.userName = this.username;
    this.newTask.done = false;
    this.projectService.addTask(this.newTask,  this.projectId).subscribe(
       (data)=>{this.project.tasks.unshift(data), this.close()},
       (error) => {this.failed = true;});
   
  }
  close(){
    this.modalService.dismissAll();
    this.newTask = undefined;
    this.failed = false;
  }
   
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
  confirmDelete(id: number) {
    if(confirm("Biztos hogy törlöd a feladatot?")) {
      this.deleteTask(id);
    }
  }
  setDone(id: number){
    this.projectService.updateTaskIsDone(this.project.id, id).subscribe(data => {
      this.project.tasks.find(t => t.id == id).done = true;
    },
    (error) => {
      console.log(error);
    });

  }
  

}