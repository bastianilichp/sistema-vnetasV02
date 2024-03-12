package cl.sisitemaventas.app.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.sisitemaventas.app.entity.Producto;
import cl.sisitemaventas.app.entity.ProductoParaVender;
import cl.sisitemaventas.app.repository.ProductosRepositorio;
import jakarta.servlet.http.HttpServletRequest;


@Controller

public class VenderControllers {

	@Autowired
	private ProductosRepositorio productosRepository;
//	@Autowired
//	private VentasRepositorio ventasRepository;
//	@Autowired
//	private ProductosVendidosRepositorio productosVendidosRepository;
//	
//	@Autowired
//	private ProductoServicio productoServicio;
	private Integer descuento;

	@GetMapping(value = "/vender")
	public String interfazVender(Model model, HttpServletRequest request) {
		model.addAttribute("producto", new Producto());
		Integer total = 0;
		Integer subTotal = 0;

		ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
		for (ProductoParaVender p : carrito) {
			total += p.getTotal();
			subTotal += p.getTotal();
		}
		if (descuento != null) {
			total = total - descuento;

		} else {
			descuento = 0;
		}

		model.addAttribute("descuento", descuento);
		model.addAttribute("total", total);
		model.addAttribute("subTotal", subTotal);

		return "principal/vender/vender";
	}
	
	  @PostMapping(value = "/agregar")
	    public String agregarAlCarrito(@ModelAttribute Producto producto, HttpServletRequest request, RedirectAttributes redirectAttrs) {
	        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
	        Producto productoBuscadoPorCodigo = productosRepository.findFirstByCodigo(producto.getCodigo());
	        if (productoBuscadoPorCodigo == null) {
	            redirectAttrs
	                    .addFlashAttribute("mensaje", "El producto con el código " + producto.getCodigo() + " no existe")
	                    .addFlashAttribute("clase", "warning");
	            return "redirect:/vender/";
	        }
	        if (productoBuscadoPorCodigo.sinStock()) {
	            redirectAttrs
	                    .addFlashAttribute("mensaje", "El producto está agotado")
	                    .addFlashAttribute("clase", "warning");
	            return "redirect:/vender/";
	        }
	        boolean encontrado = false;
	        for (ProductoParaVender productoParaVenderActual : carrito) {
	            if (productoParaVenderActual.getCodigo().equals(productoBuscadoPorCodigo.getCodigo())) {
	                productoParaVenderActual.aumentarCantidad();
	                encontrado = true;
	                break;
	            }
	        }
	        if (!encontrado) {
	        	carrito.add(new ProductoParaVender(productoBuscadoPorCodigo.getId(), productoBuscadoPorCodigo.getNombre(), productoBuscadoPorCodigo.getCodigo(), productoBuscadoPorCodigo.getPrecioCompra(), productoBuscadoPorCodigo.getPrecioVenta(), productoBuscadoPorCodigo.getStock(),productoBuscadoPorCodigo.getCategoria_id(),  1));
	        	
	        }
	        this.guardarCarrito(carrito, request);
	        return "redirect:/vender";
	    }
	  
	  private ArrayList<ProductoParaVender> obtenerCarrito(HttpServletRequest request) {
	        ArrayList<ProductoParaVender> carrito = (ArrayList<ProductoParaVender>) request.getSession().getAttribute("carrito");
	        if (carrito == null) {
	            carrito = new ArrayList<>();
	        }
	        return carrito;
	    }

	    private void guardarCarrito(ArrayList<ProductoParaVender> carrito, HttpServletRequest request) {
	        request.getSession().setAttribute("carrito", carrito);
	    }

}
