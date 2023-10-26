import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PortingComponent } from './porting/porting.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ValidateRequestsComponent } from './validate-requests/validate-requests.component';
import { DeactivationComponent } from './deactivation/deactivation.component';
import { SubscribersComponent } from './subscribers/subscribers.component';
import { CancelComponent } from './cancel/cancel.component';
import { LoginComponent } from './login/login.component';
import { HttpInterceptorService } from './HttpInterceptorService';

@NgModule({
  declarations: [
    AppComponent,
    PortingComponent,
    ValidateRequestsComponent,
    DeactivationComponent,
    SubscribersComponent,
    CancelComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
