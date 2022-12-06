package iua.edu.ar.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import iua.edu.ar.business.IAlertaBusiness;
import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.business.exception.NotFoundException;
import iua.edu.ar.model.Alerta;

@RestController
@RequestMapping(value = Constantes.URL_ALERTA)
@Api(value = "Alertas", description = "Operaciones relacionadas con las alertas", tags = { "Alertas" })
public class AlertaRestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IAlertaBusiness alertaBusiness;
	
	@ApiOperation(value="Obtener la ultima alerta mediante el ID de la orden")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alerta> load(@ApiParam(value = "El ID de la orden ")@PathVariable("id") Long id) {

		try {
			return new ResponseEntity<Alerta>(alertaBusiness.loadLast(id), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Alerta>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Alerta>(HttpStatus.NOT_FOUND);
		}
	}
	@ApiOperation(value="Obtener una lista de alertas de una orden")
	@GetMapping(value = "/list/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Alerta>> list(@ApiParam(value = "El ID de la orden ")@PathVariable("id") Long id) {
		try {
			log.debug("GetMapping: Una lista de productos ");
			return new ResponseEntity<List<Alerta>>(alertaBusiness.list(id), HttpStatus.OK);

		} catch (BusinessException e) {
			return new ResponseEntity<List<Alerta>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<List<Alerta>>(HttpStatus.NOT_FOUND);
		}

	}

	@ApiOperation(value="Aceptar una Alerta")
	@PutMapping(value = "/aceptar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody Alerta alerta,@PathVariable("id") Long id) {
		try {
			alertaBusiness.aceptarAlerta(id, alerta);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			// e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			// e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
}
