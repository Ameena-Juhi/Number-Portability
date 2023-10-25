import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { RequestDTO } from './RequestDTO';
import { MsgDTO } from './MsgDTO';
import { SubDetailsDto } from './SubDetailsDto';
import { RequestDTOLocale } from './RequestDTOLocale';
import { CafDTO } from './cafDTO';

@Injectable({
  providedIn: 'root'
})
export class PortingService {

  constructor(private http : HttpClient) { }

  getUpc(sms : MsgDTO) : Observable<MsgDTO>{

    return this.http.post<MsgDTO>("http://localhost:8081/operator/port",sms);
  }

  getAllRequests():Observable<CafDTO[]>{
    return this.http.get<CafDTO[]>("http://localhost:8081/operator/getRequests");
  }

  validate(form : CafDTO): Observable<MsgDTO>{
    return this.http.post<MsgDTO>("http://localhost:8081/operator/validate",form);
  }

  getAllDeactReqs():Observable<RequestDTOLocale[]>{
    return this.http.get<RequestDTOLocale[]>("http://localhost:8081/operator/allDeactReqs");
  }

  requestDeactivation(request : RequestDTOLocale): Observable<MsgDTO>{
    return this.http.post<MsgDTO>("http://localhost:8081/operator/deactivation",request);
  }

  getSubscribers():Observable<SubDetailsDto[]>{
    return this.http.get<SubDetailsDto[]>("http://localhost:8081/operator/getSubscribers");
  }

  cancelRequest(sms:MsgDTO):Observable<MsgDTO>{
    return this.http.post<MsgDTO>("http://localhost:8081/operator/cancel",sms)
  }
  
}
