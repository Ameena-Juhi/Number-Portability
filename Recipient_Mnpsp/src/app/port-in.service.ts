import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { RequestDTO } from './RequestDTO';
import { messageDTO } from './messageDTO';
import { CafDTO } from './CafDTO';

@Injectable({
  providedIn: 'root'
})
export class PortingService {

  constructor(private http : HttpClient) { }

  getUpc(Caf : CafDTO) : Observable<void>{
    return this.http.post<void>("http://localhost:8082/request/portin",Caf);
  }

  getAllPortingRquests(): Observable<CafDTO[]>{
    return this.http.get<CafDTO[]>("http://localhost:8082/request/allrequests");
  }

  saveActivationReq(activationRequest:RequestDTO ):Observable<void>{
    return this.http.post<void>("http://localhost:8082/request/saveRequest",activationRequest);
  }

  getAllActivationReq():Observable<RequestDTO[]>{
    return this.http.get<RequestDTO[]>("http://localhost:8082/request/activationRequests");
  }

  getRequestActivation(request: RequestDTO): Observable<messageDTO>{
    return this.http.post<messageDTO>("http://localhost:8082/request/activation",request);
  }

}
