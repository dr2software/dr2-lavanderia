package br.com.dr2.lavanderia.controllers;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.dr2.lavanderia.models.enums.TipoUnidadeMedida;

@Controller
@RequestMapping("/servico")
public class ServicoCadastroController {

	@GetMapping("/cadastro")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("servico/cadastro");
		adicionarDadosPadrao(mav);
		return mav;
	}

	private void adicionarDadosPadrao(ModelAndView mav){
		mav.addObject("medidas", Arrays.asList(TipoUnidadeMedida.values()));
	}

}
