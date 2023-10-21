import { Component,OnInit } from '@angular/core';
import { status } from '../status';
import { MNPSPService } from '../mnpsp.service';
import { PortingService } from '../port-in.service';

@Component({
  selector: 'app-status-check',
  templateUrl: './status-check.component.html',
  styleUrls: ['./status-check.component.css']
})
export class StatusCheckComponent implements OnInit {
  statusDtos: status[] = [];

  constructor(
    private mnpspService: MNPSPService,
    private portinService: PortingService
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





