package org.springframework.inmocasa.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.inmocasa.util.PDFUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;


@RequestMapping(value="usuario")
@Controller
public class UsuarioController {
	
	VisitaService visitaService;
	ClienteService clienteService;
    PropietarioService propietarioService;
	CompraService compraService;
	ViviendaService viviendaService;

	@Autowired
	public UsuarioController(VisitaService visitaService,ClienteService clienteService, PropietarioService clinicService,
			CompraService compraService, ViviendaService viviendaService) {
		super();
		this.visitaService = visitaService;
		this.clienteService = clienteService;
		this.propietarioService = clinicService;
		this.compraService = compraService;
		this.viviendaService =viviendaService;

	}

	// Santi-Alvaro




	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/usuarios/new")
	public String initCreationForm(Map<String, Object> model) {
		Propietario propietario = new Propietario();
		model.put("propietario", propietario);
		return "usuarios/createPropietarioForm";
	}

	@PostMapping(value = "/usuarios/new")
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
  
  @GetMapping(value = { "/misVisitas" })
	public String showListViviendas(ModelMap modelMap) {

		User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Cliente> clientes = clienteService.findClienteByUsername(usuario.getUsername());
		
		Collection<Visita> pVisitas = visitaService.findProximasVisitas(clientes.get(0), LocalDateTime.now());
		modelMap.put("proximasVisitas", pVisitas);
		
		Collection<Visita> vivs = visitaService.findOldVisitas(clientes.get(0), LocalDateTime.now());
		modelMap.put("visitas", vivs);

		return "users/visitas";
	}
  
  @RequestMapping(value = "/exportPDF", method = RequestMethod.GET,
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
	

}
