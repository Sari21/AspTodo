import { Component, OnInit } from '@angular/core';
import {ProjectService} from '../services/project.service';
import {Project} from '../model/project'
import { NgbModal, ModalDismissReasons } from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  constructor(projectService : ProjectService, private modalService: NgbModal)
   { this.projectService = projectService}

  ngOnInit(): void {
    this.loadProjects();
  }
  projectService: ProjectService;
  projects : Project[];
  newProject: Project;
  closeResult;
  loadProjects(){
    this.projectService.getProjects().subscribe((t) => {
      this.projects = t;
  });
}
open(content) {
 
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
  this.projectService.addProject(this.newProject).subscribe( t=>{this.loadProjects()});
 
  this.newProject = undefined;
  this.modalService.dismissAll();
}
deleteProject(id: number){
  this.projectService.deleteProject(id).subscribe(
    t => {this.loadProjects();}
  );
}
}
