import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { RequestDTO } from './RequestDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DonorService {

  constructor(private http: HttpClient) { }

  saveDeactivationReq(deactivationRequest:RequestDTO ):Observable<void>{
    return this.http.post<void>("http://localhost:8081/operator/saveRequest",deactivationRequest);
  }
}
