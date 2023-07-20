import { NgModule } from '@angular/core';
import { AddNewAdministratorComponent } from "./add-new-administrator/add-new-administrator.component";
import { AddNewCentersComponent } from "./add-new-centers/add-new-centers.component";
import { AppealResponseComponent } from "./appeal-response/appeal-response.component";
import { AppealComponent } from "./appeal/appeal.component";
import { BloodCenterListComponent } from "./blood-center-list/blood-center-list.component";
import { EditCenterComponent } from "./edit-center/edit-center.component";
import { EditEmployeeComponent } from "./edit-employee/edit-employee.component";
import { EditUserFormComponent } from "./edit-user-form/edit-user-form.component";
import { EmployeeReportComponent } from "./employee-report/employee-report.component";
import { LoginAdministratorComponent } from "./login-administrator/login-administrator.component";
import { QueryFormComponent } from "./query-form/query-form.component";
import { ReportsListComponent } from "./reports-list/reports-list.component";
import { ScheduleCalendarComponent } from "./schedule-calendar/schedule-calendar.component";
import { SetPasswordComponent } from "./set-password/set-password.component";
import { UserFormComponent } from "./user-form/user-form.component";
import { UserListComponent } from "./user-list/user-list.component";
import { UsersSearchComponent } from "./users-search/users-search.component";
import { CommonModule } from "@angular/common";
import { AppRoutingModule } from "src/app/app-routing.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MaterialModule } from "src/app/material/material.module";
import { BloodCenterAdminComponent } from './blood-center-admin/blood-center-admin.component';
import { UserLoginComponent } from './user-login/user-login.component';

@NgModule(
    {
        declarations: [
            AddNewAdministratorComponent,
            AddNewCentersComponent,
            AppealComponent,
            AppealResponseComponent,
            BloodCenterListComponent,
            EditCenterComponent,
            EditEmployeeComponent,
            EditUserFormComponent,
            EmployeeReportComponent,
            LoginAdministratorComponent,
            QueryFormComponent,
            ReportsListComponent,
            ScheduleCalendarComponent,
            SetPasswordComponent,
            UserFormComponent,
            UserListComponent,
            UserLoginComponent,
            UsersSearchComponent,
            BloodCenterAdminComponent
        ],
        imports: [
            CommonModule,
            AppRoutingModule,
            ReactiveFormsModule,
            FormsModule,
            MaterialModule,
        ],
    }
)
export class PagesModule { }