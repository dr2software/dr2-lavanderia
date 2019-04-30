package br.com.dr2.lavanderia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.dr2.lavanderia.services.ServicoServiceImpl;

@Controller
@RequestMapping("/servico/consulta")
public class ServicoConsultaController {

	@Autowired
	private ServicoServiceImpl service;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView mav = new ModelAndView("servico/consulta");
		mav.addObject("servicos", service.buscarTodos());
		return mav;
	}

}
