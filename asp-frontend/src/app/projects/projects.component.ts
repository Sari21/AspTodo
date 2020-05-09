import { Component, OnInit } from '@angular/core';
import {ProjectService} from '../services/project.service';
import {Project} from '../model/project'
import { NgbModal, ModalDismissReasons } from "@ng-bootstrap/ng-bootstrap";
import { TokenStorageService } from "../auth/token-storage.service";

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  constructor(    private tokenStorage: TokenStorageService, projectService : ProjectService, private modalService: NgbModal)
   { this.projectService = projectService}

  ngOnInit(): void {
    this.loadProjects();
    this.checkAuth();
  }
  projectService: ProjectService;
  projects : Project[];
  newProject: Project;
  editedProject: Project;
  originalProject: Project;
  closeResult;
  edit=false;
  new=false;
  public roles: string[];
  public authority: string;
  public admin: string;
  loadProjects(){
    this.projectService.getProjects().subscribe((t) => {
      this.projects = t;
  });
}
addProject(content) {
 this.new = true;
 // this.selectedUser = {...this.originalUser};
  
  this.newProject = new Project();
  this.modalService.open(content).result.then((result) => {
    this.closeResult = `Closed with: ${result}`;
    
  }, (reason) => {
    this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
  });
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
onSave(){
  if(this.new){
    this.projectService.addProject(this.newProject).subscribe( t=>{this.projects.unshift(t)});
    this.new = false;
  }
  if(this.edit){
  
    this.projectService.updateProject(this.newProject).subscribe( t => this.showUpdatedItem(t));
    this.edit = false;
  }
  this.newProject = undefined;
  this.modalService.dismissAll();
}

  close(){
  this.modalService.dismissAll();
  this.newProject = undefined;
  this.originalProject = undefined;

  this.edit = false;
  this.new = false;
  }

deleteProject(id: number){
  this.projectService.deleteProject(id).subscribe(
    t => {
      var dp = this.projects.find(a => a.id == id);
      const index = this.projects.indexOf(dp, 0);
      if (index > -1) {
        this.projects.splice(index, 1);
      }
    } 
  );
}
confirmDelete(id: number, name: string) {
  if(confirm("Biztos hogy törlöt a "+ name + "projektet?")) {
    this.deleteProject(id);
  }
}
editProject(content, project : Project){
    this.edit = true;
    this.newProject = {...project};
     
     this.modalService.open(content).result.then((result) => {
       this.closeResult = `Closed with: ${result}`;
     }, (reason) => {
       this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
     });
   }

   showUpdatedItem(newItem){
    let updateItem = this.projects.find(this.findIndexToUpdate, newItem.id);
    let index = this.projects.indexOf(updateItem);
    this.projects[index] = newItem;

  }

  findIndexToUpdate(newItem) { 
        return newItem.id === this;
  }
  makeUrl(id: number){
    return "http://localhost:4200/project/".concat(id.toString());
  }
  makeUrlAll(id: number){
    return "http://localhost:4200/projectall/".concat(id.toString());
  }
  checkAuth() {
    this.authority = undefined;
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every((role) => {
        if (role === "ROLE_ADMIN") {
          this.authority = "admin";
          return false;
        }
        this.authority = "user";
        return true;
      });
    }
  }
}

