package br.com.dr2.lavanderia.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.dr2.lavanderia.services.OrdemServicoServiceImpl;

@Controller
@RequestMapping("/ordem-servico/consulta")
public class OrdemServicoConsultaController {

	@Autowired
	private OrdemServicoServiceImpl ordemServicoService;
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mav = new ModelAndView("ordem-servico/consulta");
		mav.addObject("ordensServicos", ordemServicoService.buscarTodos());
		return mav;
	}

	@GetMapping("/pdf-os/{id}")
	public void gerarPDF(@PathVariable int id, HttpServletResponse response) {
		ordemServicoService.gerarPDF(id, response);
	}
	
}
