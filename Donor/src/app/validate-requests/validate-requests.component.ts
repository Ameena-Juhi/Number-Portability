import { Component, OnInit } from '@angular/core';
import { CafDTO } from '../cafDTO'; // Check the case sensitivity of the file name
import { PortingService } from '../porting.service';
import { MsgDTO } from '../MsgDTO';

@Component({
  selector: 'app-validate-requests',
  templateUrl: './validate-requests.component.html',
  styleUrls: ['./validate-requests.component.css']
})
export class ValidateRequestsComponent implements OnInit {
  forms: CafDTO[] = [];
  form: CafDTO = { mobileNumber: '', upc: '' }; 

  constructor(
    private portingService: PortingService
  ) {}

  ngOnInit(): void {
    this.portingService.getAllRequests().subscribe(
      (res: CafDTO[]) => {
        this.forms = res; // assigning the response to forms
        if (res && res.length > 0) {
          this.form = res[0]; // Ensure the 'form' variable is assigned with the first element of the response
        }
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  validateRequest(form: CafDTO) {
    this.portingService.validate(form).subscribe(
      (res: boolean) => {
        let message = res ? 'Validation Successful!' : 'Validation Failed!';
        alert(message);
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }
}
