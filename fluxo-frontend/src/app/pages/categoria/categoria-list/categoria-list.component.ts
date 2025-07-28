import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DefaultListLayoutComponent } from '../../../components/default-list-layout/default-list-layout.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CategoriaService } from '../../../services/categoria.service';
import { Categoria } from '../../../types/categoria.type';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-categoria-list',
  standalone: true,
  imports: [DefaultListLayoutComponent, CommonModule, FormsModule, HttpClientModule],
  templateUrl: './categoria-list.component.html',
  styleUrls: ['./categoria-list.component.scss']
})
export class CategoriaListComponent implements OnInit {
  pesquisa: string = '';

  allCategorias: Categoria[] = [];
  filteredCategorias: Categoria[] = [];

  constructor(private router: Router, private categoriaService: CategoriaService) { }

  ngOnInit(): void {
    this.pesquisar();
  }

  pesquisar() {
     this.categoriaService.findAll().subscribe({
      next: (data) => {
        this.allCategorias = data;
        this.applyCategoriaFilter();
      },
      error: (error) => {
        console.error('Erro ao carregar transações:', error);
      }
    });
  }

  applyCategoriaFilter(): void {
    if (!this.pesquisa) {
      this.filteredCategorias = [...this.allCategorias];
      return;
    }

    const lowerCaseSearchTerm = this.pesquisa.toLowerCase();
    this.filteredCategorias = this.allCategorias.filter(categoria =>
      categoria.nome.toLowerCase().includes(lowerCaseSearchTerm));
  }

  editCategoria(id: number): void {
    this.router.navigate(['/categoria/editar', id]);
  }

  navigateToNewCategoria() {
    this.router.navigate(['/categoria/novo']);
  }

  deleteCategoria(id: number): void {
    if (confirm(`Tem certeza que deseja excluir a categoria com ID ${id}?`)) {
      this.categoriaService.delete(id).subscribe({
        next: () => {this.pesquisar();},
        error: (err) => {console.error(`Erro ao excluir categoria ${id}:`, err);}
      });
    }
  }
}