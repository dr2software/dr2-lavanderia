package br.com.dr2.lavanderia.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dr2.lavanderia.exception.ObjetoNaoEncontradoException;
import br.com.dr2.lavanderia.models.OrdemServico;
import br.com.dr2.lavanderia.repositorys.OrdemServicoRepository;

@Service
public class OrdemServicoServiceImpl implements OrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository osRepository;
	
	@Override
	public OrdemServico inserir(OrdemServico ordemServico) {
		
		if(ordemServico == null) {
			throw new IllegalArgumentException("Objeto OrdemServico não pode ser nulo.");
		}	
		ordemServico.setDataCadastro(Calendar.getInstance());
		return osRepository.save(ordemServico);
	}

	@Override
	public OrdemServico atualizar(OrdemServico entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdemServico buscarPorId(int id) throws ObjetoNaoEncontradoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletarPorId(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OrdemServico> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
}
