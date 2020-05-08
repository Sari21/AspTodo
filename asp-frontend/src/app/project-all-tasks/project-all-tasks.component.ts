import { Component, OnInit } from '@angular/core';
import {ProjectService} from '../services/project.service';
import {Project} from '../model/project'
import { ActivatedRoute } from "@angular/router";
import { TokenStorageService } from "../auth/token-storage.service";
import { Task } from '../model/Task';
import { NgbModal, ModalDismissReasons } from "@ng-bootstrap/ng-bootstrap";


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
      private modalService: NgbModal) {this.projectService = projectService}
      projectService:ProjectService;
    ngOnInit(): void {
      this.route.params.subscribe((params) => {
        this.projectId = +params["id"];
        if (this.tokenStorage.getToken()) {
          this.username = this.tokenStorage.getUsername();
        
        }
        //var v = this.projectService.getProjectById( projectId).subscribe(t => console.log(t));
        this.projectService.getProjectById( this.projectId).subscribe(t => this.project = t);
        //var v = this.projectService.getProjectByUsername( username).subscribe(t => console.log(t));
      });
    }
    project : Project;
    newTask: Task;
    closeResult;
    username;
    projectId;
    
    addTask(content) {
      // this.selectedUser = {...this.originalUser};
       
       this.newTask = new Task();
       this.modalService.open(content).result.then((result) => {
         this.closeResult = `Closed with: ${result}`;
         
       }, (reason) => {
         this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
       });
     }
     onSave(){
     
      this.projectService.addTask(this.newTask, this.username, this.projectId).subscribe( t=>{this.project.tasks.unshift(t)});
      this.newTask = undefined;
      this.modalService.dismissAll();
    }
    close(){
      this.modalService.dismissAll();
      this.newTask = undefined;
    }
     private getDismissReason(reason: any): string {
      if (reason === ModalDismissReasons.ESC) {
        return 'by pressing ESC';
      } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
        return 'by clicking on a backdrop';
      } else {
        return  `with: ${reason}`;
      }
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
  }