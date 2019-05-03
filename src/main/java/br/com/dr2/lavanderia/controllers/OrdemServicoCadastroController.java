package br.com.dr2.lavanderia.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.dr2.lavanderia.exception.ObjetoNaoEncontradoException;
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
		ModelAndView mav = new ModelAndView("redirect:/ordem-servico/consulta");
		if (ordemServico.getId() == 0) {
			ordemServico.setServicos(getServicosOS());
			osService.inserir(ordemServico);
		} else {
			ordemServico.setServicos(getServicosOS());
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
	
	@DeleteMapping("/excluir-servico-os/{index}")
	public ModelAndView removerServicoOS(@PathVariable int index){
		ModelAndView mav = new ModelAndView("ordem-servico/cadastro :: grid-servicos_os");
		if(!getServicosOS().isEmpty()) {
			getServicosOS().remove(index);
		}
		return mav;
	}

	@GetMapping("/grid-servicos")
	public ModelAndView griServicosOS() {
		ModelAndView mav = new ModelAndView("/ordem-servico/cadastro :: grid-servicos_os");
		mav.addObject("servicosOS", getServicosOS());
		return mav;
	}
	
	@GetMapping("/{id}")
	public ModelAndView carregar(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("ordem-servico/cadastro");
		try {
			OrdemServico os = osService.buscarPorId(id) ;
			adicionarDadosPadrao(mav, os);
		} catch (ObjetoNaoEncontradoException e) {
			e.printStackTrace();
		}
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
		if(ordemServico.getServicos() == null || ordemServico.getServicos().isEmpty()) {
			mav.addObject("servicosOS", getServicosOS());
		}else {
			mav.addObject("servicosOS", ordemServico.getServicos());
			setServicosOS(ordemServico.getServicos());
		}
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
