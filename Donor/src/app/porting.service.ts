import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { CafDTO } from './cafDTO';
import { RequestDTO } from './RequestDTO';
import { MsgDTO } from './MsgDTO';

@Injectable({
  providedIn: 'root'
})
export class PortingService {

  constructor(private http : HttpClient) { }

  getUpc(sms : string) : Observable<string>{

    return this.http.post<string>("http://localhost:8081/operator/port",{sms});
  }

  getCAF(form : CafDTO): Observable<boolean>{
    return this.http.post<boolean>("http://localhost:8081/operator/forwardcaf",form);
  }

  requestDeactivation(request : RequestDTO): Observable<MsgDTO>{
    return this.http.post<MsgDTO>("http://localhost:8081/operator/deactivation",request);
  }
  
}
