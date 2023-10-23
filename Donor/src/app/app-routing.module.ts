import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PortingComponent } from './porting/porting.component';
import { ValidateRequestsComponent } from './validate-requests/validate-requests.component';
import { DeactivationComponent } from './deactivation/deactivation.component';
import { SubscribersComponent } from './subscribers/subscribers.component';

const routes: Routes = [
  {path:'',component:PortingComponent},
  {path:'validate',component:ValidateRequestsComponent},
  {path:'deactivate',component:DeactivationComponent},
  { path:'subscribers', component:SubscribersComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
