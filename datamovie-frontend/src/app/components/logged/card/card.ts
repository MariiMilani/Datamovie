import {booleanAttribute, Component, Input} from '@angular/core';

@Component({
  selector: 'app-card',
  imports: [],
  templateUrl: './card.html',
  styleUrl: './card.css'
})
export class Card {
  @Input() title: string = '';
  @Input() description: string = '';
  @Input() duration: string = '';
  @Input() ageRating: string = '';
  @Input() approvalRating: number = 0;
  @Input() streamingLogo: string = '';
  @Input({transform: booleanAttribute}) isTop10: boolean = false;
}
