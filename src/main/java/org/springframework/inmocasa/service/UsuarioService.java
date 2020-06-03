package org.springframework.inmocasa.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Denuncia;
import org.springframework.inmocasa.model.Habitacion;
import org.springframework.inmocasa.model.Mensaje;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Usuario;
import org.springframework.inmocasa.model.Valoracion;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.repository.ClienteRepository;
import org.springframework.inmocasa.repository.CompraRepository;
import org.springframework.inmocasa.repository.DenunciaRepository;
import org.springframework.inmocasa.repository.HabitacionRepository;
import org.springframework.inmocasa.repository.MensajeRepository;
import org.springframework.inmocasa.repository.PropietarioRepository;
import org.springframework.inmocasa.repository.UsuarioRepository;
import org.springframework.inmocasa.repository.ValoracionRepository;
import org.springframework.inmocasa.repository.VisitaRepository;
import org.springframework.inmocasa.repository.ViviendaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	// Santi-Alvaro

	private UsuarioRepository userRepository;
	private PropietarioRepository propRepository;
	private ClienteRepository clientRepository;
	
	private ViviendaRepository viviendaRepository;
	private HabitacionRepository habitacionRepository;
	
	private VisitaRepository visitaRepository;
	private ValoracionRepository valoracionRepository;
	private CompraRepository compraRepository;
	private DenunciaRepository denunciaRepository;
	private MensajeRepository mensajeRepository;

	@Autowired
	public UsuarioService(UsuarioRepository userRepository, PropietarioRepository propRepository, ClienteRepository clientRepository,
							ViviendaRepository viviendaRepository, HabitacionRepository habitacionRepository,
							VisitaRepository visitaRepository, ValoracionRepository valoracionRepository, CompraRepository compraRepository,
							MensajeRepository mensajeRepository, DenunciaRepository denunciaRepository) {
		
		this.userRepository = userRepository;
		this.propRepository = propRepository;
		this.clientRepository = clientRepository;
		
		this.viviendaRepository = viviendaRepository;
		this.habitacionRepository = habitacionRepository;
		
		this.visitaRepository = visitaRepository;
		this.valoracionRepository = valoracionRepository;
		this.compraRepository = compraRepository;
		this.denunciaRepository = denunciaRepository;
		this.mensajeRepository = mensajeRepository;
		
	}

	@Transactional
	public void saveUsuario(Usuario usuario) throws DataAccessException {

		userRepository.save(usuario);
	}

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro
	
	public Usuario findUsuarioByUsername(String username) {
		return userRepository.findUsuarioByUsername(username);
	}
	
	public Usuario findUsuarioById(Integer userId) {
		return userRepository.findUsuarioById(userId);
	}
	
	//Delete completo del Usuario
	@Transactional
	public void delete(Usuario usuario) {
		UserDetails userPrincipalDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userPrincipalDetails.getUsername();
//		Usuario userPrincipal = userRepository.findUsuarioByUsername(username);
//		if(userPrincipal == usuario) {
			Propietario propietario = propRepository.findByUsername(username);
			List<Cliente> cliente = clientRepository.findByUsername(username);
			if(propietario != null) {
				Integer propId = propietario.getId();
				//Si es propietario obtenemos sus viviendas y:
				Collection<Vivienda> viviendas = viviendaRepository.findViviendasByPropietario(propId);
				//Borramos Habitaciones
				for(Vivienda v : viviendas) {
					Collection<Habitacion> habitaciones = viviendaRepository.findHabitacionesByVivienda(v.getId());
					Collection<Compra> compras = compraRepository.findComprasByVivienda(v.getId());
					Collection<Visita> visitas = visitaRepository.findVisitasByVivienda(v.getId());
					Collection<Denuncia> denuncias = denunciaRepository.findDenunciasByViviendaId(v.getId());
					if(habitaciones != null) {
						habitacionRepository.deleteAll(habitaciones);
					} 
				
					//Borramos Denuncias
					if(denuncias != null) {
						denunciaRepository.deleteAll(denuncias);
					}
				
					//Borramos Compras
					if(compras != null) {
						compraRepository.deleteAll(compras);
					}				
					//Borramos Valoraciones
					for(Visita vis : visitas) {
						Collection<Valoracion> valoraciones = visitaRepository.findValoracionesByVisita(vis.getId());
						if(valoraciones != null) {
							valoracionRepository.deleteAll(valoraciones);
						}
					}
					//Borramos Visitas
					if(visitas != null) {
						visitaRepository.deleteAll(visitas);
					}
					
					//Borramos la vivienda en los favoritos de los clientes
					Collection<Cliente> clientes = clientRepository.findAll();
					for(Cliente c : clientes) {
						if(c.getFavoritas().contains(v)) {
							c.getFavoritas().remove(v);
						}
					}
				}
				
				//Borramos Viviendas
				viviendaRepository.deleteAll(viviendas);
				
				Collection<Mensaje> mensajesProp = mensajeRepository.findMensajesByUserId(propietario.getId());
				if(mensajesProp != null) {
					mensajeRepository.deleteAll(mensajesProp);
				}
				
			} else if(cliente != null) {
				//Si es cliente:
				Integer clientId = cliente.get(0).getId();
				Collection<Visita> visitas = visitaRepository.findVisitasByCliente(clientId);
				//Borramos sus Valoraciones
				for(Visita v : visitas) {
					Collection<Valoracion> valoraciones = visitaRepository.findValoracionesByVisita(v.getId());
					if(valoraciones != null) {
						valoracionRepository.deleteAll(valoraciones);
					}
				}
				//Borramos sus Visitas
				if(visitas != null) {
					visitaRepository.deleteAll(visitas);
				}
				//Borramos sus Compras
				Collection<Compra> compras = compraRepository.findComprasByCliente(clientId);
				if(compras != null) {
					compraRepository.deleteAll(compras);
				}
				
				Collection<Mensaje> mensajesCliente = mensajeRepository.findMensajesByUserId(cliente.get(0).getId());
				if(mensajesCliente != null) {
					mensajeRepository.deleteAll(mensajesCliente);
				}
			}
					
			
			//Borramos el usuario completo
			userRepository.delete(usuario);
			
			
		//}
	}

}
