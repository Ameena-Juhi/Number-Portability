import { Component } from '@angular/core';
import { PortingService } from '../port-in.service';
import { RequestDTO } from '../RequestDTO';
import { Observable } from 'rxjs';
import { messageDTO } from '../messageDTO';

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.css']
})
export class ActivationComponent {

  requests : RequestDTO[] = [];
  request : RequestDTO = { mobileNumber:'',activationTime:new Date('2024-01-01')};
  response: messageDTO = {message:''};
  isButtonDisabled: boolean = false;

  constructor(
    private portinService:PortingService
  ){}

  ngOnInit(): void {
    this.portinService.getAllActivationReq().subscribe(
      (res: RequestDTO[]) => {
        this.requests = res; 
        this.requests.reverse();// assigning the response to forms
        if (res && res.length > 0) {
          this.request = res[0];
        }
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  activateNumber(request:RequestDTO){
    this.portinService.getRequestActivation(request).subscribe(
      (response : messageDTO) => {
        this.response = response;
      },
      (error) => {
        console.error('Error:', error);
        this.response ={message : 'An error occurred .'} ;
      }
    )
    this.isButtonDisabled = true;
  }
}
