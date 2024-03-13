package cl.sisitemaventas.app.controllers;

import java.util.Date;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import cl.sisitemaventas.app.entity.Usuario;
import cl.sisitemaventas.app.entity.Venta;
import cl.sisitemaventas.app.repository.VentasRepositorio;
import cl.sisitemaventas.app.util.Util;
import jakarta.servlet.http.HttpSession;



@Controller
public class IndexControllers {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	VentasRepositorio ventasRepositorio;
	
	
	Locale español = new Locale("es", "CL");
	NumberFormat formatoImporte = NumberFormat.getCurrencyInstance(new Locale("es","CL"));
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String hoy = sdf.format(new Date());


	
	@GetMapping("/index")
	public String index(Model  model, HttpSession session ) {
		Usuario user = (Usuario) session.getAttribute("user");
		String nombreVendedor = ""; ;
		if(user != null) {
			nombreVendedor = user.getNombre() + " " + user.getApellido();
		}
		String montoD = ventaDiaria();
		String montoM = ventaMensual();
		String montoMesAnterior = ventaMesAnterior();
		List<?> masVendido = masVendidos();
		List<Venta> ultimaVentas = UltimasVentas();
		
		model.addAttribute("montoD", montoD);
		model.addAttribute("montoM", montoM);
		model.addAttribute("montoMesAnterior", montoMesAnterior);
		
		model.addAttribute("masVendido", masVendido);
		model.addAttribute("ventas", ultimaVentas);
		model.addAttribute("vendedor", nombreVendedor);
		
		return "principal/index";

	}
	
	
	public String ventaDiaria() {
		//List<?> result = jdbcTemplate.queryForList("CALL SP_VENTAS_DIARIAS ()");
		List<?> result = jdbcTemplate.queryForList("CALL SP_VENTAS_DIARIAS('" + hoy + " 00:00:00"+ "',"  + "'" + hoy + "23:59:59" + "')");
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
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");		
		String  mFechaInicio = formatter.format(Util.inicioMesActual());
		String mFechaFin = formatter.format(Util.finMesActual());	
		//List<?> result = jdbcTemplate.queryForList("CALL SP_VENTAS_MENSUAL ()");
		List<?> result = jdbcTemplate.queryForList("CALL SP_VENTAS_MENSUAL('" + mFechaInicio + " 00:00:00"+ "',"  + "'" + mFechaFin + "23:59:59" + "')");
		if(result.isEmpty()) {
			return "0";
		}else {
		String[] valor = result.get(0).toString().replace("{", "").replace("}", "").split("=");			
		Integer monto = Integer.parseInt(valor[1]);		
		String m = formatoImporte.format(monto);				
		return m;
		}		
		
	}
	public String ventaMesAnterior() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");		
		String  mFechaInicio = formatter.format(Util.inicioMes());
		String mFechaFin = formatter.format(Util.finMes());	
		//List<?> result = jdbcTemplate.queryForList("CALL SP_VENTAS_MENSUAL ()");
		List<?> result = jdbcTemplate.queryForList("CALL SP_VENTAS_MENSUAL('" + mFechaInicio + " 00:00:00"+ "',"  + "'" + mFechaFin + "23:59:59" + "')");
		if(result.isEmpty()) {
			return "0";
		}else {
		String[] valor = result.get(0).toString().replace("{", "").replace("}", "").split("=");			
		Integer monto = Integer.parseInt(valor[1]);		
		String m = formatoImporte.format(monto);				
		return m;
		}		
		
	}
	
	public List<?> masVendidos() {
		List<?> result = jdbcTemplate.queryForList("CALL SP_MAS_VENDIDO ()");	
		
		return result;		
	}
	
	public List<Venta> UltimasVentas() {		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String hoy = sdf.format(new Date());		
		List<Venta> result = ventasRepositorio.findFiltroFecha(hoy + " 00:00:00", hoy + " 23:59:59");
	
		return result;
	}
	
	

}
