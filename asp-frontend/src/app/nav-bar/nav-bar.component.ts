import { Component, OnInit } from '@angular/core';

import { Router } from "@angular/router";

import { TokenStorageService } from "../auth/token-storage.service";
@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  public page: string;

  constructor(
    private router: Router,
    private tokenStorage: TokenStorageService
  ) {
    console.log(this.router.url);
    this.page = this.router.url;
  }

  public roles: string[];
  public authority: string;

  ngOnInit() {
    this.checkAuth();
  }
  logout() {
    this.tokenStorage.signOut();
    this.checkAuth();
  }
  checkAuth() {
    this.authority = undefined;
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every((role) => {
        if (role === "ROLE_ADMIN") {
          this.authority = "admin";
          return false;
        }
        this.authority = "user";
        return true;
      });
    }
  }
}
