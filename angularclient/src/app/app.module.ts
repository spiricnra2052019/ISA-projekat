import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserFormComponent } from './user-form/user-form.component';
import { RegisteredUserService } from './registered-user.service';
import { UserLoginComponent } from './user-login/user-login.component';
import { BloodCenterListComponent } from './blood-center-list/blood-center-list.component';
import { QueryFormComponent } from './query-form/query-form.component';
import { MatTableModule } from '@angular/material/table'
import { MatSortModule } from '@angular/material/sort';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NikolaTestComponent } from './nikola-test/nikola-test.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserFormComponent,
    UserLoginComponent,
    BloodCenterListComponent,
    QueryFormComponent,
    NikolaTestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatSortModule,
    MatTableModule,
    BrowserAnimationsModule
  ],
  providers: [RegisteredUserService],
  bootstrap: [AppComponent]
})
export class AppModule { }