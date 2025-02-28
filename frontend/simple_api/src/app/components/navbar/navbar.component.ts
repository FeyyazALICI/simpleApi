import { Component, HostListener, ElementRef, ViewChild, Renderer2 } from '@angular/core';
import { Router } from '@angular/router';
import { NavbarServicesService } from '../../models_and_services/services/navbar-services.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  homeIcon: string = "assets/images/homeIcon.png";

  constructor(
    private navbarService: NavbarServicesService, 
    private renderer: Renderer2,
    private router:Router,
    ) {}

  change_background: boolean = false;

  @HostListener('window:scroll', [])
  onWindowScroll() {
    this.navbarService.toggleNavbarBackground(window.pageYOffset);
    this.change_background = this.navbarService.isNavbarScrolled;
    console.log('Scroll position:', window.pageYOffset);
    console.log('Is Navbar Scrolled:', this.change_background);
    const navbarElement = document.getElementById('navbar_id');
    if(window.pageYOffset>0){
      navbarElement.style.backgroundColor = '#702963';
      navbarElement.style.opacity = '1';
      navbarElement.style.color = 'white';
      navbarElement.style.fontWeight = "thin";
    }
    else{
      navbarElement.style.backgroundColor = 'transparent';
      navbarElement.style.backgroundColor = 'white';
      navbarElement.style.color = 'red';
      navbarElement.style.opacity = '0.5';
    }
  }

  onMouseEnter() {
    this.applyHoverStyles();
  }

  onMouseLeave() {
    this.applyNormalStyles();
  }

  private applyHoverStyles() {
    const navbarElement = document.getElementById('navbar_id');
    navbarElement.style.backgroundColor = '#702963';
    navbarElement.style.opacity = '1';
    navbarElement.style.color = 'white';
    navbarElement.style.fontWeight = "thin";
  }

  private applyNormalStyles() {
    const navbarElement = document.getElementById('navbar_id');
    navbarElement.style.backgroundColor = 'transparent';
    navbarElement.style.backgroundColor = 'white';
    navbarElement.style.color = 'red';
    navbarElement.style.opacity = '0.5';
  }

  navigate(route: string) {
    this.router.navigate([route]);
  }

}
