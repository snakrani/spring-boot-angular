import { Component, OnInit } from '@angular/core';
import { User } from '../model/model';
import { UserService } from './user.service';
import { ErrorStateMatcher } from '@angular/material';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DataService } from '../services/data.service';


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user : User = new User();
  message: string;

  constructor(private userService: UserService, private router: Router,
    private data: DataService) { }
  
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);
  firstNameFormControl = new FormControl('', [Validators.required]);
  lastNameFormControl = new FormControl('', [Validators.required]);
  passwordFormControl = new FormControl('', [Validators.required]);
  confirmPasswordFormControl = new FormControl('', [Validators.required]);
  matcher = new MyErrorStateMatcher();
  
  ngOnInit() {
  }

  registerUser(){
    if(!this.validate()) {
      this.message = "Please enter all mandatory fields.";
      return;
    }
    this.message = 'Thank you for registering.'
    this.userService.storeUser(this.user).subscribe(
      response => {
        this.data.changeMessage(this.message);
        this.router.navigate(['']);
      }, error => {
        this.data.changeMessage(this.message);
        this.router.navigate(['']);
      }
    );
  }
  
  validate() {
    if(this.user 
      && this.user.firstName && this.user.firstName.length > 0
      && this.user.lastName && this.user.lastName.length > 0
      && this.user.email && this.user.email.length > 0
      && this.user.confirmPassword && this.user.confirmPassword.length > 0
      && this.user.password && this.user.password.length > 0) {
        return true;
      }
    return false;
  }
}
