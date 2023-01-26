import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BloodCenterListComponent } from './components/blood-center-list/blood-center-list.component';
import { QueryFormComponent } from './components/query-form/query-form.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { UsersSearchComponent } from './components/users-search/users-search.component';
import { AddNewCentersComponent } from './components/add-new-centers/add-new-centers.component';
import { EditUserFormComponent } from './components/edit-user-form/edit-user-form.component';
import { EditCenterComponent } from './components/edit-center/edit-center.component';
import { EditEmployeeComponent } from './components/edit-employee/edit-employee.component';
import { AddNewAdministratorComponent } from './components/add-new-administrator/add-new-administrator.component';
import { ScheduleCalendarComponent } from './components/schedule-calendar/schedule-calendar/schedule-calendar.component';
import { AppealComponent } from './components/appeal/appeal.component';
import { AppealResponseComponent } from './components/appeal-response/appeal-response.component';
import { LoginAdministratorComponent } from './components/login-administrator/login-administrator.component';
import { SetPasswordComponent } from './components/set-password/set-password.component';
import { EmployeeReportComponent } from './components/employee-report/employee-report/employee-report.component';
import { ReportsListComponent } from './components/reports-list/reports-list/reports-list.component';


const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: 'adduser', component: UserFormComponent },
  { path: 'blood-centers', component: BloodCenterListComponent },
  { path: 'query', component: QueryFormComponent },
  { path: 'users-search', component: UsersSearchComponent },
  { path: 'add-blood-center', component: AddNewCentersComponent },
  { path: 'edituser', component: EditUserFormComponent },
  { path: 'edit-center', component: EditCenterComponent },
  { path: 'editemployee', component: EditEmployeeComponent },
  { path: 'add-new-administrator', component: AddNewAdministratorComponent },
  { path: 'schedule-calendar', component: ScheduleCalendarComponent },
  { path: 'send-appeal', component: AppealComponent },
  { path: 'response-appeal', component: AppealResponseComponent },
  { path: 'login-administrator', component: LoginAdministratorComponent },
  { path: 'set-password', component: SetPasswordComponent },
  { path: 'set-password', component: SetPasswordComponent },
  { path: 'employee-report', component: EmployeeReportComponent },
  { path: 'report-list', component: ReportsListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
