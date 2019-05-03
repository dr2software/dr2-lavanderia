package br.com.dr2.lavanderia.services;

import javax.servlet.http.HttpServletResponse;

import br.com.dr2.lavanderia.models.OrdemServico;

public interface OrdemServicoService extends IService<OrdemServico> {

	void gerarPDF(int idOS, HttpServletResponse response);

}
