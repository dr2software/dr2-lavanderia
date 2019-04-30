package br.com.dr2.lavanderia.controllers;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.dr2.lavanderia.exception.ObjetoNaoEncontradoException;
import br.com.dr2.lavanderia.models.Servico;
import br.com.dr2.lavanderia.models.enums.TipoUnidadeMedida;
import br.com.dr2.lavanderia.services.ServicoServiceImpl;

@Controller
@RequestMapping("/servico/cadastro")
public class ServicoCadastroController {
	
	@Autowired
	private ServicoServiceImpl service;

	@GetMapping
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("servico/cadastro");
		adicionarDadosPadrao(mav,null);
		return mav;
	}

	@PostMapping
	public ModelAndView salvar(@Valid Servico servico) {
		ModelAndView mav = new ModelAndView("redirect:/servico/consulta");
		if(servico != null) {
			if(servico.getId() == 0) {
				service.inserir(servico);
			}else {
				service.atualizar(servico);
			}
		}
		return mav;
	}
	
	@GetMapping("/{id}")
	public ModelAndView carregar(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("servico/cadastro");
		try {
			Servico servico = service.buscarPorId(id);
			adicionarDadosPadrao(mav, servico);
		} catch (ObjetoNaoEncontradoException e) {
			e.printStackTrace();
		}
		return mav;
	}

	private void adicionarDadosPadrao(ModelAndView mav, Servico servico) {
		if(servico == null) {
			servico = new Servico();
		}
		mav.addObject("medidas", Arrays.asList(TipoUnidadeMedida.values()));
		mav.addObject("servico", servico);
	}

}
