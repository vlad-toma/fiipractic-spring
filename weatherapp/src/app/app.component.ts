import {
  AfterViewInit,
  Component,
  inject,
  OnDestroy,
  OnInit,
} from '@angular/core';
import { AngularOpenlayersModule } from 'ng-openlayers';
import { transform } from 'ol/proj';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { DialogComponent } from './dialog/dialog.component';
import { WeatherResponse } from './model/weather-response';
import * as data from '../../data.json';
import { AppService } from './app.service';
import { LoginComponent } from './login/login.component';
import { CommonModule } from '@angular/common';

const SESSION_TIMEOUT_MINUTES = 10;
@Component({
  selector: 'app-root',
  imports: [
    AngularOpenlayersModule,
    MatDialogModule,
    CommonModule,
    LoginComponent,
  ],
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit, AfterViewInit, OnDestroy {
  title = 'weatherapp-fe';

  lat = 0;
  lon = 0;

  readonly dialog = inject(MatDialog);

  res: WeatherResponse = data;

  isLoggedIn = false;

  constructor(private appService: AppService) {}

  ngOnInit(): void {
    const auth = localStorage.getItem('auth');
    const sessionStart = localStorage.getItem('session_start');

    if (auth && sessionStart) {
      const now = new Date().getTime();
      const elapsed = now - parseInt(sessionStart, 10);
      const limit = SESSION_TIMEOUT_MINUTES * 60 * 1000;

      if (elapsed < limit) {
        this.isLoggedIn = true;
      } else {
        this.logout();
      }
    }
  }

  ngAfterViewInit() {
    window.addEventListener('click', this.extendSession.bind(this));
  }

  extendSession() {
    if (this.isLoggedIn) {
      localStorage.setItem('session_start', new Date().getTime().toString());
    }
  }

  logout() {
    localStorage.removeItem('auth');
    localStorage.removeItem('session_start');
    this.isLoggedIn = false;
  }

  async dispatchCursor(event: any) {
    const coordinates = event.coordinate;
    this.lon = transform(coordinates, 'EPSG:3857', 'EPSG:4326')[0];
    this.lat = transform(coordinates, 'EPSG:3857', 'EPSG:4326')[1];
    try {
      const resp = await this.appService.getWeatherInfo(this.lon, this.lat);
      if (resp) {
        console.log(resp);
        this.openDialog(resp);
      }
    } catch (error) {
      console.log(error);
    }
  }

  openDialog(resp: any): void {
    const dialogRef = this.dialog.open(DialogComponent, {
      data: { weatherResponse: resp },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
    });
  }

  onLoginSuccess() {
    const now = new Date().getTime();
    localStorage.setItem('session_start', now.toString());
    this.isLoggedIn = true;
  }

  ngOnDestroy() {
    window.removeEventListener('click', this.extendSession.bind(this));
  }
}
