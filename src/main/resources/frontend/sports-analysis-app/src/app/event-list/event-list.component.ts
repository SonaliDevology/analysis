import { Component, inject, OnInit, signal, computed } from '@angular/core';
import { EventService } from '../event.service';
import { CommonModule, DatePipe } from '@angular/common';
import { SafePipe } from '../safe.pipe';
import { FormsModule } from '@angular/forms';
import { toSignal } from '@angular/core/rxjs-interop';
import { SocialAuthService, SocialLoginModule } from '@abacritt/angularx-social-login';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

interface VideoCard {
  name: any;
  location: any;
  dateTime: any;
  videoUrl: any;
  description: any;
}

@Component({
  selector: 'app-event-list',
  standalone: true,
  imports: [DatePipe, SafePipe, CommonModule, FormsModule, SocialLoginModule],
  templateUrl: './event-list.component.html',
  styleUrl: './event-list.component.sass'
})
export class EventListComponent implements OnInit {
  constructor(
      private socialAuthService: SocialAuthService,
    private eventService: EventService,
    private authService: AuthService,
    private router: Router
  ) {}
  events = toSignal(this.eventService.getEvents(), { initialValue: [] });
  filterText = signal('');
  currentIndex = 0;
  currentPage = 1;
  pageSize = 1;
  userName: string = '';

  videoCards = computed<VideoCard[]>(() => {
    return this.events().flatMap((event: any) => {
      if (!event.videos) return [];
      return event.videos.map((video: any) => ({
        name: event.name,
        location: event.location,
        dateTime: event.dateTime,
        videoUrl: video.url,
        description: video.description
      }));
    });
  });

filteredVideoCards = computed(() => {
  const query = this.filterText().toLowerCase();
  return this.videoCards().filter(item =>
    item.name.toLowerCase().includes(query) ||
    item.location.toLowerCase().includes(query)
  );
});


  ngOnInit(): void {
    const user = this.authService.getUser();
        if (user) {
          this.userName = user.name;
        }
    setInterval(() => this.nextPage(), 6000);
  }
  get totalCards(): number {
    return this.filteredVideoCards().length;
  }

  nextPage(): void {
    const total = this.filteredVideoCards().length;
    if (total > 0) {
      this.currentIndex = (this.currentIndex + 1) % total;
    }
  }

  prevPage(): void {
    const total = this.filteredVideoCards().length;
    if (total > 0) {
      this.currentIndex = (this.currentIndex - 1 + total) % total;
    }
  }

  onFilterChange(event: any) {
    this.filterText.set(event.target.value);
    this.currentIndex = 0;
  }


  logout(): void {
    this.socialAuthService.signOut();
    this.authService.logout(); // optional if you have logout logic
    this.router.navigate(['/login']);
  }

}
