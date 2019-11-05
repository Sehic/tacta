import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {TweetModel} from '../../models/tweet.model';
import {UserModel} from '../../models/user.model';

const ENDPOINT_BASE = '/public';

@Injectable()
export class RegisterService {

  constructor(private http: HttpClient) {
  }

  checkExisitingUser(username: string): Observable<boolean>  {
    return this.http.get<boolean>(ENDPOINT_BASE + '/check/exist/' + username);
  }

  register(user: UserModel) {
    return this.http.post<UserModel>(ENDPOINT_BASE + '/register', user);
  }

}


