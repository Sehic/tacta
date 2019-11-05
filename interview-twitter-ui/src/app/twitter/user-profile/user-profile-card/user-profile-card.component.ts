import {Component, Input, OnInit} from '@angular/core';
import {UserProfileModel} from '../../../models/user-profile.model';

@Component({
  selector: 'app-user-profile-card',
  templateUrl: './user-profile-card.component.html',
  styleUrls: ['./user-profile-card.component.css']
})
export class UserProfileCardComponent {

  @Input() profile: UserProfileModel;

}
