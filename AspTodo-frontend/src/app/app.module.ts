import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { EmployeeComponent } from "./employee/employee.component";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { AddEmployeeComponent } from "./add-employee/add-employee.component";
import { FormsModule } from "@angular/forms";
import { HeaderComponent } from "./header/header.component";
import { LoginComponent } from "./login/login.component";
import { LogoutComponent } from "./logout/logout.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { BasicAuthHtppInterceptorService } from "./service/basic-auth-interceptor.service";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    LogoutComponent,
    EmployeeComponent,
    AddEmployeeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BasicAuthHtppInterceptorService,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
