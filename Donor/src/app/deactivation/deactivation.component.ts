import { Component, OnInit } from '@angular/core';
import { PortingService } from '../porting.service';
import { RequestDTO } from '../RequestDTO';
import { MsgDTO } from '../MsgDTO';

@Component({
  selector: 'app-deactivation',
  templateUrl: './deactivation.component.html',
  styleUrls: ['./deactivation.component.css']
})
export class DeactivationComponent implements OnInit {

  requests: RequestDTO[] = [];
  request: RequestDTO = {
    mobileNumber:'',
    activationTime: new Date('2024-01-01')
  };
  response: MsgDTO = { message : '' };

  constructor(private portingService: PortingService) {}

  ngOnInit(): void {
    this.portingService.getAllDeactReqs().subscribe(
      (res: RequestDTO[]) => {
        this.requests = res; 
        if (res && res.length > 0) {
          this.request = res[0]; 
        }
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  deactivateSubscriber(request: RequestDTO): void {
    this.portingService.requestDeactivation(request).subscribe(
      (res: MsgDTO) => {
        this.response = res;
      },
      (error) => {
        console.error('Error:', error);
        this.response = { message : 'An error occurred.' };
      }
    );
  }
}
