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
  selectedUser: User;
  originalUser: User;
  editing = false;
  isAdmin: boolean;
  ngOnInit(): void {
    this.loadUsers();
  }
  edit(user: User) {
    this.editing = true;
    console.log(user);
  }
  open(content, id: number) {
    this.originalUser = this.users.find(t => t.id == id);
    this.selectedUser = {...this.originalUser};
    
    
    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
  submit(){
   
      if(this.isAdmin){
        //this.selectedUser.roles = ["ROLE_ADMIN", "ROLE_USER"];
      }
      else{
       // this.selectedUser.roles =["ROLE_USER"]
      }
    
    var u = this.users.find(t => t.id = this.selectedUser.id) ;
    u = this.selectedUser;
    this.userService.updateUser(u);
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
  loadUsers(){
    this.userService.getUsers().subscribe((t) => {
      this.users = t;
      this.users.forEach(u => {
        console.log(u);
        u.isAdmin = false;
        u.roles.forEach((role) => {
          console.log(role);
          if (role.name === "ROLE_ADMIN") {
            u.isAdmin = true;
            console.log(role);
           
          }
         
        });
        console.log(u.isAdmin);
    });
  });
}
}
