import { Component, OnInit } from '@angular/core';
import { CafDTO } from '../CafDTO';// Check the case sensitivity of the file name
import { PortingService } from '../porting.service';
import { MsgDTO } from '../MsgDTO';

@Component({
  selector: 'app-validate-requests',
  templateUrl: './validate-requests.component.html',
  styleUrls: ['./validate-requests.component.css']
})
export class ValidateRequestsComponent implements OnInit {
  forms: CafDTO[] = [];
  form: CafDTO = {
    mobileNumber: '', upc: '',
    name: '',
    address: '',
    clearance: false
  }; 
  response : string ='';

  constructor(
    private portingService: PortingService
  ) {}

  ngOnInit(): void {
    this.portingService.getAllRequests().subscribe(
      (res: CafDTO[]) => {
        this.forms = res; 
        this.forms.reverse();
        if (res && res.length > 0) {
          this.form = res[0]; 
        }
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  validateRequest(form: CafDTO) {
    this.portingService.validate(form).subscribe(
      (res:MsgDTO) => {
        alert(this.response = res.message);
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }
}
