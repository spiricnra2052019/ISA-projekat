import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BloodCenterListComponent } from './blood-center-list/blood-center-list.component';
import { QueryFormComponent } from './query-form/query-form.component';
import { UserFormComponent } from './user-form/user-form.component';
import { UserListComponent } from './user-list/user-list.component';
import { NikolaTestComponent } from './administrator-search/nikola-test.component';
import { AddNewCentersComponent } from './add-new-centers/add-new-centers.component';
import { EditUserFormComponent } from './edit-user-form/edit-user-form.component';
import { EditCenterComponent } from './edit-center/edit-center.component';
import { EditEmployeeComponent } from './edit-employee/edit-employee.component';

const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: 'adduser', component: UserFormComponent },
  { path: 'blood-centers', component: BloodCenterListComponent },
  { path: 'query', component: QueryFormComponent },
  { path: 'nikola', component: NikolaTestComponent },
  { path: 'add-blood-center', component: AddNewCentersComponent },
  { path: 'edituser', component: EditUserFormComponent },
  { path: 'edit-center', component: EditCenterComponent },
  { path: 'editemployee', component: EditEmployeeComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
