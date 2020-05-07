package org.springframework.inmocasa.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	ClienteService clienteService;
	
	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}


	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	
	@GetMapping(value = "/{viviendaId}/favoritos")
	public String añadirFavorito(@PathVariable("viviendaId") int viviendaId, ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		Cliente cliente = clienteService.findByUsername(userPrincipal.getUsername());
		Vivienda vivienda = clienteService.findViviendaById(viviendaId);
		List<Vivienda> favoritas = new ArrayList<>();
		favoritas.addAll(cliente.getFavoritas());
		favoritas.add(vivienda);
		cliente.setFavoritas(favoritas);
		clienteService.save(cliente);
		model.addAttribute("clientes", cliente);
		model.addAttribute("message", "La vivienda ha sido añadida a favoritos correctamente");
		return favoritos(model);
	}
	
	
	@GetMapping(value = "/lista/favoritas")
	public String favoritos(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		Cliente cliente = clienteService.findByUsername(userPrincipal.getUsername());
		model.addAttribute("viviendas", cliente.getFavoritas());
		return "viviendas/listNewViviendas";
		
	}
	//Alba-Alejandro
	
	
}
