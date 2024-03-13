package cl.sisitemaventas.app.entity;

import java.util.Set;

import cl.sisitemaventas.app.util.Util;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Venta {
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer id;
	    private String fechaYHora;
	    private Integer descuento;

	    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
	    private Set<ProductoVendido> productos;

	    public Venta(Integer descuento) {
	        this.fechaYHora = Util.obtenerFechaYHoraActual();
	        this.descuento = descuento;
	    }
	    public Venta() {
	    	
	    }

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public Integer getTotal() {
	    	Integer total = 0;
	        for (ProductoVendido productoVendido : this.productos) {
	            total += productoVendido.getTotal();
	        }
	        return total;
	    }

	    public String getFechaYHora() {
	        return fechaYHora;
	    }

	    public void setFechaYHora(String fechaYHora) {
	        this.fechaYHora = fechaYHora;
	    }

	    public Set<ProductoVendido> getProductos() {
	        return productos;
	    }

	    public void setProductos(Set<ProductoVendido> productos) {
	        this.productos = productos;
	    }

		public Integer getDescuento() {
			return descuento;
		}

		public void setDescuento(Integer descuento) {
			this.descuento = descuento;
		}
}