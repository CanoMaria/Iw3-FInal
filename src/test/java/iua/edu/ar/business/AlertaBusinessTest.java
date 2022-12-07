package iua.edu.ar.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.business.exception.NotFoundException;
import iua.edu.ar.model.Alerta;

@ActiveProfiles("mysqldev")
@RunWith(SpringRunner.class)
@SpringBootTest
class AlertaBusinessTest {

	@Autowired
	AlertaBusiness alertaBusiness;
	
	@Rule
	public final ExpectedException ex = ExpectedException.none();
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Test
	final void testNoAceptarAlertaInexistente() {
		Alerta alerta= new Alerta();
		alerta.setUsrAceptador("ayee");
		alerta.setObservaciones("todo mal");
		try {
			alertaBusiness.aceptarAlerta((long) 100, alerta);
		} catch (NotFoundException | BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ex.expect(NotFoundException.class);
	}
	
	@Test
	final void testUltimaAlarmaEsDeClaseAlarma() {
		try {
			assertEquals(Alerta.class, alertaBusiness.loadLast((long)1).getClass());
			
		} catch (BusinessException | NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@Test
	final void testNoDevolverListaInexistente() {
		try {
			alertaBusiness.list((long) 100);
		} catch (BusinessException | NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ex.expect(NotFoundException.class);
	}
	
	

}
