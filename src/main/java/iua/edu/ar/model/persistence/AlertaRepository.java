package iua.edu.ar.model.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import iua.edu.ar.model.Alerta;
import iua.edu.ar.model.OrdenDetalle;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {

	List<Alerta>  findAllAlertaByOrdenId(Long id);
	Alerta findFirstByOrdenIdOrderByIdDesc(Long id);
	
}
