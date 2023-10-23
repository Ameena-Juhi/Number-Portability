import { Component, OnInit } from '@angular/core';
import { PortingService } from '../porting.service';
import { SubDetailsDto } from '../SubDetailsDto';

@Component({
  selector: 'app-subscribers',
  templateUrl: './subscribers.component.html',
  styleUrls: ['./subscribers.component.css']
})
export class SubscribersComponent implements OnInit {
  subscribers: SubDetailsDto[] = [];

  constructor(private portingService: PortingService) {}

  ngOnInit() {
    this.portingService.getSubscribers().subscribe(
      (data: SubDetailsDto[]) => {
        this.subscribers = data;
      },
      (error) => {
        console.error('Error fetching subscribers:', error);
      }
    );
  }
}
