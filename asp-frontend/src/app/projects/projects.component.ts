import { Component, OnInit } from '@angular/core';
import {ProjectService} from '../services/project.service';
import {Project} from '../model/project'
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
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
  failed = false;
  errorMessage = '';
  loadProjects(){
    this.projectService.getProjects().subscribe((t) => {
      this.projects = t;
  });
}
//új project hozzáadása
addProject(content) {
 this.new = true;
  this.failed= false;
  this.newProject = new Project();
  this.modalService.open(content);
}
//új / szerkesztett projekt mentése
onSave(){
  //új
  if(this.new){
    this.projectService.addProject(this.newProject).subscribe( 
      (data)=>{this.projects.unshift(data);
        this.new = false;
        this.close();
        this.newProject = undefined;
      },
      (error) => {
        this.errorMessage= "Nem sikerült hozzáadni a projektet";
        this.failed = true;
      }
      );
  }
  //szerkesztett
  if(this.edit){
  
    this.projectService.updateProject(this.newProject).subscribe( data => {
      this.showUpdatedItem(data);
      this.edit = false;
      this.close();
      this.newProject = undefined;
    }); 
  }
}
//modal bezárása
  close(){
  this.modalService.dismissAll();
  this.newProject = undefined;
  this.originalProject = undefined;

  this.edit = false;
  this.new = false;
  this.failed = false;
  }
//project törlése
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
//törlés megerősítése
confirmDelete(id: number, name: string) {
  if(confirm("Biztos hogy törlöt a "+ name + "projektet?")) {
    this.deleteProject(id);
  }
}
//project szerkesztése
editProject(content, project : Project){
    this.edit = true;
    this.newProject = {...project};
     
     this.modalService.open(content);
}
//a szerkesztett adat módosítása a frontenden
   showUpdatedItem(newItem){
    let updateItem = this.projects.find(this.findIndexToUpdate, newItem.id);
    let index = this.projects.indexOf(updateItem);
    this.projects[index] = newItem;

  }
//index meghatározása
  findIndexToUpdate(newItem) { 
        return newItem.id === this;
  }
  //url készítése a project oldalhoz
  makeUrl(id: number){
    return "http://localhost:4200/project/".concat(id.toString());
  }
  //url meghatározása a project-all-tasks oldalhoz
  makeUrlAll(id: number){
    return "http://localhost:4200/projectall/".concat(id.toString());
  }
  //autentikáció ellenőrzése
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

