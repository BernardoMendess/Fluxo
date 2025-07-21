import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-default-list-layout',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './default-list-layout.component.html',
  styleUrl: './default-list-layout.component.scss'
})
export class DefaultListLayoutComponent {
  @Input() title: string = "Título da Lista";
  @Input() primaryBtnText: string = "";
  @Input() disablePrimaryBtn: boolean = false;
  @Input() showPrimaryBtnIcon: boolean = true;

  @Output("primaryAction") onPrimaryAction = new EventEmitter<void>();

  constructor() { }

  emitPrimaryAction(): void {
    this.onPrimaryAction.emit();
  }
}