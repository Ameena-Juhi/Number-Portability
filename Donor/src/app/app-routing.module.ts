import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PortingComponent } from './porting/porting.component';
import { ValidateRequestsComponent } from './validate-requests/validate-requests.component';
import { DeactivationComponent } from './deactivation/deactivation.component';

const routes: Routes = [
  {path:'',component:PortingComponent},
  {path:'validate',component:ValidateRequestsComponent},
  {path:'deactivate',component:DeactivationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
