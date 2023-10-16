import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { messageDTO } from './messageDTO';

@Injectable({
  providedIn: 'root'
})
export class MNPSPService {

  constructor(private http : HttpClient) { }

  Validate(mobileNumber : string) : Observable<messageDTO>{
    return this.http.get<messageDTO>(`http://localhost:8080/mnpsp/validate/${mobileNumber}`);
  }
}
