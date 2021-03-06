package iua.edu.ar.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.business.exception.NotFoundException;
import iua.edu.ar.business.exception.PasswordException;
import iua.edu.ar.model.DatoCarga;
import iua.edu.ar.model.Orden;
import iua.edu.ar.model.OrdenDetalle;
import iua.edu.ar.model.PromedioDatoCarga;
import iua.edu.ar.model.UltimoDatoCarga;
import iua.edu.ar.model.dto.ConciliacionDTO;
import iua.edu.ar.model.persistence.OrdenDetalleRepository;
import iua.edu.ar.model.persistence.OrdenRepository;

@Service
public class OrdenBusiness implements IOrdenBusiness {

	@Autowired
	private OrdenRepository ordenDAO;
	@Autowired
	private OrdenDetalleRepository ordenDetalleDAO;

	@Autowired
	OrdenDetalleBusiness ordenDetalleBusiness;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public Orden load(Long id) throws NotFoundException, BusinessException {
		Optional<Orden> op;
		try {
			op = ordenDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("El Orden con el id " + id + " no se encuentra en la BD");
		}
		return op.get();
	}

	@Override
	public List<Orden> list() throws BusinessException {
		try {
			return ordenDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Orden add(Orden orden) throws BusinessException {
		System.out.println(orden.toString());
		try {
			if(orden.getEstado() == 0) {
				int [] cisternadoArray=orden.getCamion().getCisternado();
				int preset=0;
						
				for(int i=0;i<cisternadoArray.length;i++){
					preset+=cisternadoArray[i];
				}
				orden.setPreset(preset);
				
				if (orden.checkBasicData()) {
					orden.setEstado(1);
				}else {
					throw new BusinessException("Los datos ingresados no son correctos");
				}
			}
		return ordenDAO.save(orden);			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(Long id) throws NotFoundException, BusinessException {
		try {
			ordenDAO.deleteById(id);
		} catch (EmptyResultDataAccessException el) {
			throw new NotFoundException("No se encuentra la orden con id = " + id + ".");
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public Orden update(Orden orden) throws NotFoundException, BusinessException {
		Orden ordenDB = load(orden.getId());

		//ordenDAO.save(ordenDB);

		// ordenDB.setUltimosDatosCarga(ultimosDatosCarga);

		/*orden.partialUpdate(ordenDB);

		if (orden.checkBasicData())
			orden.setEstado(1);*/

		return ordenDAO.save(orden);

	}

	@Override
	public void checkPassword(Orden orden) throws NotFoundException, BusinessException, PasswordException {
		Orden ordenDB = load(orden.getId());
		
		if (orden.checkPassword(ordenDB.getPassword()) && ordenDB.getEstado() == 2) {
			return;
		}

		throw new PasswordException("La contrasenia no es correcta");
	}

	@Override
	/**
	 * Funcion para cargar los datos del detalle de una orden
	 * 
	 * @param datosCarga    Todos los datos recibidos por el sistema externo
	 * @param idOrden	    Id de la orden
	 */
	public void cargaDatos(DatoCarga datosCarga, Long idOrden) throws NotFoundException, BusinessException {
		Orden ordenDB;
		try {
			ordenDB = load(idOrden);
			if (ordenDB.getEstado() != 2)
				throw new BusinessException("Estado incorrecto");
			
			Date date2 = new Date();
			UltimoDatoCarga ultimosDatosCarga = new UltimoDatoCarga(
					datosCarga.getMasaAcumulada(),
					datosCarga.getDensidadProducto(), 
					datosCarga.getTemperaturaProducto(), 
					datosCarga.getCaudal()
					);
			ultimosDatosCarga.setFecha(date2);
			
			
			if(ordenDB.getUltimosDatosCarga() != null) {
				Double masaAcumuladaRegistrada=ordenDB.getUltimosDatosCarga().getMasaAcumulada(); 
				
				if(masaAcumuladaRegistrada>ultimosDatosCarga.getMasaAcumulada()){
					throw new BusinessException("La masa acumulada no puede ser menor");
				}
				if(ultimosDatosCarga.getMasaAcumulada()>ordenDB.getPreset()) {
					throw new BusinessException("La masa acumulada no puede ser mayor al preset");
				}
			}
			
			
			ordenDB.setUltimosDatosCarga(ultimosDatosCarga);
			add(ordenDB);
			
			List<OrdenDetalle> test = ordenDetalleDAO.findByOrdenId(idOrden);
			
			OrdenDetalle ordenDetalle = new OrdenDetalle();
			ordenDetalle.setCaudal(datosCarga.getCaudal());
			ordenDetalle.setDensidadProducto(datosCarga.getDensidadProducto());
			ordenDetalle.setMasaAcumulada(datosCarga.getMasaAcumulada());
			ordenDetalle.setOrden(ordenDB);
			ordenDetalle.setTemperaturaProducto(datosCarga.getTemperaturaProducto());
			ordenDetalle.setFecha(date2);
			
			if (test.isEmpty()) {
				ordenDetalleDAO.save(ordenDetalle);
				return;
			}
			
			OrdenDetalle test2 = ordenDetalleDAO.findFirstByOrdenIdOrderByFecha(idOrden);
			Date date1 = test2.getFecha();
			
			
			System.out.println(date1+"-------"+date2);
			long segundos = getDateDiff(date1, date2, TimeUnit.SECONDS);
			if (segundos >= ordenDB.getFecuencia()) {
				ordenDetalleDAO.save(ordenDetalle);
			}


		} catch (NotFoundException el) {
			throw new NotFoundException("No se encuentra la orden con id = " + idOrden + ".");
		}catch (Exception e) {
			throw new BusinessException(e);
		}

		return;

	}

	/**
	 * Get a diff between two dates
	 * 
	 * @param date1    the oldest date
	 * @param date2    the newest date
	 * @param timeUnit the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	public void addPesajeInicial(Orden orden) throws NotFoundException, BusinessException {
		try {
			Orden ordenDB = load(orden.getId());
			if (ordenDB.getEstado() == 1) {

				ordenDB.setPesoInicial(orden.getPesoInicial());
				ordenDB.setFechaRecepcionPesajeInicial(new Date());
				ordenDB.setEstado(2);
				String pass = "";
				for (int i = 0; i < 5; i++) {
					pass = (int) (Math.random() * 9) + 1 + pass;
				}
				ordenDB.setPassword(pass);
				ordenDAO.save(ordenDB);
			} else {
				throw new BusinessException("El estado de la orden es distinto a 1");
			}
		} catch (NotFoundException el) {
			throw new NotFoundException("No se encuentra la orden.");
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public void addPesajeFinal(Orden orden) throws NotFoundException, BusinessException {
		try {
			Orden ordenDB = load(orden.getId());
			if (ordenDB.getEstado() == 3) {

				ordenDB.setPesoFinal(orden.getPesoFinal());
				ordenDB.setEstado(4);
				ordenDB.setFechaRecepcionPesajeFinal(new Date());
				ordenDAO.save(ordenDB);

			} else {
				throw new BusinessException("El estado de la orden es distinto a 1");
			}
		} catch (EmptyResultDataAccessException el) {
			throw new NotFoundException("No se encuentra la orden con id = " + orden.getId() + ".");
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Orden cierreOrden(Long id) throws BusinessException, NotFoundException {
		try {
			Orden ordenDB = load(id);

			if (ordenDB.getEstado() != 2) 
				throw new BusinessException("El estado no se encuentra en estado 2, no se cambiara el valor");
						
			ordenDB.setFechaFinCarga(new Date());
			
			PromedioDatoCarga promedios = ordenDAO.findPromedios(id);
			
			promedios.setMasaAcumulada(ordenDB.getUltimosDatosCarga().getMasaAcumulada());
			promedios.setFecha(new Date());
			
			ordenDB.setPromedioDatosCarga(promedios);
			
			ordenDB.setEstado(3);
			return ordenDAO.save(ordenDB);

		} catch (NotFoundException el) {
			throw new NotFoundException("No se encuentra la orden.");
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}
	
	@Override
    public ConciliacionDTO conciliacion(Long id) throws NotFoundException, BusinessException {
        Orden ordenDb = load(id);

        try {
            if (ordenDb.getEstado() != 4)
                throw new BusinessException("El estado de la orden no corresponde para conciliacion");

            Double pInicial = ordenDb.getPesoInicial();
            Double pFinal = ordenDb.getPesoFinal();
            PromedioDatoCarga promDatoCarga = ordenDb.getPromedioDatosCarga();
            ConciliacionDTO conciliacion = new ConciliacionDTO(pInicial, pFinal, promDatoCarga, ordenDb.getUltimosDatosCarga());
            
            return conciliacion;
        } catch (Exception e) {
            throw new BusinessException(e);
        }

    }

}
