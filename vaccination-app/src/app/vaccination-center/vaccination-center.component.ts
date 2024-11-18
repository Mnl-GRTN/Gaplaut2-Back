import { Component } from '@angular/core';
import { VaccinationCenter } from '../vaccination-center';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-vaccination-center',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './vaccination-center.component.html',
  styleUrl: './vaccination-center.component.scss'
})

export class VaccinationCenterComponent {
  clearName() {
      this.center.name = '';
  }

  center: VaccinationCenter = {
    id: 2,
    name: 'Messehalle 1',
    address: 'Messeplatz 1',
    postalCode: '20357',
    city: 'Hamburg'
  };
}
