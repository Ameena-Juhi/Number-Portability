import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { RequestDTO } from './RequestDTO';
import { Observable } from 'rxjs';
import { messageDTO } from './messageDTO';

@Injectable({
  providedIn: 'root'
})
export class DonorService {

  constructor(private http: HttpClient) { }

  saveDeactivationReq(deactivationRequest:RequestDTO ):Observable<void>{
    return this.http.post<void>("http://localhost:8081/operator/saveRequest",deactivationRequest);
  }

  cancelDO(mobileNumber:messageDTO):Observable<messageDTO>{
    return this.http.post<messageDTO>("http://localhost:8081/operator/rejectDO",mobileNumber);
  }
}
