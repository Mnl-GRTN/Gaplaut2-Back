import { Component, EventEmitter, Input, Output } from '@angular/core';
import { VaccinationCenter } from '../vaccination-center';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-vaccination-center',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './vaccination-center.component.html',
  styleUrl: './vaccination-center.component.scss'
})

export class VaccinationCenterComponent {

  @Input() center?: VaccinationCenter;

  @Output() deleted = new EventEmitter<VaccinationCenter>();

  clearName() {
      this.center!.name = '';
  }

  delete(){
    this.deleted.emit(this.center);
  }
}
