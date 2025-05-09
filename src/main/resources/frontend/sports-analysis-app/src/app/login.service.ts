import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private backendUrl = '/api/login';

  constructor(private http: HttpClient) {}

  authenticateWithGoogle(idToken: string): Observable<any> {
    return this.http.post(this.backendUrl, { token: idToken });
  }
}
