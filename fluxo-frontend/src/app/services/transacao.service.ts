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

  findAll(data: string | null): Observable<Transacao[]> {
    let url = this.apiUrl;
    if (data) { url += '?data=' + data; }
    return this.httpClient.get<Transacao[]>(url);
  }

  save(transacao: Transacao): Observable<Transacao> {
    return this.httpClient.post<Transacao>(this.apiUrl, transacao);
  }

  delete(id: number): Observable<void> { 
    return this.httpClient.delete<void>(this.apiUrl + '/' + id);
  }
}