import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Employee } from "../model/employee";
@Injectable({
  providedIn: "root",
})
export class HttpClientService {
  constructor(private httpClient: HttpClient) {}

  getEmployees() {
    return this.httpClient.get<Employee[]>("http://localhost:8080/api/user");
  }

  public deleteEmployee(employee) {
    return this.httpClient.delete<Employee>(
      "http://localhost:8080/employees" + "/" + employee.empId
    );
  }

  public createEmployee(employee) {
    return this.httpClient.post<Employee>(
      "http://localhost:8080/api/user",
      employee
    );
  }
}
