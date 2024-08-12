import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PessoaDTO } from '../model/PessoaDTO';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  private urlAPI = 'http://localhost:9000/pessoas';

  constructor(private http: HttpClient) { }

  public getAllPessoas(): Observable<PessoaDTO[]>  {
    //console.log("Estou no PESSOASERVICE - GetAll: Entrei em contato com o Backend")
    return this.http.get<PessoaDTO[]>(this.urlAPI);
  }

  public getPessoaById(id: number): Observable<PessoaDTO>{
    //console.log("Estou no PESSOASERVICE - GetById: Entrei em contato com o Backend")
    return this.http.get<PessoaDTO>(`${this.urlAPI}/${id}`);
  }

  public createPessoa(pessoa: PessoaDTO): Observable<PessoaDTO> {
    ///console.log("Estou no PESSOASERVICE - POST: Entrei em contato com o Backend")
    return this.http.post<PessoaDTO>(this.urlAPI, pessoa);
  }

  public updatePessoa(id: number, pessoa: PessoaDTO): Observable<PessoaDTO>{
    //console.log("Estou no PESSOASERVICE - PUT: Entrei em contato com o Backend")
    return this.http.put<PessoaDTO>(`${this.urlAPI}/${id}`, pessoa);
  }

  public deletePessoa(id: number): Observable<void>{
    //console.log("Estou no PESSOASERVICE - DELETE: Entrei em contato com o Backend")
    return this.http.delete<void>(`${this.urlAPI}/${id}`);
  }

  public reportCSV(): Observable<Blob>{
    console.log("Estou no PESSOASERVICE - RELATORIO: Entrei em contato com o Backend")
    return this.http.get(`${this.urlAPI}/relatorio`, { responseType: 'blob' });
  }

}
