import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TwitterRoutingModule} from "./twitter-routing.module";
import {MainComponent} from "./main/main.component";
import {SharedModule} from "../shared/shared.module";
import {TweetService} from "../services/tweet/tweet.service";
import {UserProfileService} from '../services/user-profile/user-profile.service';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    TwitterRoutingModule,
  ],
  declarations: [
    MainComponent
  ],
  providers: [TweetService, UserProfileService]
})
export class TwitterModule {
}
