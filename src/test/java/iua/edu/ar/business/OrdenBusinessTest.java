package iua.edu.ar.business;

import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Random;

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

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import iua.edu.ar.model.AlertaDatos;
import iua.edu.ar.model.Camion;
import iua.edu.ar.model.Chofer;
import iua.edu.ar.model.Cliente;
import iua.edu.ar.model.Orden;
import iua.edu.ar.model.Producto;
import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.business.exception.NotFoundException;


@ActiveProfiles("mysqldev")
@RunWith(SpringRunner.class)
@SpringBootTest
class OrdenBusinessTest {
	@Autowired
	OrdenBusiness ordenBusiness;
	
	@Autowired
	ChoferBusiness chofer;
	@Rule
	public final ExpectedException ex = ExpectedException.none();
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	//Test que verifica que la orden se guarde correctamente
	@Test
	final void testGuardarOrden() throws BusinessException {
		Orden ordenEsperada= cargarOrden();
		Orden ordenObtenida = ordenBusiness.add(ordenEsperada);
		System.out.print(ordenObtenida);
		assertEquals(ordenEsperada, ordenObtenida);
			
	}
	
	
	
	//Teste que verifica que en caso que alguno de los datos este mal, no gaurde la orden
	@Test()
	final void testNoAceptarDatosErroneos()  {
		Orden ordenEsperada= cargarOrden();
		ordenEsperada.setFechaRecepcion(null);
		
		try {
			ordenBusiness.add(ordenEsperada);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ex.expect(BusinessException.class);
		
	}
	public Orden cargarOrden() {
		Orden ordenEsperada= new Orden();
		Cliente clienteEsperado = new Cliente();
		Chofer choferEsperado = new Chofer();
		Camion camionEsperado = new Camion();
		AlertaDatos alertaEsperado = new AlertaDatos();
		Producto productoEsperado = new Producto();
		Long numRand= new Random().nextLong();
		String codigoEsperado=numRand.toString();
		int cisterna[]= {10,10,10};
		
		clienteEsperado.setContacto("333333333");
		clienteEsperado.setRazonSocial("osde");
		
		choferEsperado.setNombre("Ayelen");
		choferEsperado.setApellido("Cano");
		choferEsperado.setDocumento(111111111);
		
		alertaEsperado.setMail("[ayecano98@gmail.com,nbrambilla024@alumnos.iua.edu.ar]");
		alertaEsperado.setTemperaturaMax(50);
		
		productoEsperado.setNombre("harina");
		productoEsperado.setDescripcion("muy buena");
		
		camionEsperado.setPatente("xxx-xxx-xxx");
		camionEsperado.setDescripcion("camion de nafta");
		camionEsperado.setCisternado(cisterna);
		
		ordenEsperada.setFechaRecepcion(new Date());
		ordenEsperada.setCliente(clienteEsperado);
		ordenEsperada.setChofer(choferEsperado);
		
		ordenEsperada.setCamion(camionEsperado);
		ordenEsperada.setAlertaDatos(alertaEsperado);
		ordenEsperada.setProducto(productoEsperado);
		ordenEsperada.setCodigoExterno(codigoEsperado);
		ordenEsperada.setEstado(0);
		
		return ordenEsperada;
	}
	
	
}
