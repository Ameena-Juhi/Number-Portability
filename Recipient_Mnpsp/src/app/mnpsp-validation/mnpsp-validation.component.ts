import { Component, OnInit } from '@angular/core';
import { CafDTO } from '../CafDTO';
import { messageDTO } from '../messageDTO';
import { MNPSPService } from '../mnpsp.service';
import { PortingService } from '../port-in.service';
import { RequestDTO } from '../RequestDTO';

@Component({
  selector: 'app-mnpsp-validation',
  templateUrl: './mnpsp-validation.component.html',
  styleUrls: ['./mnpsp-validation.component.css']
})
export class MnpspValidationComponent implements OnInit {
  forms: CafDTO[] = [];
  form: CafDTO = { mobileNumber: '', upc: '' };
  response: messageDTO = { message: '' };
  inputMobNum: messageDTO = { message: '' };
  request: RequestDTO = { mobileNumber: '', activationTime: new Date('2024-01-01') };

  constructor(
    private mnpspService: MNPSPService,
    private portinService: PortingService
  ) {}

  ngOnInit(): void {
    this.portinService.getAllPortingRquests().subscribe(
      (res: CafDTO[]) => {
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

  forwardForm(form: CafDTO) {
    this.mnpspService.processToDonor(form).subscribe(() => {
      alert('Request forwarded to Donor Operator successfully!');
    });
  }

  schedulePortingTime(form: CafDTO) {
    this.inputMobNum.message = form.mobileNumber;
    this.mnpspService.scheduleTime(this.inputMobNum).subscribe(
      (res: Date) => {
        alert(`Scheduled Porting time at ${res}`);
        this.request.mobileNumber = form.mobileNumber;
        this.request.activationTime = new Date(res);
        this.portinService.saveActivationReq(this.request).subscribe();
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }
}
