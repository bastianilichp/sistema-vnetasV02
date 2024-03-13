package cl.sisitemaventas.app.entity;



public class ProductoParaVender	 extends Producto {
    private Integer cantidad;
    
    private Integer totalModificado;
    
    private Integer total;
    

	public ProductoParaVender(Integer id, String nombre, String codigo, Integer precioCompra, Integer precioVenta,
			Integer stock, Integer cantidad, Integer categoria_id) {
		super(id, nombre, codigo, precioCompra, precioVenta, stock, categoria_id);
		 this.cantidad = cantidad;
	}

	public ProductoParaVender(String nombre, String codigo, Integer precioCompra, Integer precioVenta, Integer stock, Integer cantidad,Integer categoria_id) {
		super(nombre, codigo, precioCompra, precioVenta, stock, categoria_id);
		 this.cantidad = cantidad;
	}

	public void aumentarCantidad() {
        this.cantidad++;
    }

    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

    public Integer getTotal() {
        return this.getPrecioVenta() * this.cantidad;
    }

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotalModificado() {
		return totalModificado;
	}

	public void setTotalModificado(Integer totalModificado) {
		this.totalModificado = totalModificado;
	}

	

    
    
    
}