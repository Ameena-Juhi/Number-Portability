import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { messageDTO } from './messageDTO';
import { CafDTO } from './CafDTO';
import { status } from './status';
import { validationClearancedto } from './ValidationClearancedto';

@Injectable({
  providedIn: 'root'
})
export class MNPSPService {

  constructor(private http : HttpClient) { }

  Validate(form : CafDTO) : Observable<messageDTO>{
    return this.http.post<messageDTO>(`http://localhost:8083/mnpsp/sendcaf`,form);
  }

  scheduleTime(mobNum: messageDTO):Observable<Date>{
    return this.http.post<Date>("http://localhost:8083/mnpsp/scheduleport",mobNum);
  }

  getAllStatus(): Observable<status[]>{
    return this.http.get<status[]>("http://localhost:8083/mnpsp/all");
  }

  processToDonor(form: CafDTO):Observable<void>{
    return this.http.post<void>("http://localhost:8083/mnpsp/processform",form)
  }
}
