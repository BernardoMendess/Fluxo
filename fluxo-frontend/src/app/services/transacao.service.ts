import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Transacao } from '../types/transacao.type';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransacaoService {
  apiUrl: string = "http://localhost:8080/transacao"

  constructor(private httpClient: HttpClient) { }

  findAll(data?: string | null, categoria?: string, descricao?: string): Observable<Transacao[]> {
    let url = this.apiUrl;
    const params = [];

    if (data) params.push(`data=${data}`);
    if (categoria) params.push(`categoria=${encodeURIComponent(categoria)}`);
    if (descricao) params.push(`descricao=${encodeURIComponent(descricao)}`);

    if (params.length > 0) {
      url += '?' + params.join('&');
    }

    return this.httpClient.get<Transacao[]>(url);
  }

  save(transacao: Transacao): Observable<Transacao> {
    return this.httpClient.post<Transacao>(this.apiUrl, transacao);
  }

  delete(id: number): Observable<void> { 
    return this.httpClient.delete<void>(this.apiUrl + '/' + id);
  }
}