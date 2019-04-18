import { Injectable } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
 

import { User } from '../model/model';
import { Observable, BehaviorSubject } from 'rxjs';

const URL = "http://localhost:8080/api/user";

@Injectable()
export class UserService {

    constructor(private http: HttpClient) {}

    storeUser(user: User): Observable<any>  {
        return this.http.post(URL, user);
    }
    
}