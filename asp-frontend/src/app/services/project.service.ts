import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project} from '../model/project'
import {Task} from '../model/task';
@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private BASE_URL = "http://localhost:8080/api";

  constructor(private http: HttpClient) {}

  getProjects(): Observable<Project[]> {
    return <Observable<Project[]>>this.http.get(this.BASE_URL.concat("/project"));
  }
  addProject(project:Project): Observable<Project> {
    return <Observable<Project>>this.http.post(this.BASE_URL.concat("/project"), project);
  }
  updateProject(project:Project): Observable<Project> {
    return <Observable<Project>>this.http.put(this.BASE_URL.concat("/project"), project);
  }
  deleteProject(id: number){
    return <Observable<Project[]>>this.http.delete(this.BASE_URL.concat("/project/").concat(id.toString()));
  }
  getTasksByUserAndProject(username : string, projectId: number): Observable<Project> {
    return <Observable<Project>>this.http.get(this.BASE_URL.concat("/project/user/").concat(username).concat("/project/").concat(projectId.toString()));
  }
  getProjectById(id :number)  :Observable<Project>{
    return <Observable<Project>>this.http.get(this.BASE_URL.concat("/project/").concat(id.toString()));
  }
  getProjectByUsername(username :string)  :Observable<Task>{
    return <Observable<Task>>this.http.get(this.BASE_URL.concat("/task/username/").concat(username));
  }
  addTask(task: Task, projectId: number): Observable<Task> {
    return <Observable<Task>>this.http.post(this.BASE_URL.concat("/task/project/").concat(projectId.toString()), task);
  }
  deleteTask(taskId: number, projectId: number){
    return this.http.delete(this.BASE_URL.concat("/task/").concat(taskId.toString()).concat("/project/").concat(projectId.toString()));
  }
  updateTaskIsDone(id: number):Observable<Task>{
    return <Observable<Task>>this.http.patch(this.BASE_URL.concat("/task/").concat(id.toString()), null);
  }
 
}
