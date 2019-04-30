package br.com.dr2.lavanderia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dr2.lavanderia.exception.ObjetoNaoEncontradoException;
import br.com.dr2.lavanderia.models.Cliente;
import br.com.dr2.lavanderia.repositorys.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente inserir(Cliente entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente atualizar(Cliente entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente buscarPorId(int id) throws ObjetoNaoEncontradoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletarPorId(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

}
package br.com.dr2.lavanderia.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dr2.lavanderia.exception.ObjetoNaoEncontradoException;
import br.com.dr2.lavanderia.models.Cliente;
import br.com.dr2.lavanderia.repositorys.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository clienteRepo;

	@Override
	public Cliente inserir(Cliente cliente) {
		if (cliente == null) {
			throw new IllegalArgumentException("O cliente não pode ser nulo");
		}

		cliente.setDataCadastro(Calendar.getInstance());
		return clienteRepo.save(cliente);
	}

	@Override
	public Cliente atualizar(Cliente cliente) {
		if (cliente.getId() <= 0) {
			throw new IllegalArgumentException("O cliente não pode ser nulo");
		}
		cliente.setDataAlteracao(Calendar.getInstance());
		clienteRepo.save(cliente);
		return null;
	}

	@Override
	public Cliente buscarPorId(int id) throws ObjetoNaoEncontradoException {
		if (id <= 0) {
			throw new IllegalArgumentException("O id não pode ser null ou <=0");
		}
		Optional<Cliente> cliente = clienteRepo.findById(id);
		return cliente.orElseThrow(() -> new ObjetoNaoEncontradoException("Busca por id: Id não encontrado"));
	}

	@Override
	public void deletarPorId(int id) {

	}

	@Override
	public List<Cliente> buscarTodos() {
		return clienteRepo.findAll();
	}

}
