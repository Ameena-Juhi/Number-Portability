import { Component } from '@angular/core';
import { MNPSPService } from '../mnpsp.service';
import { PortingService } from '../port-in.service';
import { messageDTO } from '../messageDTO';
import { CAF } from '../CAF';
import { DonorService } from '../donor.service';

@Component({
  selector: 'app-mnpsp',
  templateUrl: './mnpsp.component.html',
  styleUrls: ['./mnpsp.component.css']
})

export class MNPSPComponent {

  caf : CAF[] =[];
  response: messageDTO = { message: '' }; 
  inputNumber : messageDTO = {message:''};
  responses : String[] = [];

  constructor(private mnpspService : MNPSPService,
    private portinService : PortingService,
    private donorService : DonorService){}

  ngOnInit(): void {
    this.portinService.getAllPortingRquests().subscribe(res => this.caf = res);
  }
  
  getValidation(form: CAF): void {
    this.mnpspService.Validate(form).subscribe(
      (response : messageDTO) => {
        this.response = response;
      },
      (error) => {
        console.error('Error:', error);
        this.response ={message : 'An error occurred while validating the mobile number.'} ;
      }
    );
  }

  cancelTheRequest(form: CAF) {
    this.inputNumber.message = form.mobileNumber;
    const responses: string[] = [];
    const cancelRequestPromise = this.portinService.cancelRequest(this.inputNumber).toPromise();
    const cancelReqMNPSPPromise = this.mnpspService.cancelReqMNPSP(this.inputNumber).toPromise();
    const cancelDOPromise = this.donorService.cancelDO(this.inputNumber).toPromise();
  
    Promise.all([cancelRequestPromise, cancelReqMNPSPPromise, cancelDOPromise])
      .then((values) => {
        values.forEach((res: any) => {
          if (res && res.message) {
            responses.push(res.message);
          }
        });
        alert(responses);
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  }
}
  
