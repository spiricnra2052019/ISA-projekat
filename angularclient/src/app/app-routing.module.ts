import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BloodCenterListComponent } from './blood-center-list/blood-center-list.component';
import { QueryFormComponent } from './query-form/query-form.component';
import { UserFormComponent } from './user-form/user-form.component';
import { UserListComponent } from './user-list/user-list.component';
import { NikolaTestComponent } from './nikola-test/nikola-test.component';
import { EditUserFormComponent } from './edit-user-form/edit-user-form.component';

const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: 'adduser', component: UserFormComponent },
  { path: 'blood-center', component: BloodCenterListComponent },
  { path: 'query', component: QueryFormComponent },
  { path: 'nikola', component: NikolaTestComponent },
  { path: 'edituser', component: EditUserFormComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
