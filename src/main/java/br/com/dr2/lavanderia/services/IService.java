package br.com.dr2.lavanderia.services;

import java.util.List;

import br.com.dr2.lavanderia.exception.ObjetoNaoEncontradoException;

public interface IService<T> {

	T inserir(T entity);

	T atualizar(T entity);

	T buscarPorId(int id) throws ObjetoNaoEncontradoException;

	void deletarPorId(int id);
	
	List<T> buscarTodos();

}
