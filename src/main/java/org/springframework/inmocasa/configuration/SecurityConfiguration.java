package org.springframework.inmocasa.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */

/**
 * 
 * @author japarejo
 * 
 */

@Configuration

@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Value("${paypal.client.app}")
    private String clientId;
	@Value("${paypal.client.secret}")
    private String clientSecret;
	@Value("${paypal.mode}")
    private String mode;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/resources/**","/webjars/**","/h2-console/**").permitAll()
				.antMatchers(HttpMethod.GET, "/","/oups").permitAll()
				.antMatchers("/viviendas/delete/{viviendaId}").hasAnyAuthority("admin, propietario")
				.antMatchers("/compras/create/{viviendaId}").hasAnyAuthority("cliente, admin")
				.antMatchers("/propietario/**").hasAnyAuthority("propietario","admin")
				.antMatchers("/cliente/**").hasAnyAuthority("cliente","admin")
			
				.antMatchers("/clientes/new").permitAll()
				.antMatchers("/clientes/miPerfil").permitAll()
				.antMatchers("/clientes/{clienteId}/edit").permitAll()
				.antMatchers("/clientes/{clienteId}/save").permitAll()
				.antMatchers("/clientes/save").permitAll()
				.antMatchers("/clientes/{viviendaId}/favoritos").hasAuthority("cliente")
				.antMatchers("/clientes/lista/favoritas").hasAuthority("cliente")
				.antMatchers("/dashboard").hasAnyAuthority("admin")
				.antMatchers("/pay/**").hasAnyAuthority("propietario, admin")
				.antMatchers("/resources/**", "/webjars/**", "/h2-console/**").permitAll()
				.antMatchers("/mensajes/**").hasAnyAuthority("cliente", "propietario")
				.antMatchers("/users/new").permitAll()
				.antMatchers("/admin/**").hasAnyAuthority("admin")
				.antMatchers("/viviendas/allNew").permitAll()
				.antMatchers("/viviendas/allNewFiltros").permitAll()
				.antMatchers("/viviendas/ofertadas").permitAll()
				.antMatchers("/viviendas/{viviendaId}").permitAll()
				.antMatchers("/viviendas/new").hasAuthority("propietario")
				.antMatchers("/viviendas/denunciadas").hasAuthority("admin")
				.antMatchers("/viviendas/allViviendasAdmin").hasAuthority("admin")
				.antMatchers("/viviendas/save").hasAnyAuthority("propietario")
				.antMatchers("/visita/**").hasAnyAuthority("cliente","admin")
				.antMatchers("/viviendas/mis-viviendas").hasAnyAuthority("propietario")
				.antMatchers("/viviendas/{viviendaId}/**").permitAll()
				.antMatchers("/usuarios/**").permitAll()	
				.antMatchers("/propietarios/**").permitAll()		
				.antMatchers("/compras/**").permitAll()
				.antMatchers("/owners/**").hasAnyAuthority("owner","admin")				
				.antMatchers("/clientes/**").hasAnyAuthority("cliente")
				.antMatchers("/owners/**").hasAnyAuthority("owner","admin")			
				.antMatchers("/denuncias/create/**").hasAnyAuthority("cliente")
				.antMatchers("/denuncias/save/**").hasAnyAuthority("cliente")
				.antMatchers("/usuario/miPerfil").permitAll()
				.antMatchers("/usuario/delete/{usuarioId}").hasAnyAuthority("cliente","propietario","admin")
				.antMatchers("/usuario/misVisitas").hasAuthority("cliente")
				.antMatchers("/usuario/exportPDF").hasAnyAuthority("cliente", "propietario")
				.antMatchers("/valoracion/misValoraciones").hasAuthority("propietario")
				.antMatchers("/valoracion/{visitaId}/new").hasAuthority("cliente")
				.antMatchers("/valoracion/save").hasAuthority("cliente")
				.antMatchers("/visita/valoracion/**").hasRole("cliente")
				.antMatchers("/compras/").permitAll()
				.antMatchers("/usuario/misVisitas").hasAuthority("cliente")
				.antMatchers("/valoracion/**").permitAll()
				.antMatchers("/visita/valoracion/**").hasRole("cliente")
				.anyRequest().denyAll()
				.and()
				.formLogin()
				/* .loginPage("/login") */
				.failureUrl("/login-error")
				.and()
				.logout()
				.logoutSuccessUrl("/");

		// Configuración para que funcione la consola de administración
		// de la BD H2 (deshabilitar las cabeceras de protección contra
		// ataques de tipo csrf y habilitar los framesets si su contenido
		// se sirve desde esta misma página.

		http.csrf().ignoringAntMatchers("/h2-console/**");
		http.headers().frameOptions().sameOrigin();

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication()

	      .dataSource(dataSource)
	      .usersByUsernameQuery(
	      "select username,password,true from usuario where username =?")
	    		  .authoritiesByUsernameQuery(
	       "select username, authority "
	        + "from authorities "
	        + "where username = ?")	      	      
	      .passwordEncoder(passwordEncoder());	

	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();

		return encoder;
	}
	
	@Bean
	public Map<String, String> paypalSdkConfig(){
		Map<String, String> sdkConfig = new HashMap<>();
		sdkConfig.put("mode", mode);
		return sdkConfig;
	}
	
	@Bean
	public OAuthTokenCredential authTokenCredential(){
		return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
	}
	
	@Bean
	public APIContext apiContext() throws PayPalRESTException{
		APIContext apiContext = new APIContext(authTokenCredential().getAccessToken());
		apiContext.setConfigurationMap(paypalSdkConfig());
		return apiContext;
	}

}