import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PortingComponent } from './porting/porting.component';
import { HttpClientModule } from '@angular/common/http';
import { ValidateRequestsComponent } from './validate-requests/validate-requests.component';
import { DeactivationComponent } from './deactivation/deactivation.component';
import { SubscribersComponent } from './subscribers/subscribers.component';
import { CancelComponent } from './cancel/cancel.component';
@NgModule({
  declarations: [
    AppComponent,
    PortingComponent,
    ValidateRequestsComponent,
    DeactivationComponent,
    SubscribersComponent,
    CancelComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
