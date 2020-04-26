import { Component, OnInit, Input } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { HttpParams } from "@angular/common/http";
import { Router } from "@angular/router";
import { ApiService } from "../api.service";
@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  invalidLogin: boolean = false;
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private apiService: ApiService
  ) {}

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }
    const body = new HttpParams()
      .set("username", this.loginForm.controls.username.value)
      .set("password", this.loginForm.controls.password.value)
      .set("grant_type", "password");

    this.apiService.login(body.toString()).subscribe(
      (data) => {
        window.sessionStorage.setItem("token", JSON.stringify(data));
        console.log(window.sessionStorage.getItem("token"));
        this.router.navigate(["list-user"]);
      },
      (error) => {
        alert(error.error.error_description);
        console.log(error);
      }
    );
  }

  ngOnInit() {
    window.sessionStorage.removeItem("token");
    this.loginForm = this.formBuilder.group({
      username: ["", Validators.compose([Validators.required])],
      password: ["", Validators.required],
    });
  }
}
