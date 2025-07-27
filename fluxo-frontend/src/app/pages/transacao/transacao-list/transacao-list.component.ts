import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DefaultListLayoutComponent } from '../../../components/default-list-layout/default-list-layout.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TransacaoService } from '../../../services/transacao.service';
import { Transacao } from '../../../types/transacao.type';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-transacao-list',
  standalone: true,
  imports: [DefaultListLayoutComponent, CommonModule, FormsModule, HttpClientModule],
  templateUrl: './transacao-list.component.html',
  styleUrls: ['./transacao-list.component.scss']
})
export class TransacaoListComponent implements OnInit {
  pesquisa: string = '';

  allTransacoes: Transacao[] = [];
  filteredTransacoes: Transacao[] = [];

  constructor(private router: Router, private transacaoService: TransacaoService) { }

  ngOnInit(): void {
    this.pesquisar();
  }

  pesquisar() {
     this.transacaoService.findAll().subscribe({
      next: (data) => {
        this.allTransacoes = data;
        this.applyTransacaoFilter();
      },
      error: (error) => {
        console.error('Erro ao carregar transações:', error);
      }
    });
  }

  applyTransacaoFilter(): void {
    if (!this.pesquisa) {
      this.filteredTransacoes = [...this.allTransacoes];
      return;
    }

    const lowerCaseSearchTerm = this.pesquisa.toLowerCase();
    this.filteredTransacoes = this.allTransacoes.filter(transacao =>
      transacao.descricao.toLowerCase().includes(lowerCaseSearchTerm) ||
      transacao.tipoTransacao.toLowerCase().includes(lowerCaseSearchTerm) ||
      (transacao.dataTransacao instanceof Date ? transacao.dataTransacao.toISOString().split('T')[0].includes(lowerCaseSearchTerm) : String(transacao.dataTransacao).includes(lowerCaseSearchTerm))
    );
  }

  navigateToNewTransacao(): void {
    this.router.navigate(['/transacao/novo']);
  }

  editTransacao(id: number): void {
    this.router.navigate(['/transacao/editar', id]);
  }

  deleteTransacao(id: number): void {
    if (confirm(`Tem certeza que deseja excluir a transação com ID ${id}?`)) {
      this.transacaoService.delete(id).subscribe({
        next: () => {this.pesquisar();},
        error: (err) => {console.error(`Erro ao excluir transação ${id}:`, err);}
      });
    }
  }
}