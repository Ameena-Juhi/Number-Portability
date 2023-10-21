import { Component } from '@angular/core';
import { PortingService } from '../porting.service';
import { CafDTO } from '../cafDTO';
import { RequestDTO } from '../RequestDTO';
import { MsgDTO } from '../MsgDTO';

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


  forwardCAF(cafDTO:CafDTO){
      this.portingService.getCAF(cafDTO).subscribe(
        (result) => {
          if (result) {
            console.log('CAF forwarded successfully.');
          } else {
            console.error('Error forwarding CAF.');
          }
        }
      )
    }
  

  deactivation(request:RequestDTO){
    this.portingService.requestDeactivation(request).subscribe(
      (result: MsgDTO) => {
        console.log('Deactivation request processed successfully.');
      },
      (error) => {
        console.error('Error:', error);
      }
    )
  }
}
