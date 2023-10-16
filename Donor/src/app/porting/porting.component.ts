import { Component } from '@angular/core';
import { PortingService } from '../porting.service';

@Component({
  selector: 'app-porting',
  templateUrl: './porting.component.html',
  styleUrls: ['./porting.component.css']
})
export class PortingComponent {
  sms: string = '';
  response: string = '';

  constructor(private portingService : PortingService) { }

  retrieveMobileNumber() {
    if (this.sms) {
        this.portingService.getUpc(this.sms).subscribe((result) => {this.response = result;
        },
        (error) => {
          console.error('Error:', error);
          this.response = 'Error occurred while processing the request.';
        });
      } 
    else {
      this.response = 'No SMS entered.';
    }
  }
}
