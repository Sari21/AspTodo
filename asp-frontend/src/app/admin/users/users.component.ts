import { Component, OnInit } from "@angular/core";
import { UserService } from "../../services/user.service";
import { User } from "../../model/user";
import { NgbModal, ModalDismissReasons } from "@ng-bootstrap/ng-bootstrap";
import { Role } from "src/app/model/role";

@Component({
  selector: "app-users",
  templateUrl: "./users.component.html",
  styleUrls: ["./users.component.css"],
})
export class UsersComponent implements OnInit {
  title = "appBootstrap";
  closeResult: string;
  constructor(user: UserService, private modalService: NgbModal) {
    this.userService = user;
  }
  users: User[];
  userService: UserService;
  selectedUser: User;
  originalUser: User;
  editing = false;
  isAdmin: boolean;
  failed = false;
  errorMessage = 'Nem sikerült módosítani a felhasználót';
  ngOnInit(): void {
    this.loadUsers();
  }
  edit(user: User) {
    this.editing = true;
    console.log(user);
  }
  open(content, id: number) {
    this.failed = false;
    this.selectedUser = this.users.find((t) => t.id == id);
     this.originalUser = {...this.selectedUser};

    this.modalService.open(content);

  }
  submit() {
    if (this.selectedUser.isAdmin) {
      this.selectedUser.roles = [new Role("ROLE_ADMIN"), new Role("ROLE_USER")];
    } else {
      this.selectedUser.roles = [new Role("ROLE_USER")];
    }

    //this.originalUser = this.selectedUser;
    this.userService
      .updateUser(this.selectedUser)
      .subscribe((data) =>{},
      (error) => {console.log(error),
        this.failed = true;
        var mu = this.users.find((a) => a.id == this.selectedUser.id);
        const index = this.users.indexOf(mu, 0);
        if (index > -1) {
          this.users[index] = this.originalUser;
        }
     });
  }
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return "by pressing ESC";
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return "by clicking on a backdrop";
    } else {
      return `with: ${reason}`;
    }
  }
  loadUsers() {
    this.userService.getUsers().subscribe((t) => {
      this.users = t;
      this.users.forEach((u) => {
        console.log(u);
        u.isAdmin = false;
        u.roles.forEach((role) => {
          console.log(role);
          if (role.name === "ROLE_ADMIN") {
            u.isAdmin = true;
          }
        });
      });
    });
  }
  close() {
    this.modalService.dismissAll();
    this.failed = false;
  }
  confirmDelete(id: number, name: string) {
    if (confirm("Biztos hogy törlöd a(z) " + name + "felhasználót?")) {
      this.delete(id);
    }
  }
  delete(id: number) {
    this.userService.deleteUser(id).subscribe(
      (data) => {
        var du = this.users.find((a) => a.id == id);
        const index = this.users.indexOf(du, 0);
        if (index > -1) {
          this.users.splice(index, 1);
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
