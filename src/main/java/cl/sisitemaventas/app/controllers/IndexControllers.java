package cl.sisitemaventas.app.controllers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Spliterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControllers {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	Locale español = new Locale("es", "CL");
	NumberFormat formatoImporte = NumberFormat.getCurrencyInstance(new Locale("es","CL"));

	
	@GetMapping("/index")
	public String index(Model  model) {
		String montoD = ventaDiaria();
		String montoM = ventaMensual();
		
		model.addAttribute("montoD", montoD);
		model.addAttribute("montoM", montoM);
		
		return "principal/index";

	}
	
	
	public String ventaDiaria() {
		List<?> result = jdbcTemplate.queryForList("CALL SP_VENTAS_DIARIAS ()");
		if(result.isEmpty()) {
			return "0";
		}else {
		String[] valor = result.get(0).toString().replace("{", "").replace("}", "").split("=");			
		Integer monto = Integer.parseInt(valor[1]);		
		String m = formatoImporte.format(monto);				
		return m;
		}		
		
	}
	
	public String ventaMensual() {
		List<?> result = jdbcTemplate.queryForList("CALL SP_VENTAS_MENSUAL ()");
		if(result.isEmpty()) {
			return "0";
		}else {
		String[] valor = result.get(0).toString().replace("{", "").replace("}", "").split("=");			
		Integer monto = Integer.parseInt(valor[1]);		
		String m = formatoImporte.format(monto);				
		return m;
		}		
		
	}
	
	

}
