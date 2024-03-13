package cl.sisitemaventas.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cl.sisitemaventas.app.entity.Venta;

public interface VentasRepositorio extends CrudRepository<Venta, Integer> {

	 @Query("FROM Venta Where fechaYHora BETWEEN :fechaDesde and :fechaHasta ORDER BY fechaYHora DESC LIMIT 2")
		List<Venta> findFiltroFecha(@Param("fechaDesde") String fechaDesde, @Param("fechaHasta")String fechaHasta);
	


}
