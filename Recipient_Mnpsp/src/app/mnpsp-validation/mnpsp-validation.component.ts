import { Component, OnInit } from '@angular/core';
import { messageDTO } from '../messageDTO';
import { MNPSPService } from '../mnpsp.service';
import { PortingService } from '../port-in.service';
import { RequestDTO } from '../RequestDTO';
import { DonorService } from '../donor.service';
import { validationClearancedto } from '../ValidationClearancedto';
import { CAF } from '../CAF';

@Component({
  selector: 'app-mnpsp-validation',
  templateUrl: './mnpsp-validation.component.html',
  styleUrls: ['./mnpsp-validation.component.css']
})
export class MnpspValidationComponent implements OnInit {
  forms: CAF[] = [];
  form: CAF = {
    mobileNumber: '', upc: '', name: '', address: '',
    clearance: false
  };
  response: messageDTO = { message: '' };
  inputMobNum: messageDTO = { message: '' };
  request: RequestDTO = { mobileNumber: '', activationTime: new Date('2024-01-01') };
  clearance : validationClearancedto = {mobileNumber:'',validationClearance:false};

  constructor(
    private mnpspService: MNPSPService,
    private portinService: PortingService,
    private donorService: DonorService
  ) {}

  ngOnInit(): void {
    this.portinService.getAllPortingRquests().subscribe(
      (res: CAF[]) => {
        this.forms = res; // assigning the response to forms
        if (res && res.length > 0) {
          this.form = res[0];
        }
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  forwardForm(form: CAF) {
    this.mnpspService.processToDonor(form).subscribe((res: boolean) => {
      let message = res ? 'Request forwarded to Donor Operator successfully!' : 'Unsuccessful!';
        alert(message);
    
    });
  }

  getClearance(form: CAF) {
    this.inputMobNum.message = form.mobileNumber;
    this.mnpspService.getClearance(this.inputMobNum).subscribe(
      (res: boolean) => {
        form.clearance = res; // Store the clearance response for the current form
        
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  schedulePortingTime(form: CAF) {
    this.inputMobNum.message = form.mobileNumber;
    this.mnpspService.scheduleTime(this.inputMobNum).subscribe(
      (res: Date) => {
        alert(`Scheduled Porting time at ${res}`);
        this.request.mobileNumber = form.mobileNumber;
        this.request.activationTime = new Date(res);
        this.donorService.saveDeactivationReq(this.request).subscribe();
        this.mnpspService.getDonorClearance(this.clearance).subscribe();
        this.portinService.saveActivationReq(this.request).subscribe();
        this.mnpspService.getRecipientClearance(this.clearance).subscribe();
        this.mnpspService.updateNumDB(this.inputMobNum).subscribe();
        
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

    
}
