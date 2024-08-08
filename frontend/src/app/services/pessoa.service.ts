import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PessoaDTO } from '../model/PessoaDTO';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  private urlAPI = 'http://localhost:8080/pessoas';

  constructor(private http: HttpClient) { }

  public getAllPessoas(): Observable<PessoaDTO[]>  {
    console.log("Estou no PESSOASERVICE: Entrei em contato com o Backend")
    return this.http.get<PessoaDTO[]>(this.urlAPI);
  }

  public createPessoa(pessoa: PessoaDTO): Observable<PessoaDTO> {
    return this.http.post<PessoaDTO>(this.urlAPI, pessoa);
  }
}
