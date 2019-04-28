package br.com.dr2.lavanderia.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dr2.lavanderia.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
