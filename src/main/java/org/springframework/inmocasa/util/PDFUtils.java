package org.springframework.inmocasa.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Usuario;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.model.Vivienda;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFUtils {

	
	public static ByteArrayInputStream usuarioPDFGenerator(Usuario user) {
		
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfPTable table = cabecera("Datos del usuario");
			Paragraph pUsuario = datosUsuario(user);
			
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			PdfWriter.getInstance(document, out);
			document.open();

			
			document.add(table);
			document.add(new Paragraph("\n"));
			document.add(pUsuario);

			document.close();
		} catch (DocumentException ex) {

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	private static PdfPTable cabecera(String tipo) throws DocumentException, MalformedURLException, IOException {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(80);

		PdfPCell hcell;
		

		Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		headFont.setSize(18);
		hcell = new PdfPCell(new Phrase(tipo, headFont));
		hcell.setBorderWidth(0f);
		hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		
		return table;
	}
	
	private static Paragraph datosUsuario(Usuario usuario) {
		Paragraph p = new Paragraph();
		
		p.add("\n");
		Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		headFont.setSize(18);
		p.add(new Phrase("Datos del usuario: ",headFont));
		p.add("\nNombre y apellidos: "+usuario.getNombre() + " "+usuario.getApellidos());
		p.add("\nDNI: "+usuario.getDni());
		p.add("\nFecha de nacimiento: "+usuario.getFechaNacimiento());
		p.add("\nEmail: "+usuario.getEmail());
		p.add("\nTeléfono: "+usuario.getTelefono());
		p.add("\n\n");
		
		return p;
	}

	public static ByteArrayInputStream clientePDFGenerator(Usuario datos, Collection<Visita> visitas,
			List<Compra> compras) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			//cabecera y datos del usuario
			PdfPTable table = cabecera("Datos del cliente");
			Paragraph pUsuario = datosUsuario(datos);
			
			
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			PdfWriter.getInstance(document, out);
			document.open();

			
			document.add(table);
			document.add(new Paragraph("\n"));
			document.add(pUsuario);
			
			//Meter las viviendas favoritas del cliente

			//Datos de las viviendas compradas
			if(!compras.isEmpty()) {
				List<Paragraph> pcompras = compras(compras);
				document.add(new Paragraph(new Phrase("Compras realizadas: ",headFont)));
				for (Paragraph paragraph : pcompras) {
					document.add(paragraph);
				}
			}
			if(!visitas.isEmpty()) {
				List<Paragraph> pvisitas = visitas(visitas);
				document.add(new Paragraph(new Phrase("Visitas realizadas: ",headFont)));
				for (Paragraph paragraph : pvisitas) {
					document.add(paragraph);
				}
			}
			
			document.close();
		} catch (DocumentException ex) {

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	private static List<Paragraph> compras(Collection<Compra> compras) {
		List<Paragraph> lista = new ArrayList<Paragraph>();
		for (Compra compra : compras) {
			Paragraph p = new Paragraph();
			p.add("Id: "+compra.getId());
			p.add("\nId vivienda: "+compra.getVivienda().getId());
			p.add("\nEstado: "+compra.getEstado());
			p.add("\nPrecio final: "+compra.getPrecioFinal());
			p.add("\nComentario: "+compra.getComentario());
			p.add("\n");
			lista.add(p);
		}
		return lista;
	}
	
	private static List<Paragraph> visitas(Collection<Visita> visitas) {
		List<Paragraph> lista = new ArrayList<Paragraph>();
		for (Visita visita : visitas) {
			Paragraph p = new Paragraph();
			p.add("\nId de la visita: "+visita.getId());
			p.add("\nLugar de la visita: "+visita.getLugar());
			p.add("\nVivienda id: "+visita.getVivienda().getId());
			p.add("\nFecha de la visita: "+visita.getFecha());
			p.add("\n");
			lista.add(p);
		}
		return lista;
	}

	public static ByteArrayInputStream propietarioPDFGenerator(Usuario datos, List<Vivienda> viviendas) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			//cabecera y datos del usuario
			PdfPTable table = cabecera("Datos del propietario");
			Paragraph pUsuario = datosUsuario(datos);
			
			//Datos del propietario
			Paragraph propi = new Paragraph();
			propi.add("\n");
			Propietario usup = (Propietario) datos;
			if(usup.getEsInmobiliaria()) {
				propi.add("\nEs inmobiliaria");
				propi.add("\nCif: "+usup.getCif());
				propi.add("\nNombre: "+usup.getInmobiliaria());
				propi.add("\n");
			}
			
			
			
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			PdfWriter.getInstance(document, out);
			document.open();

			
			document.add(table);
			document.add(new Paragraph("\n"));
			document.add(pUsuario);
			document.add(propi);
			
			//Datos de las viviendas del propietario
			if(!viviendas.isEmpty()) {
				List<Paragraph> pvivs = viviendas(viviendas);
				document.add(new Paragraph(new Phrase("Viviendas: ",headFont)));
				for (Paragraph paragraph : pvivs) {
					document.add(paragraph);
				}
			}

			document.close();
		} catch (DocumentException ex) {

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	private static List<Paragraph> viviendas(List<Vivienda> viviendas) {
		List<Paragraph> lista = new ArrayList<Paragraph>();
		for (Vivienda viv : viviendas) {
			Paragraph p = new Paragraph();
			p.add("\nId de la vivienda: "+viv.getId());
			p.add("\nTitulo: "+viv.getTitulo());
			p.add("\nCaracteristicas: "+viv.getCaracteristicas());
			p.add("\nDirección: "+viv.getDireccion());
			p.add("\nPrecio de la vivienda: " +viv.getPrecio()+"€");
			p.add("\n");
			lista.add(p);
		}
		return lista;
	}
}
