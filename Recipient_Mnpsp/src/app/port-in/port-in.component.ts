import { Component } from '@angular/core';
import { PortingService } from '../port-in.service';
import { CAF } from '../CAF';

@Component({
  selector: 'app-port-in',
  templateUrl: './port-in.component.html',
  styleUrls: ['./port-in.component.css']
})
export class PortInComponent {

  form : CAF = {
    name:'',
    address:'',
    mobileNumber: '',
    upc: ''
  };
  successMessage : string ='';

  constructor(private portinService: PortingService){}

  processCAF(){
    if(this.form){
      this.portinService.getUpc(this.form).subscribe( () => {
        this.successMessage = 'CAF submitted successfully.';
        this.form.mobileNumber = '',
        this.form.upc = ''
      },
      (error) => {
        console.error('Error:', error);
        this.successMessage = 'Error occurred while processing the request.';
      });
    }
    else {
      this.successMessage = 'No Request recieved.';
    }
  }
}
