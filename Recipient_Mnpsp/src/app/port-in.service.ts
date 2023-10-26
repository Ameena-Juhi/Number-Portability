import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { RequestDTO } from './RequestDTO';
import { messageDTO } from './messageDTO';
import { CAF } from './CAF';
import { SubDetailsDto } from './SubDetailsDto';

@Injectable({
  providedIn: 'root'
})
export class PortingService {

  private recipientURL = 'http://localhost:8071/request/';

  constructor(private http : HttpClient) { }

  getUpc(Caf : CAF) : Observable<void>{
    return this.http.post<void>(`${this.recipientURL}portin`,Caf);
  }

  getAllPortingRquests(): Observable<CAF[]>{
    return this.http.get<CAF[]>(`${this.recipientURL}allrequests`);
  }

  saveActivationReq(activationRequest:RequestDTO ):Observable<void>{
    return this.http.post<void>(`${this.recipientURL}saveRequest`,activationRequest);
  }

  getAllActivationReq():Observable<RequestDTO[]>{
    return this.http.get<RequestDTO[]>(`${this.recipientURL}activationRequests`);
  }

  getRequestActivation(request: RequestDTO): Observable<messageDTO>{
    return this.http.post<messageDTO>(`${this.recipientURL}activation`,request);
  }

  getSubDetails():Observable<SubDetailsDto[]>{
    return this.http.get<SubDetailsDto[]>(`${this.recipientURL}getSubscribers`);
  }

  cancelRequest(mobileNumber:messageDTO):Observable<messageDTO>{
    return this.http.post<messageDTO>(`${this.recipientURL}cancelRO`,mobileNumber);
  }

}
