import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EnderecoDTO } from '../model/EnderecoDTO';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EnderecoVIACEPService {

  constructor(private http: HttpClient) { }

  public getCEP(cep: string): Observable<EnderecoDTO> {
    return this.http.get<EnderecoDTO>("https://viacep.com.br/ws/"+`${cep}`+"/json");
  }

}
