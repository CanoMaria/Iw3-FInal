package iua.edu.ar.business;

import java.util.List;

import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.business.exception.NotFoundException;
import iua.edu.ar.model.Alerta;
import iua.edu.ar.model.Orden;

public interface IAlertaBusiness {
	public Alerta loadLast(Long ordenId) throws NotFoundException, BusinessException;

	public List<Alerta> list(Long ordenId) throws NotFoundException, BusinessException;
	
	public void aceptarAlerta(Long ordenId, Alerta alerta) throws NotFoundException, BusinessException;

	public Alerta add(Alerta alerta) throws BusinessException;
}