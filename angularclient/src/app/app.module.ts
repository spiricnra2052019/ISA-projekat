import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { RegisteredUserService } from './services/registered-user.service';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { BloodCenterListComponent } from './components/blood-center-list/blood-center-list.component';
import { QueryFormComponent } from './components/query-form/query-form.component';
import { MatTableModule } from '@angular/material/table'
import { MatSortModule } from '@angular/material/sort';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UsersSearchComponent } from './components/users-search/users-search.component';
import { AddNewCentersComponent } from './components/add-new-centers/add-new-centers.component';
import { EditUserFormComponent } from './components/edit-user-form/edit-user-form.component';
import { EditCenterComponent } from './components/edit-center/edit-center.component';
import { EditEmployeeComponent } from './components/edit-employee/edit-employee.component';
import { BloodCenterAdministrator } from './components/blood-center-admin/blood-center-admin.component';
import { AddNewAdministratorComponent } from './components/add-new-administrator/add-new-administrator.component';
import { ScheduleCalendarComponent } from './components/schedule-calendar/schedule-calendar/schedule-calendar.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';




@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserFormComponent,
    UserLoginComponent,
    BloodCenterListComponent,
    QueryFormComponent,
    UsersSearchComponent,
    AddNewCentersComponent,
    EditUserFormComponent,
    EditCenterComponent,
    EditEmployeeComponent,
    AddNewAdministratorComponent,
    ScheduleCalendarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatSortModule,
    MatTableModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatCardModule
  ],
  providers: [RegisteredUserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
