import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Categoria } from '../types/categoria.type';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {
  apiUrl: string = "http://localhost:8080/categoria"

  constructor(private httpClient: HttpClient) { }

  findAll(): Observable<Categoria[]> { 
    return this.httpClient.get<Categoria[]>(this.apiUrl);
  }

  findAllByTipoTransacao(tipoTransacao : string): Observable<Categoria[]> { 
    return this.httpClient.get<Categoria[]>(this.apiUrl + "/tipo?" + "tipo=" + tipoTransacao);
  }
}