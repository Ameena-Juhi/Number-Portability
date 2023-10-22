import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { RequestDTO } from './RequestDTO';
import { MsgDTO } from './MsgDTO';
import { CafDTO } from './cafDTO';

@Injectable({
  providedIn: 'root'
})
export class PortingService {

  constructor(private http : HttpClient) { }

  getUpc(sms : string) : Observable<string>{

    return this.http.post<string>("http://localhost:8081/operator/port",{sms});
  }

  getAllRequests():Observable<CafDTO[]>{
    return this.http.get<CafDTO[]>("http://localhost:8081/operator/getRequests");
  }

  validate(form : CafDTO): Observable<boolean>{
    return this.http.post<boolean>("http://localhost:8081/operator/validate",form);
  }

  getAllDeactReqs():Observable<RequestDTO[]>{
    return this.http.get<RequestDTO[]>("http://localhost:8081/operator/allDeactReqs");
  }

  requestDeactivation(request : RequestDTO): Observable<MsgDTO>{
    return this.http.post<MsgDTO>("http://localhost:8081/operator/deactivation",request);
  }
  
}
