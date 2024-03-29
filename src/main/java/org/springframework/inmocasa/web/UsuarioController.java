package org.springframework.inmocasa.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.inmocasa.model.Administrador;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Usuario;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.service.AdministradorService;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.UsuarioService;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.inmocasa.util.PDFUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.BadElementException;


@Controller
public class UsuarioController {
	
	VisitaService visitaService;
	ClienteService clienteService;
    PropietarioService propietarioService;

	CompraService compraService;
	ViviendaService viviendaService;
    UsuarioService usuarioService;
    AdministradorService adminService;
	

	@Autowired
	public UsuarioController(VisitaService visitaService,ClienteService clienteService, PropietarioService clinicService,
			CompraService compraService, ViviendaService viviendaService, UsuarioService usuarioService, AdministradorService admin) {
		super();
		this.visitaService = visitaService;
		this.clienteService = clienteService;
		this.propietarioService = clinicService;
		this.compraService = compraService;
		this.viviendaService =viviendaService;
		this.usuarioService = usuarioService;
		this.adminService = admin;
	}

//	@Autowired
//	public UsuarioController(PropietarioService propietService) {
//		this.propietarioService = propietService;
//	}
	// Santi-Alvaro


	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/usuario/new")
	public String initCreationForm(Map<String, Object> model) {
		Propietario propietario = new Propietario();
		model.put("propietario", propietario);
		return "usuarios/createPropietarioForm";
	}

	@PostMapping(value = "/usuario/new")
	public String processCreationForm(@Valid Propietario propietario, BindingResult result) {
		if (result.hasErrors()) {
			return "usuarios/createPropietarioForm";
		} else {
			// creating propietario, usuario, and authority
			this.propietarioService.savePropietario(propietario);
			return "redirect:/";
		}
	}

	// Alvaro-MiguelEmmanuel
  

	@GetMapping(value = { "/usuario/misVisitas" })
	public String showListViviendas(ModelMap modelMap) {

		User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Cliente> clientes = clienteService.findClienteByUsername(usuario.getUsername());
		
		Collection<Visita> pVisitas = visitaService.findProximasVisitas(clientes.get(0), LocalDate.now());
		modelMap.put("proximasVisitas", pVisitas);
		
		Collection<Visita> vivs = visitaService.findOldVisitas(clientes.get(0), LocalDate.now());
		modelMap.put("visitas", vivs);
		modelMap.addAttribute("localDateTimeFormat", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		return "users/visitas";
	}

  
  @GetMapping(value = "/usuario/exportPDF",
          produces = MediaType.APPLICATION_PDF_VALUE)
  	public InputStreamResource descargaPDF(HttpServletRequest request, HttpServletResponse response) throws IOException, BadElementException {
	  User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  ByteArrayInputStream bis = null;//PDFUtils.usuarioPDFGenerator(datos);
	  System.out.println(usuario.getAuthorities());
	  if(usuario.getAuthorities().iterator().next().getAuthority().equals("cliente")) {
		  List<Cliente> clientes = clienteService.findClienteByUsername(usuario.getUsername());
		  Collection<Visita> visitas = visitaService.findAllByCliente(clientes.get(0));
		  List<Compra> compras = compraService.findAllFromCliente(clientes.get(0));
		  bis = PDFUtils.clientePDFGenerator(clientes.get(0), visitas, compras);
	  }else {
		  Propietario propiet = propietarioService.findByUsername(usuario.getUsername());
		  List<Vivienda> viviendas = viviendaService.findAllByPropietario(propiet);
		  bis = PDFUtils.propietarioPDFGenerator(propiet, viviendas);
	  }
	  

      HttpHeaders headers = new HttpHeaders();
      String filename = "export_usuario";
      headers.add("Content-Disposition", "attachment; filename="+filename+".pdf");
      response.setContentType("application/pdf");
      response.setHeader("Content-Disposition", "inline; filename="+filename+".pdf");
      
      System.out.println("Generado");
      IOUtils.copy(bis, response.getOutputStream());
      response.flushBuffer();
      return null;
  }
	
	//Alba-Alejandro
	
//  	@GetMapping(value = {"/miPerfil"})
//  	public String showMyProfile(ModelMap model) {
//  		
//  		String view = "users/profile";
//  		User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//  		Propietario prop = propietarioService.findByUsername(usuario.getUsername());
//  		if(prop != null) {
//  			model.addAttribute("propietario", prop);
//  		}
//  		
//  		model.put("user", usuario);
//  		return view;
//  		
//  	}
  	@GetMapping(value= {"/usuario/miPerfil"})
  	public ModelAndView showMyProfile() {
  		ModelAndView res = new ModelAndView("users/profile");
  		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Administrador admin = adminService.findAdministradorByUsername(user.getUsername());
		if(admin != null) {
			res.addObject("user", admin);
		}
		
		return res;
  	}
  	
  	@GetMapping("/usuario/delete/{usuarioId}")
  	public String borrarUsuarioCompleto(@PathVariable("usuarioId") int usuarioId, ModelMap model) {
  		Usuario usuario = usuarioService.findUsuarioById(usuarioId);
  		UserDetails userPrincipalDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userPrincipalDetails.getUsername();
		if(username.equals(usuario.getUsername())) {
			usuarioService.delete(usuario);
			return "redirect:/logout";
		}
	
		return "redirect:/";
  	}

}
