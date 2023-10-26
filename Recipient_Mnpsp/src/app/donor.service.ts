import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { RequestDTO } from './RequestDTO';
import { Observable } from 'rxjs';
import { messageDTO } from './messageDTO';

@Injectable({
  providedIn: 'root'
})
export class DonorService {

  private operatorURL = "http://localhost:8071/operator/";

  constructor(private http: HttpClient) { }

  saveDeactivationReq(deactivationRequest:RequestDTO ):Observable<void>{
    return this.http.post<void>(`${this.operatorURL}saveRequest`,deactivationRequest);
  }

  cancelDO(mobileNumber:messageDTO):Observable<messageDTO>{
    return this.http.post<messageDTO>(`${this.operatorURL}rejectDO`,mobileNumber);
  }
}
