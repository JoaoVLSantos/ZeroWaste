import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { LogoutComponent } from '../../auth/logout/logout.component';
import { AuthService } from '../../auth/auth.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, LogoutComponent, RouterModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  private authService = inject(AuthService);

  links = [
    { name: 'Produtos', url: '/products' },
    { name: 'Promoções', url: '/promotions' },
    { name: 'Doações', url: '/donations' },
    // { name: 'Listas de transmissão', url: '/broadcasts' },
    // { name: 'Pontos de doação', url: '/donation-points' },
    // { name: 'Relatórios', url: '/reports/waste' }
  ];

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }

  isAdmin() {
    return this.authService.hasRole('ADMIN');
  }
}
