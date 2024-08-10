import { EnderecoDTO } from "./EnderecoDTO";

export interface PessoaDTO {
  id: number;
  nomeCompleto: string;
  cpf: string;
  telefone: string;
  endereco: EnderecoDTO;
}
