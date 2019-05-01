package br.com.dr2.lavanderia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.dr2.lavanderia.services.ClienteServiceImpl;

@Controller
@RequestMapping("/cliente/consulta")
public class ClienteConsultaController {

	@Autowired
	private ClienteServiceImpl clienteService;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView mav = new ModelAndView("cliente/consulta");
		mav.addObject("clientes", clienteService.buscarTodos());
		return mav;
	}
}
