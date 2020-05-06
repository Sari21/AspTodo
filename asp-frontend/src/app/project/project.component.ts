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
      //this.getCompany(companyId);
      if (this.tokenStorage.getToken()) {
        var username = this.tokenStorage.getUsername();
      //this.createCompany = false;
      console.log(username);
      }
      var v = this.projectService.getTasksByUserAndProject(username, projectId).subscribe(t => console.log(t));
      
    });
  }

}