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

  findAll(): Observable<Transacao[]> { 
    return this.httpClient.get<Transacao[]>(this.apiUrl);
  }

  save(transacao: Transacao): Observable<Transacao> {
    return this.httpClient.post<Transacao>(this.apiUrl, transacao);
  }

  delete(id: number): Observable<void> { 
    return this.httpClient.delete<void>(this.apiUrl + '/' + id);
  }
}