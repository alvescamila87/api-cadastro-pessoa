package br.com.camila.cadastropessoa.services;

import java.util.List;
import java.util.Optional;


import br.com.camila.cadastropessoa.dto.PessoaDTO;
import br.com.camila.cadastropessoa.model.Endereco;
import br.com.camila.cadastropessoa.repositories.EnderecoRepository;
import br.com.camila.cadastropessoa.repositories.PessoaRepository;
import br.com.camila.cadastropessoa.utils.ValidaCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camila.cadastropessoa.model.Pessoa;


@Service
public class PessoaServiceImpl implements IPessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public List<Pessoa> recuperarTodasPessoas() {
		return pessoaRepository.findAll();
	}

	@Override
	public Pessoa recuperarPessoaPorId(Long idPessoa) {
		Optional<Pessoa> pessoaPesquisada = pessoaRepository.findById(idPessoa);

		if(pessoaPesquisada.isPresent()) {
			return pessoaPesquisada.get();
		}
		return null;
	}

	@Override
	public Pessoa salvarPessoa(PessoaDTO pessoa) {
		return salvarPessoaComCEP(pessoa);
	}

	@Override
	public Pessoa atualizar(Long idPessoa, PessoaDTO pessoa) {
		Optional<Pessoa> pessoaPesquisada = pessoaRepository.findById(idPessoa);

		if(pessoaPesquisada.isPresent()) {
			return salvarPessoaComCEP(pessoa);
		}
        return null;
    }

	@Override
	public void excluirPessoa(Long idPessoa) {
		Optional<Pessoa> pessoaPesquisada = pessoaRepository.findById(idPessoa);

		if(pessoaPesquisada.isPresent()) {
			pessoaRepository.deleteById(idPessoa);
		}
	}

	private Pessoa salvarPessoaComCEP(PessoaDTO pessoaDTO){
		Pessoa novaPessoa = new Pessoa();
		novaPessoa.setId(pessoaDTO.getId());
		novaPessoa.setNomeCompleto(pessoaDTO.getNomeCompleto());

		if (!ValidaCPF.isCPF(pessoaDTO.getCpf())) {
			throw new IllegalArgumentException("O CPF informado: " + pessoaDTO.getCpf() + " é inválido.");
		}

		String cpfFormatado = ValidaCPF.imprimeCPF(pessoaDTO.getCpf().replaceAll("\\D", ""));
		novaPessoa.setCpf(cpfFormatado);
		//novaPessoa.setCpf(pessoaDTO.getCpf());
		novaPessoa.setTelefone(pessoaDTO.getTelefone());

		Endereco novoEndereco = enderecoRepository.save(pessoaDTO.getEndereco());
		novaPessoa.setEndereco(novoEndereco);

		pessoaRepository.save(novaPessoa);
        return novaPessoa;
	}
}