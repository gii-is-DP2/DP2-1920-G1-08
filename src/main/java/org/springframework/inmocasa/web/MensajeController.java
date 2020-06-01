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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	@InitBinder
	public void initCauseBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("allClients")
	public Collection<Cliente> populateAllClients() {
		return clientService.findAll();
	}

	@ModelAttribute("allProps")
	public Collection<Propietario> populateAllProps() {
		return propService.findAll();
	}

	@GetMapping(path = "/mensajes-enviados")
	public String listadoMensajesEnviados(ModelMap model) {
		String vista = "mensajes/misMensajes";
		List<Mensaje> mensaje = new ArrayList<>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName(); // username Actual
		Collection<Mensaje> mnsjs = mensajeService.findAll();
		for (Mensaje m : mnsjs) {
			if (m.getProp().getUsername().equals(username) && m.getProp().getId().equals(m.getEmisorId())) {
				mensaje.add(m);
			} else if (m.getClient().getUsername().equals(username) && m.getClient().getId().equals(m.getEmisorId())) {
				mensaje.add(m);
			}

		}

		model.addAttribute("mensaje", mensaje);
		return vista;
	}

	@GetMapping(path = "/mensajes-recibidos")
	public String listadoMensajesRecibidos(ModelMap model) {
		String vista = "mensajes/misMensajes";
		List<Mensaje> mensaje = new ArrayList<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		String username = userPrincipal.getUsername(); // username Actual
		Collection<Mensaje> mnsjs = mensajeService.findAll();
		for (Mensaje m : mnsjs) {
			if (m.getProp().getUsername().equals(username) && m.getProp().getId().equals(m.getReceptorId())) {
				mensaje.add(m);
			} else if (m.getClient().getUsername().equals(username)
					&& m.getClient().getId().equals(m.getReceptorId())) {
				mensaje.add(m);
			}

		}

		model.addAttribute("mensaje", mensaje);
		return vista;
	}

	@GetMapping(path = "/new")
	public String crearMensaje(ModelMap modelMap) {
		String view = "mensajes/editMensaje";

		Mensaje mensaje = new Mensaje();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		List<Cliente> lista = clientService.findClienteByUsername(userPrincipal.getUsername());
		if (!lista.isEmpty()) {
			Cliente c = lista.get(0);
			mensaje.setEmisorId(c.getId());
			mensaje.setClient(c);
			modelMap.addAttribute("mensaje", mensaje);
		} else {
			Propietario p = propService.findByUsername(userPrincipal.getUsername());
			mensaje.setEmisorId(p.getId());
			mensaje.setProp(p);
			modelMap.addAttribute("mensaje", mensaje);
		}
		return view;
	}

	@PostMapping(path = { "/save" })
	public String guardarMensaje(@Valid Mensaje mensaje, BindingResult result,
			ModelMap modelMap) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		String name = userPrincipal.getUsername();
		Collection<Propietario> props = propService.findAll();
		Collection<Cliente> clients = clientService.findAll();
		for (Propietario p : props) {
			if (p.getUsername().equals(name)) {
				mensaje.setProp(p);
				mensaje.setEmisorId(p.getId());
				mensaje.setReceptorId(mensaje.getClient().getId());
			}
		}
		for (Cliente c : clients) {
			if (c.getUsername().equals(name)) {
				mensaje.setClient(c);
				mensaje.setEmisorId(c.getId());
				mensaje.setReceptorId(mensaje.getProp().getId());
			}
		}

		if (result.hasErrors()) {
//			modelMap.addAttribute("mensaje", mensaje);
			return "mensajes/editMensaje";
		} else {
			mensajeService.save(mensaje);
			modelMap.addAttribute("message", "El mensaje ha sido enviado correctamente");
		}
		return listadoMensajesEnviados(modelMap);
	}
	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
