import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideClientHydration } from '@angular/platform-browser';
import { provideAnimations } from '@angular/platform-browser/animations';
import { provideHttpClient } from '@angular/common/http';
import { provideToastr } from 'ngx-toastr';

import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideClientHydration(),
    provideAnimations(),
    provideToastr({
      timeOut: 3000, // Toast disappears after 3 seconds
      positionClass: 'toast-bottom-right', // Positioning
      preventDuplicates: true, // Avoid duplicate toasts
      closeButton: true, // Show close button
      progressBar: true, // Show progress bar
    })
  ]
};