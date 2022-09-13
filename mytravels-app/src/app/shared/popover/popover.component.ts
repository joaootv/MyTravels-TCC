import { StorageService } from './../../services/storage.service';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { PopoverController } from '@ionic/angular';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-popover',
  templateUrl: './popover.component.html',
  styleUrls: ['./popover.component.scss'],
})
export class PopoverComponent implements OnInit {

  public name;
  public email;

  constructor(
    private popoverController: PopoverController,
    private router: Router,
    private tabTitle: Title,
    private authService: AuthService,
    private storage: StorageService
  ) { }

  ngOnInit() {
    this.name = this.storage.getNome();
    this.email = this.storage.getEmail();
  }

  public loggout() {
    this.tabTitle.setTitle('Pipevot');
    this.authService.logout();
    this.close();
    this.router.navigate(['/login']);
  }

  public close(){
    this.popoverController.dismiss();
  }
}
