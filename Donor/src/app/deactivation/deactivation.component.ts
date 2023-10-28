import { Component, OnInit } from '@angular/core';
import { PortingService } from '../porting.service';
import { RequestDTO } from '../RequestDTO';
import { RequestDTOLocale } from '../RequestDTOLocale';
import { MsgDTO } from '../MsgDTO';

@Component({
  selector: 'app-deactivation',
  templateUrl: './deactivation.component.html',
  styleUrls: ['./deactivation.component.css']
})
export class DeactivationComponent implements OnInit {

  requests: RequestDTOLocale[] = [];
  request: RequestDTOLocale = {
    mobileNumber:'',
    activationTime: new Date('2024-01-01').toLocaleString()
  };
  isButtonDisabled: boolean = false;
  response: MsgDTO = { message : '' };
  datePipe: any;

  constructor(private portingService: PortingService) {}

  ngOnInit(): void {
    this.portingService.getAllDeactReqs().subscribe(
      (res: RequestDTOLocale[]) => {
        this.requests = res; 
        this.requests.reverse();
        if (res && res.length > 0) {
          this.request = res[0]; 
        }
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  deactivateSubscriber(request: RequestDTOLocale): void {
    let dateCorrectedReq = request;
    dateCorrectedReq.activationTime = this.request.activationTime.toLocaleString();
    this.portingService.requestDeactivation(request).subscribe(
      (res: MsgDTO) => {
        this.response = res;
      },
      (error) => {
        console.error('Error:', error);
        this.response = { message : 'An error occurred.' };
      }
    );
    this.isButtonDisabled = true;
  }
}
