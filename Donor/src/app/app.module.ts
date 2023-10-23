import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PortingComponent } from './porting/porting.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { ValidateRequestsComponent } from './validate-requests/validate-requests.component';
import { DeactivationComponent } from './deactivation/deactivation.component';
import { SubscribersComponent } from './subscribers/subscribers.component';
@NgModule({
  declarations: [
    AppComponent,
    PortingComponent,
    HomeComponent,
    ValidateRequestsComponent,
    DeactivationComponent,
    SubscribersComponent
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
