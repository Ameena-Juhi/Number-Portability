import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { messageDTO } from './messageDTO';
import { status } from './status';
import { validationClearancedto } from './ValidationClearancedto';
import { CAF } from './CAF';

@Injectable({
  providedIn: 'root'
})
export class MNPSPService {

  constructor(private http : HttpClient) { }

  Validate(form : CAF) : Observable<messageDTO>{
    return this.http.post<messageDTO>(`http://localhost:8083/mnpsp/sendcaf`,form);
  }

  scheduleTime(mobNum: messageDTO):Observable<Date>{
    return this.http.post<Date>("http://localhost:8083/mnpsp/scheduleport",mobNum);
  }

  getAllStatus(): Observable<status[]>{
    return this.http.get<status[]>("http://localhost:8083/mnpsp/all");
  }

  processToDonor(form: CAF):Observable<boolean>{
    return this.http.post<boolean>("http://localhost:8083/mnpsp/processform",form);
  }

  getClearance(mobNum:messageDTO):Observable<boolean>{
    return this.http.post<boolean>("http://localhost:8083/mnpsp/getClearance",mobNum);
  }

  getDonorClearance(clearance:validationClearancedto):Observable<void>{
    return this.http.post<void>("http://localhost:8083/mnpsp/deactivationClearance",clearance);
  }

  getRecipientClearance(clearance:validationClearancedto):Observable<void>{
    return this.http.post<void>("http://localhost:8083/mnpsp/activationClearance",clearance);
  }

  updateNumDB(mobileNumber: messageDTO):Observable<boolean>{
    return this.http.post<boolean>("http://localhost:8083/mnpsp/updateNumDb",mobileNumber);
  }

  cancelReqMNPSP(mobileNumber:messageDTO):Observable<messageDTO>{
    return this.http.post<messageDTO>("http://localhost:8083/mnpsp/cancelMNPSP",mobileNumber);
  }
}
