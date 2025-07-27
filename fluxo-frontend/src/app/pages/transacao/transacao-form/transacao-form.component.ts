import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { finalize } from 'rxjs';

import { DefaultFormLayoutComponent } from '../../../components/default-form-layout/default-form-layout.component';
import { TransacaoService } from '../../../services/transacao.service';
import { CategoriaService } from '../../../services/categoria.service';
import { Categoria } from '../../../types/categoria.type';

@Component({
  selector: 'app-transacao-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    DefaultFormLayoutComponent
  ],
  templateUrl: './transacao-form.component.html',
  styleUrls: ['./transacao-form.component.scss']
})
export class TransacaoFormComponent implements OnInit {
  transacaoForm!: FormGroup;
  categorias: Categoria[] = [];
  isLoading = false;
  tipoTransacao : string = '';

  tiposTransacao = [
    { value: 'ENTRADA', label: 'Entrada' },
    { value: 'INVESTIMENTO', label: 'Investimento' },
    { value: 'SAIDA', label: 'Saída' }
  ];

  constructor(
    private fb: FormBuilder,
    private transacaoService: TransacaoService,
    private categoriaService: CategoriaService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.buildForm();
  }

  private buildForm(): void {
    this.transacaoForm = this.fb.group({
      descricao: ['', [Validators.required, Validators.minLength(3)]],
      valor: [null, [Validators.required, Validators.min(0.01)]],
      dataTransacao: [new Date().toISOString().split('T')[0], Validators.required],
      tipoTransacao: [null, Validators.required],
      idCategoria: [null, Validators.required],
      mesesRecorrencia: [0, [Validators.required, Validators.min(0)]]
    });
  }

  private loadCategorias(): void {
    this.categoriaService.findAllByTipoTransacao(this.tipoTransacao).subscribe(data => {
      this.categorias = data;
    });
  }

  onSave(): void {
    if (this.transacaoForm.invalid) {
      this.transacaoForm.markAllAsTouched();
      return;
    }

    this.isLoading = true;

    const formValue = this.transacaoForm.getRawValue();

    const payload = {
      ...formValue,
      valor: Math.round(formValue.valor * 100),

    };

    this.transacaoService.save(payload).pipe(
      finalize(() => this.isLoading = false)
    ).subscribe({
      next: () => {
        alert('Transação cadastrada com sucesso!');
        this.router.navigate(['/transacao']);
      },
      error: () => {
        alert('Ocorreu um erro. Tente novamente.');
      }
    });
  }

  onCancel(): void {
    this.router.navigate(['/transacao']);
  }

  onTipoTransacaoChange(){
    this.loadCategorias();
  }
}
