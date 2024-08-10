import { EnderecoDTO } from "./EnderecoDTO";

export class PessoaDTO {
  id: number = 0;
  nomeCompleto: string = '';
  cpf: string = '';
  telefone: string = '';
  endereco: EnderecoDTO = new EnderecoDTO();
}
