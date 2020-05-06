import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project} from '../model/project'
@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private userUrl = "http://localhost:8080/api";

  constructor(private http: HttpClient) {}

  getProjects(): Observable<Project[]> {
    return <Observable<Project[]>>this.http.get(this.userUrl.concat("/project"));
  }
  addProject(project:Project): Observable<Project> {
    return <Observable<Project>>this.http.post(this.userUrl.concat("/project"), project);
  }
  updateProject(project:Project): Observable<Project> {
    return <Observable<Project>>this.http.put(this.userUrl.concat("/project"), project);
  }
  deleteProject(id: number){
    return <Observable<Project[]>>this.http.delete(this.userUrl.concat("/project/").concat(id.toString()));
  }
  getTasksByUserAndProject(username : string, projectId: number){
    return <Observable<Project[]>>this.http.get(this.userUrl.concat("/task/user/").concat(username).concat("/project/").concat(projectId.toString()));
  }
 
}
