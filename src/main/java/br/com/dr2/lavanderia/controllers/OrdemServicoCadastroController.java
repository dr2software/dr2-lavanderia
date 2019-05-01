package br.com.dr2.lavanderia.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.dr2.lavanderia.models.OrdemServico;
import br.com.dr2.lavanderia.models.Servico;
import br.com.dr2.lavanderia.models.ServicoOS;
import br.com.dr2.lavanderia.models.enums.TipoSituacao;
import br.com.dr2.lavanderia.services.ClienteServiceImpl;
import br.com.dr2.lavanderia.services.OrdemServicoServiceImpl;
import br.com.dr2.lavanderia.services.ServicoServiceImpl;

@Controller
@RequestMapping("/ordem-servico/cadastro")
public class OrdemServicoCadastroController {

	@Autowired
	private OrdemServicoServiceImpl osService;
	@Autowired
	private ServicoServiceImpl servicoService;
	@Autowired
	private ClienteServiceImpl clienteService;

	private List<ServicoOS> servicosOS;

	@GetMapping
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("ordem-servico/cadastro");
		OrdemServico ordemServico = new OrdemServico();
		adicionarDadosPadrao(mav, ordemServico);
		return mav;
	}

	@PostMapping
	public ModelAndView salvar(@Valid OrdemServico ordemServico) {
		ModelAndView mav = new ModelAndView("ordem-servico/cadastro");
		if (ordemServico.getId() == 0) {
			osService.inserir(ordemServico);
		} else {
			osService.atualizar(ordemServico);
		}
		adicionarDadosPadrao(mav, null);
		return mav;
	}

	@PostMapping("/adicionar-servico")
	public ModelAndView adicionarServicoOS(@RequestParam int id, 
			                             @RequestParam String nome, 
										 @RequestParam double valor,
										 @RequestParam int quantidade) {

		ModelAndView mav = new ModelAndView("ordem-servico/cadastro :: grid-servicos_os");

		Servico servico = new Servico();
		ServicoOS servicoOS = new ServicoOS();
		servico.setId(id);
		servico.setNome(nome);
		servico.setValor(valor);
		servicoOS.setQuantidadeServicos(quantidade);
		servicoOS.setServico(servico);
		getServicosOS().add(servicoOS);
		return mav;
	}
	
	@DeleteMapping("/excluir-servico-os/{id}")
	public ModelAndView removerServicoOS(){
		
		ModelAndView mav = new ModelAndView("ordem-servico/cadastro :: grid-servicos_os");
		
		return mav;
		
	}

	@GetMapping("/grid-servicos")
	public ModelAndView griServicosOS() {
		ModelAndView mav = new ModelAndView("/ordem-servico/cadastro :: grid-servicos_os");
		mav.addObject("servicosOS", getServicosOS());
		return mav;
	}

	private void adicionarDadosPadrao(ModelAndView mav, OrdemServico ordemServico) {
		if (ordemServico == null) {
			ordemServico = new OrdemServico();
		}
		setServicosOS(null);
		mav.addObject("situacoes", Arrays.asList(TipoSituacao.values()));
		mav.addObject("clientes", clienteService.buscarTodos());
		mav.addObject("servicos", servicoService.buscarTodos());
		mav.addObject("ordemServico", ordemServico);
		
	}

	private List<ServicoOS> getServicosOS() {
		if (servicosOS == null) {
			servicosOS = new ArrayList<>();
		}
		return servicosOS;
	}
	
	private void setServicosOS(List<ServicoOS> servicosOS) {
		this.servicosOS = servicosOS;
	}

}
