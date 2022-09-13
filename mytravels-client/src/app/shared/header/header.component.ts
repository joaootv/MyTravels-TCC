import { StorageService } from '../../services/storage/storage.service';
import { AuthService } from 'src/app/services/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(
    private router: Router,
    private tabTitle: Title,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
  }

  public loggout() {
    this.tabTitle.setTitle('MyTravels');
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
