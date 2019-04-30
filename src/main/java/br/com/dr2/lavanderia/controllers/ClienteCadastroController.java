package br.com.dr2.lavanderia.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.dr2.lavanderia.exception.ObjetoNaoEncontradoException;
import br.com.dr2.lavanderia.models.Cliente;
import br.com.dr2.lavanderia.services.ClienteService;

@Controller
@RequestMapping("/cliente/cadastro")
public class ClienteCadastroController {

	@Autowired
	ClienteService clienteService;

	@GetMapping()
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("cliente/cadastro");
		adicionarDadosPadrao(mav, null);
		return mav;
	}

	@PostMapping
	public ModelAndView salvar(@Valid Cliente cliente) {
		ModelAndView mav = new ModelAndView("redirect:/cliente/consulta");
		if (cliente != null) {
			if (cliente.getId() == 0) {
				clienteService.inserir(cliente);
			} else {
				clienteService.atualizar(cliente);

			}
		}
		return mav;
	}

	@GetMapping("/{id}")
	public ModelAndView carregar(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("cliente/cadastro");
		try {
			Cliente cliente = clienteService.buscarPorId(id);
			adicionarDadosPadrao(mav, cliente);
		} catch (ObjetoNaoEncontradoException e) {
			e.printStackTrace();
		}
		return mav;
	}

	private void adicionarDadosPadrao(ModelAndView mav, Cliente cliente) {
		if (cliente == null) {
			cliente = new Cliente();
		}
		mav.addObject("cliente", cliente);
	}
}
