import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Transacao } from '../types/transacao.type';
import { tap, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransacaoService {
  apiUrl: string = "http://localhost:8080/transacao"

  constructor(private httpClient: HttpClient) { }

  findAll(): Observable<Transacao[]> { 
    return this.httpClient.get<Transacao[]>(this.apiUrl);
  }
}