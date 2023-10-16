import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { CAF } from './CAF';

@Injectable({
  providedIn: 'root'
})
export class PortingService {

  constructor(private http : HttpClient) { }

  getUpc(Caf : CAF) : Observable<void>{
    return this.http.post<void>("http://localhost:8080/request/portin",Caf);
  }

  getAllRquests(): Observable<CAF[]>{
    return this.http.get<CAF[]>("http://localhost:8080/request/allrequests");
  }
}
