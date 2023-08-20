import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './modules/pages/user-list/user-list.component';
import { UserFormComponent } from './modules/pages/user-form/user-form.component';
import { BloodCenterListComponent } from './modules/pages/blood-center-list/blood-center-list.component';
import { QueryFormComponent } from './modules/pages/query-form/query-form.component';
import { UsersSearchComponent } from './modules/pages/users-search/users-search.component';
import { AddNewCentersComponent } from './modules/pages/add-new-centers/add-new-centers.component';
import { EditUserFormComponent } from './modules/pages/edit-user-form/edit-user-form.component';
import { EditCenterComponent } from './modules/pages/edit-center/edit-center.component';
import { EditEmployeeComponent } from './modules/pages/edit-employee/edit-employee.component';
import { AddNewAdministratorComponent } from './modules/pages/add-new-administrator/add-new-administrator.component';
import { ScheduleCalendarComponent } from './modules/pages/schedule-calendar/schedule-calendar.component';
import { AppealComponent } from './modules/pages/appeal/appeal.component';
import { AppealResponseComponent } from './modules/pages/appeal-response/appeal-response.component';
import { LoginAdministratorComponent } from './modules/pages/login-administrator/login-administrator.component';
import { SetPasswordComponent } from './modules/pages/set-password/set-password.component';
import { EmployeeReportComponent } from './modules/pages/employee-report/employee-report.component';
import { ReportsListComponent } from './modules/pages/reports-list/reports-list.component';
import { UserLoginComponent } from './modules/pages/user-login/user-login.component';
import { HomeComponent } from './modules/pages/home/home.component';
import { UserHistoryComponent } from './modules/pages/user-history/user-history.component';
import { ScheduleAppointmentComponent } from './modules/pages/schedule-appointment/schedule-appointment.component';
import { UserScheduleAppointmentComponent } from './modules/pages/user-schedule-appointment/user-schedule-appointment.component';
import { UserAppointmentsComponent } from './modules/pages/user-appointments/user-appointments.component';
import { SearchScheduleAppointmentComponent } from './modules/pages/search-schedule-appointment/search-schedule-appointment.component';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'users', component: UserListComponent },
  { path: 'sign-up', component: UserFormComponent },
  { path: 'sign-in', component: UserLoginComponent },
  { path: 'blood-centers', component: BloodCenterListComponent },
  { path: 'query', component: QueryFormComponent },
  { path: 'users-search', component: UsersSearchComponent },
  { path: 'add-blood-center', component: AddNewCentersComponent },
  { path: 'edit-user', component: EditUserFormComponent },
  { path: 'edit-center', component: EditCenterComponent },
  { path: 'editemployee', component: EditEmployeeComponent },
  { path: 'add-new-administrator', component: AddNewAdministratorComponent },
  { path: 'schedule-calendar', component: ScheduleCalendarComponent },
  { path: 'send-appeal', component: AppealComponent },
  { path: 'response-appeal', component: AppealResponseComponent },
  { path: 'login-administrator', component: LoginAdministratorComponent },
  { path: 'set-password', component: SetPasswordComponent },
  { path: 'employee-report', component: EmployeeReportComponent },
  { path: 'report-list', component: ReportsListComponent },
  { path: 'user-history', component: UserHistoryComponent },
  { path: 'schedule-appointment', component: ScheduleAppointmentComponent },
  { path: 'user-schedule-appointment/:id', component: UserScheduleAppointmentComponent },
  { path: 'user-appointments', component: UserAppointmentsComponent },
  { path: 'search-schedule-appointment', component: SearchScheduleAppointmentComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
