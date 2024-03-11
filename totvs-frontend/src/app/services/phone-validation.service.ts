import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs'

@Injectable({
  providedIn: 'root',
})
export class PhoneValidationService {
  constructor(private httpClient: HttpClient) {}

  validatePhone(number: string): Observable<{valid: boolean}>{
    return this.httpClient.post<{valid: boolean}>('http://localhost:8080/validate', {number: number})
  }
}
