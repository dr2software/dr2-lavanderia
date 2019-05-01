package br.com.dr2.lavanderia.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dr2.lavanderia.exception.ObjetoNaoEncontradoException;
import br.com.dr2.lavanderia.models.OrdemServico;
import br.com.dr2.lavanderia.models.Servico;
import br.com.dr2.lavanderia.models.ServicoOS;
import br.com.dr2.lavanderia.repositorys.OrdemServicoRepository;
import br.com.dr2.lavanderia.repositorys.ServicoRepository;

@Service
public class OrdemServicoServiceImpl implements OrdemServicoService {

	@Autowired
	private OrdemServicoRepository osRepository;

	@Autowired
	private ServicoRepository servicoRepository;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public OrdemServico inserir(OrdemServico ordemServico) {

		if (ordemServico == null) {
			throw new IllegalArgumentException("Objeto OrdemServico não pode ser nulo.");
		}

		validarServicoOS(ordemServico);
		ordemServico.setDataCadastro(Calendar.getInstance());
		return osRepository.save(ordemServico);
	}

	@Override
	public OrdemServico atualizar(OrdemServico entity) {
		return null;
	}

	@Override
	public OrdemServico buscarPorId(int id) throws ObjetoNaoEncontradoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletarPorId(int id) {
	}

	@Override
	public List<OrdemServico> buscarTodos() {
		return osRepository.findAll();
	}

	private void validarServicoOS(OrdemServico ordemServico) {

		if (ordemServico.getServicos() != null || !ordemServico.getServicos().isEmpty()) {
			Servico servico = null;
			for (ServicoOS os : ordemServico.getServicos()) {
				if (os.getServico() != null) {
					os.setOrdemServico(ordemServico);
					servico = servicoRepository.getOne(os.getServico().getId());
					os.setServico(servico);
				}
			}
		}
	}
}
