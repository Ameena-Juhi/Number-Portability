import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MNPSPComponent } from './mnpsp/mnpsp.component';
import { PortInComponent } from './port-in/port-in.component';
import { StatusCheckComponent } from './status-check/status-check.component';
import { MnpspValidationComponent } from './mnpsp-validation/mnpsp-validation.component';
import { ActivationComponent } from './activation/activation.component';

const routes: Routes = [
  {path : '', component:PortInComponent},
  { path: 'forward', component: MNPSPComponent},
  { path: 'status', component:StatusCheckComponent},
  { path: 'mnpsp',component:MnpspValidationComponent},
  { path:'activate',component:ActivationComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
