import { Component, Inject, Input } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogContent } from '@angular/material/dialog';
import { MatGridList, MatGridTile } from '@angular/material/grid-list';
import { MatCard, MatCardContent } from '@angular/material/card';
import { WeatherResponse } from '../model/weather-response';

@Component({
  selector: 'app-dialog',
  imports: [MatDialogContent, MatCard, MatCardContent],
  templateUrl: './dialog.component.html',
  standalone: true,
  styleUrl: './dialog.component.css',
})
export class DialogComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA)
    public data: {
      weatherResponse: WeatherResponse;
    }
  ) {}
}
