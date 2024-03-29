package org.springframework.inmocasa.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.UsuarioService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.inmocasa.web.validator.ClienteValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	ClienteService clienteService;
	
	ViviendaService viviendaService;
	PropietarioService propietarioService;
	UsuarioService usuarioService;
	
	@InitBinder("cliente")
	public void initCompraBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ClienteValidator());
	}
	
	@Autowired
	public ClienteController(ClienteService clienteService, ViviendaService viviendaService,PropietarioService propietarioService, UsuarioService usuarioService) {
		this.clienteService = clienteService;
		this.viviendaService = viviendaService;
		this.propietarioService = propietarioService;
		this.usuarioService = usuarioService;
	}


	// Santi-Alvaro


	@GetMapping(path = "/new")
	public String crearCliente(ModelMap model) {
		Map<String, String> generos = new LinkedHashMap<String, String>();
		for (Genero gen : Genero.values()) {
			generos.put(gen.name(), gen.getDisplayName());
		}
		model.addAttribute("generos", generos);
		
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		return "clientes/registroClientes";
	}

	@PostMapping(path = "/save")
	public String guardarCliente(@Valid Cliente cliente, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("cliente", cliente);
			return "clientes/registroClientes";
		} else {
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(usuarioService.findUsuarioByUsername(cliente.getUsername())!= null) {
				model.addAttribute("cliente", cliente);
				model.addAttribute("error", "El usuario ya existe.");
				return "clientes/registroClientes";
			}
			clienteService.saveCliente(cliente);
			model.addAttribute("message", "Cliente creado");

		}
		return "redirect:/login";
	}

	@GetMapping(value = { "/miPerfil" })
	public ModelAndView showMyProfile() {
		ModelAndView res = new ModelAndView("clientes/profile");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		String username = userPrincipal.getUsername();
		Cliente cliente = clienteService.findClienteByClienteUsername(username);
		if (cliente != null) {
			res.addObject("cliente", cliente);
		}

		return res;
	}

	@GetMapping("/{clienteId}/edit")
	public String editProfile(@PathVariable("clienteId") int clienteId,ModelMap model) {
		String view = "clientes/editCliente";
		Map<String, String> generos = new LinkedHashMap<String, String>();
		for (Genero gen : Genero.values()) {
			generos.put(gen.name(), gen.getDisplayName());
		}
		model.addAttribute("generos", generos);
		model.addAttribute("cliente",this.clienteService.findClienteById(clienteId));
		return view;
	}

	@PostMapping(path = "/{clienteId}/save")
	public String processUpdateForm(@Valid Cliente cliente, BindingResult res, ModelMap modelMap) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		if (res.hasErrors()) {
			modelMap.addAttribute("cliente", cliente);
			return "clientes/editCliente";
		} else {
			if(usuarioService.findUsuarioByUsername(cliente.getUsername())!= null && !userPrincipal.getUsername().equals(cliente.getUsername())) {
//				modelMap.addAttribute("cliente", cliente);
				modelMap.addAttribute("error", "El usuario ya existe.");
				return "clientes/editCliente";
			}
			clienteService.saveCliente(cliente);
			modelMap.addAttribute("message", "Saved successfully");
		}
		return "clientes/profile";
	}

	
	
	//Alvaro-MiguelEmmanuel
	
	@GetMapping(value = "/{viviendaId}/favoritos")
	public String añadirFavorito(@PathVariable("viviendaId") int viviendaId, ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		Cliente cliente = clienteService.findByUsername(userPrincipal.getUsername());
		Vivienda vivienda = clienteService.findViviendaById(viviendaId);
		List<Vivienda> favoritas = new ArrayList<>();
		boolean esFavorita = compruebaFav(cliente, vivienda);

		if(!esFavorita) {
			favoritas.addAll(cliente.getFavoritas());
			favoritas.add(vivienda);
			cliente.setFavoritas(favoritas);
			vivienda.setFav(true);
			clienteService.save(cliente);
			viviendaService.save(vivienda);
			model.addAttribute("clientes", cliente);
			model.addAttribute("success", "La vivienda ha sido añadida a favoritos correctamente");
		}else {
			model.addAttribute("warning", "La vivienda ya estaba seleccionada como favorita");
		}
		
		return favoritos(model);
	}
	
	
	private boolean compruebaFav(Cliente cliente, Vivienda vivienda) {
		for (Vivienda viv : cliente.getFavoritas()) {
			if(viv.getId().equals(vivienda.getId()))
				return true;
		}
		return false;
	}

	@GetMapping(value = "/lista/favoritas")
	public String favoritos(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		Cliente cliente = clienteService.findByUsername(userPrincipal.getUsername());
		model.addAttribute("viviendas", cliente.getFavoritas());
		return "viviendas/favoritas";
		
	}
	//Alba-Alejandro
	
	
}
