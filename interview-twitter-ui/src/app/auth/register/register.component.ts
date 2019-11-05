import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {debounceTime, map, take} from 'rxjs/operators';
import {RegisterService} from '../../services/register/register.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  user: any;


  constructor(private registerService: RegisterService, private router: Router) {
    this.registerForm = new FormGroup({
      'username' : new FormControl(null, [Validators.required], this.checkUsername.bind(this)),
      'firstName' : new FormControl( ),
      'lastName' : new FormControl( ),
      'password' : new FormControl(null, [ Validators.required ]),
    });
  }

  ngOnInit() {
  }

  saveUser() {

    const obj = {
      username: this.registerForm.value.username,
      firstName: this.registerForm.value.firstName,
      lastName: this.registerForm.value.lastName,
      password: this.registerForm.value.password,
    };


   this.registerService.register(obj).subscribe(
      resp => {this.user = resp ;
        this.router.navigate(['/login']); },
      err => console.log(err)
    );

  }


  checkUsername(control: FormControl) {

    return this.registerService.checkExisitingUser(control.value).pipe(
      debounceTime(500),
      take(1),
      map( res =>  res ? {username : false}  : null),
    );

  }


}
