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
  title = 'appBootstrap';
  closeResult: string;
  constructor(user: UserService, private modalService: NgbModal) {
    this.userService = user;
  }
  users: User[];
  userService: UserService;
  editUser: User;
  editing = false;
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
  open(content) {
    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }
}
