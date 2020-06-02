package org.springframework.inmocasa.web;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Valoracion;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.ValoracionService;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("valoracion")
@Controller
public class ValoracionController {

	private final String VISTA_FORM_VALORACION = "/visita/valoracion/createValoracionForm"; 
	private final String VISTA_MIS_VISITAS = "/usuario/misVisitas"; 

	
	UsuarioController usuarioController;
	VisitaService visitaService;
	ValoracionService valoracionService;
	ClienteService clienteService;
	PropietarioService propietarioService;
	
	@Autowired
	public ValoracionController(VisitaService visitaService, ValoracionService valoracionService,
			ClienteService clienteService, UsuarioController usuarioController,PropietarioService propietarioService) {
		super();
		this.visitaService = visitaService;
		this.valoracionService = valoracionService;
		this.clienteService = clienteService;
		this.usuarioController = usuarioController;
		this.propietarioService = propietarioService;
	}
	
	
	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	@GetMapping(value="/{visitaId}/new")
	public String createValoracion(@PathVariable("visitaId") int idVisita, ModelMap modelMap){
		
		String vista = VISTA_FORM_VALORACION;
		Valoracion val = new Valoracion();
		
		
		User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Cliente> cliente = clienteService.findClienteByUsername(usuario.getUsername());
		
		Visita visita = visitaService.findById2(idVisita);
		
		
		if(visita != null && !cliente.isEmpty() && visita.getCliente().getUsername().equals(cliente.get(0).getUsername())) {
			
			List<Valoracion> valoraciones = valoracionService.findByVisita(visita);
			if(valoraciones.isEmpty()) {
				val.setVisita(visita);
				modelMap.put("valoracion", val);
			}else {
				//Mostrar mensaje de error
				modelMap.addAttribute("error", "Ya ha realizado una valoración a esta vivienda.");
				vista = "users/visitas";
			}
		}
		
		return vista;
	}
	
	@PostMapping(value="/save")
	public String saveValoracion(@Valid Valoracion valoracion, ModelMap model) {
		Valoracion v = valoracionService.save(valoracion);
		model.addAttribute("success", "Valoracion guardada correctamente") ;
		
		return usuarioController.showListViviendas(model);

	}
	
	@GetMapping(value="/misValoraciones")
	public String getMisValoraciones(ModelMap model) {
		User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Propietario prop = propietarioService.findPropietarioByUsername(usuario.getUsername()).get(0);
		List<Valoracion> valoraciones = valoracionService.findAllByPropietario(prop);
		
		model.addAttribute("valoraciones", valoraciones);
		model.addAttribute("localDateTimeFormat", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		
		return "valoracion/misValoraciones";
	}
	
	//Alba-Alejandro
	
	
}