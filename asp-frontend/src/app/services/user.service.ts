import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private BASE_URL = "http://localhost:8080/api/user";

  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    return <Observable<User[]>>this.http.get(this.BASE_URL);
  }
  updateUser(user: User){
    console.log(user);
    return this.http.patch(this.BASE_URL, user);
  }
  getNames():Observable<User[]>{
    return <Observable<User[]>>this.http.get(this.BASE_URL.concat("/names"));
  }
  deleteUser(id: number){
    return this.http.delete(this.BASE_URL.concat("/").concat(id.toString()));
  }
}
