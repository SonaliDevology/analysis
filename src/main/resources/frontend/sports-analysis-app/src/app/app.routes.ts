import { Routes } from '@angular/router';
import { EventListComponent } from './event-list/event-list.component';
import { LoginComponent } from './login/login.component';
import { authGuard } from './auth.guard'; // Optional: for route protection


export const routes: Routes =  [
  { path: 'login', component: LoginComponent },
  { path: 'events', component: EventListComponent, canActivate: [authGuard] }, // protect with guard
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  ];
