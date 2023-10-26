import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PortInComponent } from './port-in/port-in.component';
import { MNPSPComponent } from './mnpsp/mnpsp.component';
import { StatusCheckComponent } from './status-check/status-check.component';
import { MnpspValidationComponent } from './mnpsp-validation/mnpsp-validation.component';
import { ActivationComponent } from './activation/activation.component';
import { SubscribersComponent } from './subscribers/subscribers.component';
import { LoginComponent } from './login/login.component';
import { HttpInterceptorService } from './HttpInterceptorService';

@NgModule({
  declarations: [
    AppComponent,
    PortInComponent,
    MNPSPComponent,
    StatusCheckComponent,
    MnpspValidationComponent,
    ActivationComponent,
    SubscribersComponent,
    LoginComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ {
    provide: HTTP_INTERCEPTORS,
    useClass: HttpInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
