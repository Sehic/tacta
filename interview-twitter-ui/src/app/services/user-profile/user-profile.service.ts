import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {UserProfileModel} from '../../models/user-profile.model';

const ENDPOINT_BASE = '/api/profile';

@Injectable()
export class UserProfileService {

  constructor(private http: HttpClient) {
  }

  fetch(): Observable<UserProfileModel> {
    return this.http.get<UserProfileModel>(ENDPOINT_BASE);
  }

}
