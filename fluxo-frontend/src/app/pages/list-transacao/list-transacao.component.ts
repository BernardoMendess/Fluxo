import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DefaultListLayoutComponent } from '../../components/default-list-layout/default-list-layout.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface Transacao {
  id: number;
  descricao: string;
  valor: number;
  tipoTransacao: 'RECEITA' | 'DESPESA';
  dataTransacao: string;
}

@Component({
  selector: 'app-transacao-list',
  standalone: true,
  imports: [DefaultListLayoutComponent, CommonModule, FormsModule],
  templateUrl: './list-transacao.component.html',
  styleUrls: ['./list-transacao.component.scss']
})
export class TransacaoListComponent implements OnInit {
  searchTerm: string = '';

  private allTransacoes: Transacao[] = [
    { id: 1, descricao: 'Aluguel do Apt', valor: 1500.00, tipoTransacao: 'DESPESA', dataTransacao: '2025-07-01' },
    { id: 2, descricao: 'Salário Mensal', valor: 5000.00, tipoTransacao: 'RECEITA', dataTransacao: '2025-07-05' },
    { id: 3, descricao: 'Compras Supermercado', valor: 350.50, tipoTransacao: 'DESPESA', dataTransacao: '2025-07-10' },
    { id: 4, descricao: 'Freelance Design', valor: 800.00, tipoTransacao: 'RECEITA', dataTransacao: '2025-07-12' },
    { id: 5, descricao: 'Conta de Luz', valor: 120.75, tipoTransacao: 'DESPESA', dataTransacao: '2025-07-15' },
    { id: 6, descricao: 'Internet', valor: 99.90, tipoTransacao: 'DESPESA', dataTransacao: '2025-07-15' },
  ];

  filteredTransacoes: Transacao[] = [];

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.filteredTransacoes = [...this.allTransacoes];
  }

  applyTransacaoFilter(): void {
    if (!this.searchTerm) {
      this.filteredTransacoes = [...this.allTransacoes];
      return;
    }

    const lowerCaseSearchTerm = this.searchTerm.toLowerCase();
    this.filteredTransacoes = this.allTransacoes.filter(transacao =>
      transacao.descricao.toLowerCase().includes(lowerCaseSearchTerm) ||
      transacao.tipoTransacao.toLowerCase().includes(lowerCaseSearchTerm) ||
      transacao.dataTransacao.includes(lowerCaseSearchTerm)
    );
  }

  navigateToNewTransacao(): void {
    this.router.navigate(['/transacoes/novo']);
  }

  editTransacao(id: number): void {
    this.router.navigate(['/transacoes/editar', id]);
  }

  deleteTransacao(id: number): void {
    if (confirm(`Tem certeza que deseja excluir a transação com ID ${id}?`)) {
      console.log(`Excluindo transação com ID: ${id}`);
      this.allTransacoes = this.allTransacoes.filter(transacao => transacao.id !== id);
      this.applyTransacaoFilter();
    }
  }
}