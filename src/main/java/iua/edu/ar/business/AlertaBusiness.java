package iua.edu.ar.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.business.exception.NotFoundException;
import iua.edu.ar.model.Alerta;
import iua.edu.ar.model.Orden;
import iua.edu.ar.model.Alerta;
import iua.edu.ar.model.persistence.AlertaRepository;
import iua.edu.ar.model.EstadoAlerta;

@Service
public class AlertaBusiness implements IAlertaBusiness{
	@Autowired
	OrdenBusiness ordenBusiness;
	@Autowired
	private AlertaRepository alertaDAO;
	
	@Override
	public Alerta add(Alerta alerta) throws BusinessException {
		try {
			return alertaDAO.save(alerta);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	
	@Override
	public Alerta loadLast(Long ordenId) throws NotFoundException, BusinessException {
		return alertaDAO.findFirstByOrdenIdOrderByIdDesc(ordenId);
	}

	@Override
	public List<Alerta> list(Long ordenId) throws BusinessException, NotFoundException {
		/*Orden ordenDB=ordenBusiness.load(ordenId);
		List<Alerta> alertList=ordenDB.getAlertList();
		return alertList;*/
		return alertaDAO.findAllAlertaByOrdenId(ordenId);
	}

	@Override
	public void aceptarAlerta(Long ordenId, Alerta alerta) throws NotFoundException, BusinessException {
		Orden ordenDB=ordenBusiness.load(ordenId);
		Alerta lastAlert=loadLast(ordenId);
		Date actualDate = new Date();
		lastAlert.setEstado(EstadoAlerta.ACEPTADO);
		lastAlert.setFechaDeAceptacion(actualDate);
		lastAlert.setOrden(ordenDB);
		lastAlert.setUsrAceptador(alerta.getUsrAceptador());
		lastAlert.setObservaciones(alerta.getObservaciones());
		
		alertaDAO.save(lastAlert);
	}

}
