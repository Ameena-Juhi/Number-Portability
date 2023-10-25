import { Component } from '@angular/core';
import { MsgDTO } from '../MsgDTO';
import { PortingService } from '../porting.service';

@Component({
  selector: 'app-cancel',
  templateUrl: './cancel.component.html',
  styleUrls: ['./cancel.component.css']
})
export class CancelComponent {

  sms: MsgDTO = {message:''};
  response: MsgDTO = {message:''};

  constructor(private portingService : PortingService) { }

  cancelPortRequest() {
    if (this.sms) {
        this.portingService.cancelRequest(this.sms).subscribe((result:MsgDTO) => {this.response = result;
          this.sms.message='';
        },
        (error) => {
          console.error('Error:', error);
          this.response = { message: 'Error occurred while processing the request.' };
        });
    }
    else {
      this.response = { message: 'No SMS entered.' };
    }
  }
}
