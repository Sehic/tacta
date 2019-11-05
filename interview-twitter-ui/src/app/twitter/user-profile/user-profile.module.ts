import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {UserProfileViewComponent} from './user-profile-view/user-profile-view.component';
import { UserProfileCardComponent } from './user-profile-card/user-profile-card.component';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: '', component: UserProfileViewComponent, children: [
          {path: '', component: UserProfileCardComponent},
        ],
      },
    ]),
    CommonModule,
  ],
  declarations: [UserProfileViewComponent, UserProfileCardComponent]
})
export class UserProfileModule {
}
