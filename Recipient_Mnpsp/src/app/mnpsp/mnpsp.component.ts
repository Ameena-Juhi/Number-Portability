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
  form:CAF={
    name: '',
    address: '',
    mobileNumber: '',
    upc: '',
    clearance: false
  };
  responses : String[] = [];

  constructor(private mnpspService : MNPSPService,
    private portinService : PortingService,
    private donorService : DonorService){}

  ngOnInit(): void {
    this.portinService.getAllPortingRquests().subscribe(res => this.caf = res);
    this.getIdentity(this.form);
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

  identityResponse: string = '';

  getIdentity(form: CAF) {
    this.inputNumber.message = form.mobileNumber;
    this.portinService.getIdentityClearance(this.inputNumber).subscribe(
      (res: boolean) => {
        if (!res) {
          this.identityResponse = 'Unsuccessful due to name/address mismatch!';
          console.log(this.identityResponse);
        } else {
          this.identityResponse = '';
        }
      },
      (error) => {
        console.error('Error:', error);
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
  
