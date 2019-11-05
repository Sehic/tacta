import { Component, OnInit } from '@angular/core';
import {UserProfileService} from '../../../services/user-profile/user-profile.service';
import {Observable} from 'rxjs/Observable';
import {UserProfileModel} from '../../../models/user-profile.model';

@Component({
  selector: 'app-user-profile-view',
  templateUrl: './user-profile-view.component.html',
  styleUrls: ['./user-profile-view.component.css']
})
export class UserProfileViewComponent implements OnInit {

  $profile: Observable<UserProfileModel>;

  constructor(private userProfileService: UserProfileService) { }

  ngOnInit() {
    this.$profile = this.userProfileService.fetch();
  }

}
