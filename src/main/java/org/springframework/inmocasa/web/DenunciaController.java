package org.springframework.inmocasa.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Denuncia;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.service.DenunciaService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/denuncias")
public class DenunciaController {
	
	private static final String VIEWS_DENUNCIA = "denuncias/nuevaDenuncia";

	
	@Autowired
	private ViviendaService viviendaService;
	
	@Autowired
	private DenunciaService denunciaService;
	
	
	@Autowired
	public DenunciaController(ViviendaService viviendaService, DenunciaService denunciaService) {
		this.viviendaService = viviendaService;
		this.denunciaService = denunciaService;

	}
	
	@GetMapping(value = "/create/{viviendaId}")
	public String create(@PathVariable("viviendaId") Integer viviendaId, ModelMap model) {
		Denuncia denuncia = new Denuncia();
		Vivienda vivienda = viviendaService.findViviendaById(viviendaId).orElse(null);

		denuncia.setVivienda(vivienda);
		model.put("denuncia", denuncia);
		return VIEWS_DENUNCIA;
	}

	@PostMapping(value = "/create/{viviendaId}")
	public String save(@PathVariable("viviendaId") Integer viviendaId, @Valid Denuncia denuncia, BindingResult binding,
			ModelMap model) {
		if (binding.hasErrors()) {
			model.put("denuncia", denuncia);
			return VIEWS_DENUNCIA;
		} else {
			Vivienda vivienda = viviendaService.findViviendaById(viviendaId).orElse(null);

			denuncia.setVivienda(vivienda);
			denunciaService.save(denuncia);
			return "redirect:/viviendas/allNew";
		}
	}

}
