import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { messageDTO } from './messageDTO';
import { status } from './status';
import { validationClearancedto } from './ValidationClearancedto';
import { CAF } from './CAF';
import { CAFtoken } from './CAFtoken';

@Injectable({
  providedIn: 'root'
})
export class MNPSPService {

  private mnpspURL = 'http://localhost:8071/mnpsp/';

  constructor(private http : HttpClient) { }

  Validate(form : CAF) : Observable<messageDTO>{
    return this.http.post<messageDTO>(`${this.mnpspURL}sendcaf`,form);
  }

  scheduleTime(mobNum: messageDTO):Observable<Date>{
    return this.http.post<Date>(`${this.mnpspURL}scheduleport`,mobNum);
  }

  getAllStatus(): Observable<status[]>{
    return this.http.get<status[]>("http://localhost:8071/mnpsp/all");
  }

  processToDonor(form: CAFtoken):Observable<boolean>{
    return this.http.post<boolean>(`${this.mnpspURL}processform`,form);
  }

  getClearance(mobNum:messageDTO):Observable<boolean>{
    return this.http.post<boolean>(`${this.mnpspURL}getClearance`,mobNum);
  }

  getDonorClearance(clearance:validationClearancedto):Observable<void>{
    return this.http.post<void>(`${this.mnpspURL}deactivationClearance`,clearance);
  }

  getRecipientClearance(clearance:validationClearancedto):Observable<void>{
    return this.http.post<void>(`${this.mnpspURL}activationClearance`,clearance);
  }

  updateNumDB(mobileNumber: messageDTO):Observable<boolean>{
    return this.http.post<boolean>(`${this.mnpspURL}updateNumDb`,mobileNumber);
  }

  cancelReqMNPSP(mobileNumber:messageDTO):Observable<messageDTO>{
    return this.http.post<messageDTO>(`${this.mnpspURL}cancelMNPSP`,mobileNumber);
  }
}
