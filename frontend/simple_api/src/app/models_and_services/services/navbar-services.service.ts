import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavbarServicesService {

  constructor() { }

  isNavbarScrolled = false;

  toggleNavbarBackground(scrollPosition: number) {
    this.isNavbarScrolled = scrollPosition > 0;
  }

}
