import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { messageDTO } from './messageDTO';
import { CafDTO } from './CafDTO';
import { status } from './status';

@Injectable({
  providedIn: 'root'
})
export class MNPSPService {

  constructor(private http : HttpClient) { }

  Validate(form : CafDTO) : Observable<messageDTO>{
    return this.http.post<messageDTO>(`http://localhost:8083/mnpsp/sendcaf`,form);
  }

  scheduleTime(clearance : boolean):Observable<Date>{
    const validationClearance = {clearance};
    return this.http.post<Date>("http://localhost:8083/mnpsp/scheduleport",validationClearance);
  }

  getAllStatus(): Observable<status[]>{
    return this.http.get<status[]>("http://localhost:8083/mnpsp/all");
  }

}
