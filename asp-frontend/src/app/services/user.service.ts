import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from "../model/user";

@Injectable({
  providedIn: 'root'
})export class UserService {
  private userUrl = "http://localhost:8080/api/user";

  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    return <Observable<User[]>>this.http.get(this.userUrl);
  }
  updateUser(user: User){
    return this.http.put(this.userUrl, user);
  }
}
