import { Component, OnInit } from "@angular/core";
import { UserService } from "../../services/user.service";
import { User } from "../../model/user";
import { NgbModal, ModalDismissReasons } from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: "app-users",
  templateUrl: "./users.component.html",
  styleUrls: ["./users.component.css"],
})
export class UsersComponent implements OnInit {
  constructor(user: UserService, private modalService: NgbModal) {
    this.userService = user;
  }
  users: User[];
  userService: UserService;
  editUser: User;
  editing = false;
  closeResult = "";
  ngOnInit(): void {
    this.userService.getUsers().subscribe((t) => {
      this.users = t;
      console.log(t);
    });
  }
  edit(user: User) {
    this.editing = true;
    console.log(user);
  }
}
