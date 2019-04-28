package br.com.dr2.lavanderia.services;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dr2.lavanderia.exception.ObjetoNaoEncontradoException;
import br.com.dr2.lavanderia.models.Servico;
import br.com.dr2.lavanderia.repositorys.ServicoRepository;

@Service
public class ServicoServiceImpl implements ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;

	@Override
	public Servico inserir(Servico servico) {

		if (servico == null) {
			throw new IllegalArgumentException("Objeto Servico não pode ser nulo.");
		}
		servico.setDataCadastro(Calendar.getInstance());
		return servicoRepository.save(servico);
	}

	@Override
	public Servico atualizar(Servico entity) {

		return null;
	}

	@Override
	public Servico buscarPorId(int id) throws ObjetoNaoEncontradoException {
		if (id <= 0) {
			throw new IllegalArgumentException("ID do Servico não pode ser <= 0");
		}
		Optional<Servico> servico = servicoRepository.findById(id);
		return servico.orElseThrow(() -> new ObjetoNaoEncontradoException("Busca por ID - Serviço não encontrado"));
	}

	@Override
	public void deletarPorId(int id) {
		// TODO Auto-generated method stub

	}

}
