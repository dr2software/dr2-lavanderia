package br.com.dr2.lavanderia.services;

public interface IService<T> {

	T inserir(T entity);

	T atualizar(T entity);

	T buscarPorId(int id);

	void deletarPorId(int id);

}
