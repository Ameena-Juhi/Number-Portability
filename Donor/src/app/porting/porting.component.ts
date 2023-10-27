import { Component } from '@angular/core';
import { PortingService } from '../porting.service';
import { CafDTO } from '../CafDTO';
import { RequestDTO } from '../RequestDTO';
import { MsgDTO } from '../MsgDTO';

@Component({
  selector: 'app-porting',
  templateUrl: './porting.component.html',
  styleUrls: ['./porting.component.css']
})
export class PortingComponent {
  sms: MsgDTO = {message:''};
  response: MsgDTO = {message:''};

  constructor(private portingService : PortingService) { }

  retrieveMobileNumber() {
    if (this.sms) {
        this.portingService.getUpc(this.sms).subscribe((result:MsgDTO) => {this.response = result;
        this.sms.message='';},
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
