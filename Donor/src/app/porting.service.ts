import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { RequestDTO } from './RequestDTO';
import { MsgDTO } from './MsgDTO';
import { SubDetailsDto } from './SubDetailsDto';
import { RequestDTOLocale } from './RequestDTOLocale';
import { CafDTO } from './cafDTO';

@Injectable({
  providedIn: 'root'
})
export class PortingService {

  private operatorURL = 'http://localhost:8071/operator/';

  constructor(private http : HttpClient) { }

  getUpc(sms : MsgDTO) : Observable<MsgDTO>{
    return this.http.post<MsgDTO>("http://localhost:8071/subscriber/port",sms);
  }

  getAllRequests():Observable<CafDTO[]>{
    return this.http.get<CafDTO[]>(`${this.operatorURL}getRequests`);
  }

  validate(form : CafDTO): Observable<MsgDTO>{
    return this.http.post<MsgDTO>(`${this.operatorURL}validate`,form);
  }

  getAllDeactReqs():Observable<RequestDTOLocale[]>{
    return this.http.get<RequestDTOLocale[]>(`${this.operatorURL}allDeactReqs`);
  }

  requestDeactivation(request : RequestDTOLocale): Observable<MsgDTO>{
    return this.http.post<MsgDTO>(`${this.operatorURL}deactivation`,request);
  }

  getSubscribers():Observable<SubDetailsDto[]>{
    return this.http.get<SubDetailsDto[]>(`${this.operatorURL}getSubscribers`);
  }

  cancelRequest(sms:MsgDTO):Observable<MsgDTO>{
    return this.http.post<MsgDTO>("http://localhost:8071/subscriber/cancel",sms)
  }
  
}
