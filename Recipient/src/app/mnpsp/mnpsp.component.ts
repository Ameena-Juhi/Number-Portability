import { Component } from '@angular/core';
import { CAF } from '../CAF';
import { MNPSPService } from '../mnpsp.service';
import { PortingService } from '../port-in.service';

@Component({
  selector: 'app-mnpsp',
  templateUrl: './mnpsp.component.html',
  styleUrls: ['./mnpsp.component.css']
})
export class MNPSPComponent {

  caf : CAF[] =[];
  response?: string;

  constructor(private mnpspService : MNPSPService,
    private portinService : PortingService){}

  ngOnInit(): void {
    this.portinService.getAllRquests().subscribe(res => this.caf = res);
  }
  
  getValidation(form: CAF): void {
    this.mnpspService.Validate(form.mobileNumber).subscribe(
      (response: string) => {
        this.response = response;
      },
      (error) => {
        console.error('Error:', error);
        this.response = 'An error occurred while validating the mobile number.';
      }
    );
  }
  

}
