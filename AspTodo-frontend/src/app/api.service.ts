import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { User } from "./user";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class ApiService {
  constructor(private http: HttpClient) {}
  baseUrl: string = "http://localhost:8080/api/";

  login(loginPayload) {
    const headers = {
      Authorization: "Basic " + btoa("devglan-client:devglan-secret"),
      "Content-type": "application/x-www-form-urlencoded",
    };
    console.log(loginPayload);
    return this.http.post(
      "http://localhost:8080/" + "oauth/token",
      loginPayload,
      { headers }
    );
  }

  getUsers() {
    return this.http.get(
      this.baseUrl +
        "user?access_token=" +
        JSON.parse(window.sessionStorage.getItem("token")).access_token
    );
  }

  getUserById(id: number) {
    return this.http.get(
      this.baseUrl +
        "user/" +
        id +
        "?access_token=" +
        JSON.parse(window.sessionStorage.getItem("token")).access_token
    );
  }

  createUser(user: User) {
    return this.http.post(
      "http://localhost:8080/api/user?access_token=" +
        JSON.parse(window.sessionStorage.getItem("token")).access_token,
      user
    );
  }

  updateUser(user: User): Observable<User> {
    return <Observable<User>>(
      this.http.put(
        this.baseUrl +
          "user/" +
          user.id +
          "?access_token=" +
          JSON.parse(window.sessionStorage.getItem("token")).access_token,
        user
      )
    );
  }

  deleteUser(id: number) {
    return this.http.delete(
      this.baseUrl +
        "user/" +
        id +
        "?access_token=" +
        JSON.parse(window.sessionStorage.getItem("token")).access_token
    );
  }
}
