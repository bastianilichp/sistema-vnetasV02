package cl.sisitemaventas.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nombre;
	private String codigo;
	private Integer precioCompra;
	private Integer precioVenta;
	private Integer stock;
	private Integer categoria_id;

	public Producto(Integer id, String nombre, String codigo, Integer precioCompra, Integer precioVenta,
			Integer stock, Integer categoria_id) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.stock = stock;
		this.categoria_id = categoria_id;
	}

	public Producto(String nombre, String codigo, Integer precioCompra, Integer precioVenta, Integer stock, Integer categoria_id) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.stock = stock;
		this.categoria_id=categoria_id;
	}

	public Producto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Integer precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Integer getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Integer precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
    public boolean sinStock() {
        return this.stock <= 0;
    }
    
    public void restarStock(Integer stock) {
        this.stock -= stock;
    }

	public Integer getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(Integer categoria_id) {
		this.categoria_id = categoria_id;
	}
    
    
}