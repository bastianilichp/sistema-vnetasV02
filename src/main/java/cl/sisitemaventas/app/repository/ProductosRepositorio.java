package cl.sisitemaventas.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cl.sisitemaventas.app.entity.Producto;


public interface ProductosRepositorio extends JpaRepository<Producto, Integer> {
	
	Producto findFirstByCodigo(String codigoProducto);
    
    @Query( value = "SELECT * FROM producto where stock <= ?1 order by stock desc", 
    		  nativeQuery = true)
    public List<Producto> findStock(Integer stockMenor);
    
    @Query( value = "SELECT * FROM producto where stock <= 10 ", 
  		  nativeQuery = true)
    public List<Producto> findStock();
    
    
        
    
}