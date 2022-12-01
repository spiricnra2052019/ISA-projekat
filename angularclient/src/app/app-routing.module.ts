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
  { path: 'schedule-calendar', component: ScheduleCalendarComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
