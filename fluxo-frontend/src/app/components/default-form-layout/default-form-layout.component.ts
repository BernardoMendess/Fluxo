import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-default-form-layout',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './default-form-layout.component.html',
  styleUrl: './default-form-layout.component.scss'
})
export class DefaultFormLayoutComponent {
  @Input() title: string = "Formulário";
  @Input() submitBtnText: string = "Salvar";
  @Input() backBtnText: string = "Voltar";
  @Input() disableSubmitBtn: boolean = false;
  
  @Output("submitAction") onSubmitAction = new EventEmitter<void>();
  @Output("backAction") onBackAction = new EventEmitter<void>();

  emitSubmitAction(): void {
    this.onSubmitAction.emit();
  }

  emitBackAction(): void {
    this.onBackAction.emit();
  }
}