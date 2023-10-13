import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PortingService {

  constructor(private http : HttpClient) { }

  getUpc(sms : string) : Observable<string>{
    return this.http.post<string>("http://localhost:8080/operator/port",sms);
  }
}
