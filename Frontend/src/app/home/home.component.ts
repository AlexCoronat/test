import { Component, OnInit } from '@angular/core';
import {MatTabsModule} from '@angular/material/tabs';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { HistoryComponent } from './components/history/history.component';
import { InventoryComponent } from './components/inventory/inventory.component';
import { OutInventoryComponent } from './components/out-inventory/out-inventory.component';

@Component({
  selector: 'app-home',
  imports: [MatTabsModule, HistoryComponent, CommonModule, MatIconModule, MatButtonModule, MatToolbarModule, InventoryComponent, OutInventoryComponent],
  standalone: true,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})

export class HomeComponent implements OnInit{
  permissions: string[] = [];
  constructor(
    private authServise: AuthService,
    private router: Router,
  ) { }
  ngOnInit(): void {
    this.permissions = this.authServise.getPermissions();
  }

  onLogout() {
    this.authServise.logOut();
    this.router.navigate(['/auth/login']);
  }
}
