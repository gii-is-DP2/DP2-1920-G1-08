package org.springframework.inmocasa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.inmocasa.model.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{

	//Santi-Alvaro
	
	
		//Alvaro-MiguelEmmanuel
		
		
		//Alba-Alejandro
		@Query("select u from Usuario u where u.username = ?1")
		public Usuario findUsuarioByUsername(String username);
		
		@Query("select u from Usuario u where u.id = ?1")
		public Usuario findUsuarioById(Integer userId);
		

}
