import { Component, OnInit } from '@angular/core';
import {ProjectService} from '../services/project.service';
import {Project} from '../model/project'
import { ActivatedRoute } from "@angular/router";
import { TokenStorageService } from "../auth/token-storage.service";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private tokenStorage: TokenStorageService,
    projectService : ProjectService) {this.projectService = projectService}
    projectService:ProjectService;
  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      let projectId = +params["id"];
      console.log(projectId);
      if (this.tokenStorage.getToken()) {
        var username = this.tokenStorage.getUsername();
      console.log(username);
      }
      //var v = this.projectService.getProjectById( projectId).subscribe(t => console.log(t));
      var f = this.projectService.getTasksByUserAndProject(username, projectId).subscribe(t => this.project = t);
      //var v = this.projectService.getProjectByUsername( username).subscribe(t => console.log(t));
    });
  }
  project : Project;
  

}