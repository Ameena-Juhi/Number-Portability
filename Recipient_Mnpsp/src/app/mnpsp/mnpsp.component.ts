import { Component } from '@angular/core';
import { MNPSPService } from '../mnpsp.service';
import { PortingService } from '../port-in.service';
import { messageDTO } from '../messageDTO';
import { CafDTO } from '../CafDTO';

@Component({
  selector: 'app-mnpsp',
  templateUrl: './mnpsp.component.html',
  styleUrls: ['./mnpsp.component.css']
})

export class MNPSPComponent {

  caf : CafDTO[] =[];
  response: messageDTO = { message: '' }; 

  constructor(private mnpspService : MNPSPService,
    private portinService : PortingService){}

  ngOnInit(): void {
    this.portinService.getAllRquests().subscribe(res => this.caf = res);
  }
  
  getValidation(form: CafDTO): void {
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

  
  

}
