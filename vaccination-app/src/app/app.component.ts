import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { VaccinationCenterComponent } from "./vaccination-center/vaccination-center.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, VaccinationCenterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Gaplaut2';
}
