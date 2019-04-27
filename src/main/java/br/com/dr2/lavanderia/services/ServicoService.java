package br.com.dr2.lavanderia.services;

import org.springframework.stereotype.Service;

import br.com.dr2.lavanderia.models.Servico;

@Service
public class ServicoService implements IService<Servico> {

	@Override
	public Servico inserir(Servico servico) {
		
		if (servico == null) {
			throw new IllegalArgumentException("Objeto Servico não pode ser nulo.");
		}
		
		return null;
	}

	@Override
	public Servico atualizar(Servico entity) {

		return null;
	}

	@Override
	public Servico buscarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletarPorId(int id) {
		// TODO Auto-generated method stub

	}

}
