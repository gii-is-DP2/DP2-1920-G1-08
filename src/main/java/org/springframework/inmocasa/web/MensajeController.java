package org.springframework.inmocasa.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Mensaje;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.MensajeService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mensajes")
public class MensajeController {

	@Autowired
	private MensajeService mensajeService;

	@Autowired
	private PropietarioService propService;

	@Autowired
	private ClienteService clientService;

	// Santi-Alvaro

//	@ModelAttribute("allClients")
//    public Collection<Cliente> populateAllClients() {
//        return clientService.findAll();
//    }
//	
//	@ModelAttribute("allProps")
//    public List<Propietario> populateAllProps() {
//        return propService.findAll();
//    }

	@GetMapping(path = "/mensajes-enviados")
	public String listadoMensajesEnviados(ModelMap model) {
		String vista = "mensajes/misMensajes";
		List<Mensaje> mensajes = new ArrayList<>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName(); // username Actual
		Collection<Mensaje> mnsjs = mensajeService.findAll();
		for (Mensaje m : mnsjs) {
			if (m.getProp().getUsername().equals(username) && m.getProp().getId().equals(m.getEmisorId())) {
				mensajes.add(m);
			} else if (m.getClient().getUsername().equals(username) && m.getClient().getId().equals(m.getEmisorId())) {
				mensajes.add(m);
			}

		}

		model.addAttribute("mensajes", mensajes);
		return vista;
	}

	@GetMapping(path = "/mensajes-recibidos")
	public String listadoMensajesRecibidos(ModelMap model) {
		String vista = "mensajes/misMensajes";
		List<Mensaje> mensajes = new ArrayList<>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName(); // username Actual
		Collection<Mensaje> mnsjs = mensajeService.findAll();
		for (Mensaje m : mnsjs) {
			if (m.getProp().getUsername().equals(username) && m.getProp().getId().equals(m.getReceptorId())) {
				mensajes.add(m);
			} else if (m.getClient().getUsername().equals(username)
					&& m.getClient().getId().equals(m.getReceptorId())) {
				mensajes.add(m);
			}

		}

		model.addAttribute("mensajes", mensajes);
		return vista;
	}

	@GetMapping(path = "/new")
	public String crearMensaje(ModelMap modelMap) {
		String viewProp = "mensajes/editMensaje";
		String viewClient = "mensajes/editMensajeCliente";

		Mensaje mensaje = new Mensaje();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		List<Propietario> props = propService.findAll();
		List<Cliente> clients = clientService.findAll();
		String username = userPrincipal.getUsername();

		for (Propietario p : props) {
			if (p.getUsername().equals(username)) {
				mensaje.setEmisorId(p.getId());
				mensaje.setReceptorId(mensaje.getClient().getId());
				modelMap.addAttribute("mensaje", mensaje);
				return viewProp;
			}
		}
		for (Cliente c : clients) {
			if (c.getUsername().equals(username)) {
				mensaje.setEmisorId(c.getId());
				mensaje.setReceptorId(mensaje.getProp().getId());
				modelMap.addAttribute("mensaje", mensaje);
				return viewClient;
			}
		}
		return listadoMensajesEnviados(modelMap);

	}

	@PostMapping(value = { "/save" })
	public String guardarMensaje(@Valid Mensaje mensaje, BindingResult result, ModelMap modelMap) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		String username = userPrincipal.getUsername();

		List<Propietario> props = propService.findAll();
		List<Cliente> clients = clientService.findAll();
		for (Propietario p : props) {
			if (p.getUsername().equals(username)) {
				mensaje.setEmisorId(p.getId());
				mensaje.setReceptorId(mensaje.getClient().getId());
			}
		}
		for (Cliente c : clients) {
			if (c.getUsername().equals(username)) {
				mensaje.setEmisorId(c.getId());
				mensaje.setReceptorId(mensaje.getProp().getId());
			}
		}

		if (result.hasErrors()) {
			modelMap.addAttribute("mensaje", mensaje);
			return "mensajes/editMensaje";
		} else {
			mensajeService.save(mensaje);
			modelMap.addAttribute("message", "La mensaje ha sido registrada correctamente");
		}
		return listadoMensajesEnviados(modelMap);
	}
	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
