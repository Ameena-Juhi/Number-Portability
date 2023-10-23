import { Component, OnInit } from '@angular/core';
import { SubDetailsDto } from '../SubDetailsDto';
import { PortingService } from '../port-in.service';

@Component({
  selector: 'app-subscribers',
  templateUrl: './subscribers.component.html',
  styleUrls: ['./subscribers.component.css']
})
export class SubscribersComponent implements OnInit {
  subscribers: SubDetailsDto[] = [];

  constructor(private portingService: PortingService) {}

  ngOnInit() {
    this.portingService.getSubDetails().subscribe(
      (data: SubDetailsDto[]) => {
        this.subscribers = data;
      },
      (error) => {
        console.error('Error fetching subscribers:', error);
      }
    );
  }
}
