import { GoogleLoginProvider, GoogleSigninButtonModule, SocialAuthService, SocialLoginModule, SocialUser } from '@abacritt/angularx-social-login';
import { Component, NgZone, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [SocialLoginModule, GoogleSigninButtonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.sass'
})
export class LoginComponent implements OnInit{
  user?: SocialUser;
  loggedIn = false;

  constructor(
    private socialAuthService: SocialAuthService,
    private authService: AuthService,
    private loginService: LoginService,
    private router: Router,
    private ngZone: NgZone
  ) {}

  ngOnInit(): void {
    const wasLoggedOut = sessionStorage.getItem('loggedOut');
  if (wasLoggedOut) {
    sessionStorage.removeItem('loggedOut');
    return; // Do not auto-login after logout
  }
    this.socialAuthService.authState.subscribe((user: SocialUser) => {
      if (user && user.idToken) {
        this.user = user;
        this.loggedIn = true;


        // Call backend to verify token
        this.loginService.authenticateWithGoogle(user.idToken).subscribe({
          next: () => {
            this.authService.login(user); // set auth state
            this.ngZone.run(() => this.router.navigate(['/events']));
          },
          error: (err) => {
            console.error('Backend authentication failed:', err);
          }
        });
      }
    });

  }

  signInWithGoogle(): void {
    this.socialAuthService.signIn(GoogleLoginProvider.PROVIDER_ID).catch(error => {
      console.error('Google sign-in failed', error);
    });
  }

  signOut(): void {
    this.socialAuthService.signOut();
    this.authService.logout(); // optional if you have logout logic
    this.router.navigate(['/login']);
  }
}

