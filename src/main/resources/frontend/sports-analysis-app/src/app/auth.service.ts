import { Injectable } from '@angular/core';
import { SocialUser } from '@abacritt/angularx-social-login';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = false;
  private currentUser: SocialUser | null = null;
  constructor() { }

  login(user: SocialUser): void {
    this.loggedIn = true;
    localStorage.setItem('loggedIn', 'true');
    this.currentUser = user;
  }

  logout(): void {
    this.loggedIn = false;
    localStorage.removeItem('loggedIn');
    localStorage.removeItem('user');
    sessionStorage.setItem('loggedOut', 'true');
    this.currentUser = null;
  }

  isAuthenticated(): boolean {
    return localStorage.getItem('loggedIn') === 'true';
  }

  getUser(): SocialUser | null {
    return this.currentUser;
  }
}
