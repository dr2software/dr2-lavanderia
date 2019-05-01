package br.com.dr2.lavanderia.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dr2.lavanderia.models.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer>{
}
