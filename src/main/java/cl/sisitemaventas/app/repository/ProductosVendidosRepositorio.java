package cl.sisitemaventas.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.sisitemaventas.app.entity.ProductoVendido;

public interface ProductosVendidosRepositorio extends JpaRepository<ProductoVendido, Integer> {

}
