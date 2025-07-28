import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { finalize } from 'rxjs';

import { DefaultFormLayoutComponent } from '../../../components/default-form-layout/default-form-layout.component';
import { CategoriaService } from '../../../services/categoria.service';

@Component({
  selector: 'app-categoria-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    DefaultFormLayoutComponent
  ],
  templateUrl: './categoria-form.component.html',
  styleUrls: ['./categoria-form.component.scss']
})
export class CategoriaFormComponent implements OnInit {
  categoriaForm!: FormGroup;
  isLoading = false;

  tiposCategoria = [
    { value: 'ENTRADA', label: 'Entrada' },
    { value: 'INVESTIMENTO', label: 'Investimento' },
    { value: 'SAIDA', label: 'Saída' }
  ];

  constructor(
    private fb: FormBuilder,
    private categoriaService: CategoriaService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.buildForm();
  }

  private buildForm(): void {
    this.categoriaForm = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(3)]],
      tipoCategoria: [null, Validators.required]
    });
  }

  onSave(): void {
    if (this.categoriaForm.invalid) {
      this.categoriaForm.markAllAsTouched();
      return;
    }

    this.isLoading = true;

    const formValue = this.categoriaForm.getRawValue();

    const payload = {
      ...formValue,
      valor: Math.round(formValue.valor * 100),

    };

    this.categoriaService.save(payload).pipe(
      finalize(() => this.isLoading = false)
    ).subscribe({
      next: () => {
        alert('Categoria cadastrada com sucesso!');
        this.router.navigate(['/categoria']);
      },
      error: () => {
        alert('Ocorreu um erro. Tente novamente.');
      }
    });
  }

  onCancel(): void {
    this.router.navigate(['/categoria']);
  }

}
