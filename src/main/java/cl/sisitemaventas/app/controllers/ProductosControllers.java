package cl.sisitemaventas.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cl.sisitemaventas.app.repository.ProductosRepositorio;

@Controller
public class ProductosControllers {
	
	
	@Autowired
	private ProductosRepositorio productosRepositorio;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	@GetMapping("/verProductos")
	public String verProductos(Model model) {
		List<?> productos = buscarProductos();
		model.addAttribute("productos", productos);

		return "principal/productos/verProductos";
	}

	@GetMapping("/agregarProductos")
	public String agregarProductos() {

		return "principal/productos/agregarProductos";

	}

	public List<?> buscarProductos() {
		List<?> result = jdbcTemplate.queryForList("CALL SP_FIND_PRODUCTOS()");
		return result;
		
	}
	
	
}
