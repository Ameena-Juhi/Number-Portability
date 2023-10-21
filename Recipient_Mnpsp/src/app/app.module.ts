import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PortInComponent } from './port-in/port-in.component';
import { MNPSPComponent } from './mnpsp/mnpsp.component';
import { StatusCheckComponent } from './status-check/status-check.component';

@NgModule({
  declarations: [
    AppComponent,
    PortInComponent,
    MNPSPComponent,
    StatusCheckComponent
    
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
