import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';
import {
  SocialAuthServiceConfig,
  GoogleLoginProvider
} from '@abacritt/angularx-social-login';
import { environment } from '../environments/environment';
export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes),provideHttpClient(), {
    provide: 'SocialAuthServiceConfig',
    useValue: {
      autoLogin: false,
      providers: [
        {
          id: GoogleLoginProvider.PROVIDER_ID,
          provider: new GoogleLoginProvider(environment.googleClientId)
        }
      ]
    } as SocialAuthServiceConfig,
  }]
};
