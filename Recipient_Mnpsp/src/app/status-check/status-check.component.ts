import { Component,OnInit } from '@angular/core';
import { status } from '../status';
import { MNPSPService } from '../mnpsp.service';
import { Observable } from 'rxjs';
import { messageDTO } from '../messageDTO';

@Component({
  selector: 'app-status-check',
  templateUrl: './status-check.component.html',
  styleUrls: ['./status-check.component.css']
})
export class StatusCheckComponent implements OnInit {
  statusDtos: status[] = [];

  constructor(
    private mnpspService: MNPSPService
  ) {}

  ngOnInit(): void {
    this.mnpspService.getAllStatus().subscribe(
      (res: status[]) => {
        this.statusDtos = res;
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }


}





