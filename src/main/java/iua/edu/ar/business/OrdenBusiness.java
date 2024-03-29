package iua.edu.ar.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.business.exception.NotFoundException;
import iua.edu.ar.business.exception.PasswordException;
import iua.edu.ar.model.Alerta;
import iua.edu.ar.model.AlertaDatos;
import iua.edu.ar.model.DatoCarga;
import iua.edu.ar.model.EstadoAlerta;
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
	@Autowired
	AlertaBusiness alertBusiness;
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
	/**
	 * Funcion para cargar una nueva Orden
	 * 
	 * @param orden    Todos los datos de la nueva orden
	 
	 */
	public Orden add(Orden orden) throws BusinessException {
		System.out.println(orden.toString());
		try {
			//Verificamos que este en estado 0
			if(orden.getEstado() == 0) {
				//obtenemos el cisternado del camnion
				int [] cisternadoArray=orden.getCamion().getCisternado();
				int preset=0;
					
				//Calculamos el tamanio del preset
				for(int i=0;i<cisternadoArray.length;i++){
					preset=preset+cisternadoArray[i];
				}
				//Guardamos el limite del preset
				orden.setPreset(preset);
				//Guardamos el id de la orden en la alerta
				orden.getAlertaDatos().setOrden(orden);
				
				//Si todos lo datos son correctos, guardamos en estado 1
				if (orden.checkBasicData()) {
					orden.setEstado(1);
				}else {
					System.out.println(orden.checkBasicData());
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
			//Cargamos la orden desde la BD
			ordenDB = load(idOrden);
			
			//Verificamos que la misma este en el estado correcto (osea el 2)
			if (ordenDB.getEstado() != 2)
				throw new BusinessException("Estado incorrecto");
			
			//Cargamos la Fecha actual
			Date actualDate = new Date();
			
			//----Actualizamos el registro de "Ultimo dato cargado"---
			//Cargamos el ultimo registro almacenado
			UltimoDatoCarga ultimosDatosCarga = new UltimoDatoCarga(
					datosCarga.getMasaAcumulada(),
					datosCarga.getDensidadProducto(), 
					datosCarga.getTemperaturaProducto(), 
					datosCarga.getCaudal()
					);
			
			//Seteamos que como ultimo dato de carga es la fecha actual
			ultimosDatosCarga.setFecha(actualDate);
			
			//Si no existia ningun registro de carga anterior (osea es este el 1er registro)
			if(ordenDB.getUltimosDatosCarga() != null) {
				Double masaAcumuladaRegistrada=ordenDB.getUltimosDatosCarga().getMasaAcumulada(); 
				
				//Verificamos que la masa actual acumulada sea efectivamente mayor 
				if(masaAcumuladaRegistrada>ultimosDatosCarga.getMasaAcumulada()){
					throw new BusinessException("La masa acumulada no puede ser menor");
				}
				//Verificamos que la masa acumulada no sea mayor al preset (no deberia ocurrir)
				if(ultimosDatosCarga.getMasaAcumulada()>ordenDB.getPreset()) {
					throw new BusinessException("La masa acumulada no puede ser mayor al preset");
				}
			}
			
			//Guardamos este registro como el ultimo
			ordenDB.setUltimosDatosCarga(ultimosDatosCarga);
			add(ordenDB);
			
			//----Actualizamos el registro de "Orden Detalle---
			//Generamos un nuevo orden detalle y almacenamos los datos nuevos
			OrdenDetalle ordenDetalle = new OrdenDetalle();
			ordenDetalle.setCaudal(datosCarga.getCaudal());
			ordenDetalle.setDensidadProducto(datosCarga.getDensidadProducto());
			ordenDetalle.setMasaAcumulada(datosCarga.getMasaAcumulada());
			ordenDetalle.setOrden(ordenDB);
			ordenDetalle.setTemperaturaProducto(datosCarga.getTemperaturaProducto());
			ordenDetalle.setFecha(actualDate);
			
						
			//Verificamos si ya se envio un mensaje de alerta
			
			//obtenemos la ultima alerta que tuvo
			Alerta lastAlert= new Alerta();
			if(ordenDB.getAlertList().size()!=0) {
				lastAlert=ordenDB.getAlertList().get(ordenDB.getAlertList().size()-1);
			}
			
			if(lastAlert.getEstado().equals(EstadoAlerta.NO_ENVIADO)|| lastAlert.getEstado().equals(EstadoAlerta.ACEPTADO)) {
				//Obtenemos los datos para comunicar la alerta
				AlertaDatos dataAlert=ordenDB.getAlertaDatos();
				
				//Funcion que verifica la temperatura y envia un mail en caso de alerta
				boolean alertStatus = sendAlarm(datosCarga.getTemperaturaProducto(),idOrden,dataAlert);
				
				//Si se detecto una alerta registramos que ya se envio el mail
				if(alertStatus==true) {
					Alerta newAlert= new Alerta();
					newAlert.setEstado(EstadoAlerta.ENVIADO);
					newAlert.setOrden(ordenDB);
					//guardamos el nuevo estado de la alerta
					alertBusiness.add(newAlert);
					
				}
			}
			
					
			//Buscamos la lista de ordenes detalle por el Id de orden
			List<OrdenDetalle> orderDetailListByOrderId = ordenDetalleDAO.findByOrdenId(idOrden);
			
			//Verificamos que exista algun detalle de orden, caso contrario la guardamos como nueva
			if (orderDetailListByOrderId.isEmpty()) {
				ordenDetalleDAO.save(ordenDetalle);
				return;
			}
			
			//En caso de que exista algun detalle de orden anterior lo cargamos y filtramos por la mas reciente
			OrdenDetalle oldOrderDetail = ordenDetalleDAO.findFirstByOrdenIdOrderByFecha(idOrden);
			Date oldDate = oldOrderDetail.getFecha();
			
			//Calculamos la diferencia y solo almacenamos el detalle de orden que sea superior a la frecuencia de guardado
			System.out.println(oldDate+"-------"+actualDate);
			long segundos = getDateDiff(oldDate, actualDate, TimeUnit.SECONDS);
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
	
	public boolean sendAlarm(Double temperature,Long idOrden,AlertaDatos alertData) {
		double temperatureLimit=alertData.getTemperaturaMax();
		String emailString=alertData.getMail(); //obtenemos un string con formato [mail1,mail2]
		emailString=emailString.substring(1, emailString.length()-1);
		String emailArray[]=emailString.split(",");
				
		//para mas info ver:https://netcorecloud.com/tutorials/send-email-in-java-using-gmail-smtp/
		//Verificamos que la temperatura no sea mayor a lo permitido
		if(temperature>temperatureLimit) {
			
			for(int i=0;i<emailArray.length;i++) {
				System.out.println("Email:" +emailArray[i]);
				// Recipient's email ID needs to be mentioned.
		        String to = emailArray[i];

		        // Sender's email ID needs to be mentioned
		        String from = "mcano596@alumnos.iua.edu.ar";

		        // Assuming you are sending email from through gmails smtp
		        String host = "smtp.gmail.com";

		        // Get system properties
		        Properties properties = System.getProperties();

		        // Setup mail server
		        properties.put("mail.smtp.host", host);
		        properties.put("mail.smtp.port", "465");
		        properties.put("mail.smtp.ssl.enable", "true");
		        properties.put("mail.smtp.auth", "true");

		        // Get the Session object.// and pass username and password
		        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(from, "skipbeat");
		            }

		        });

		        // Used to debug SMTP issues
		        session.setDebug(true);

		        try {
		            // Create a default MimeMessage object.
		            MimeMessage message = new MimeMessage(session);

		            // Set From: header field of the header.
		            message.setFrom(new InternetAddress(from));

		            // Set To: header field of the header.
		            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		            // Set Subject: header field
		            message.setSubject("Alerta de temperatura!");

		            // Now set the actual message
		            message.setContent(
		              "<h1>Alerta!! La temperatura actual de la orden: "+idOrden+" es de "+temperature+"C </h1>","text/html");
		       
		            System.out.println("sending...");
		            // Send message
		            Transport.send(message);
		            System.out.println("Sent message successfully....");
		        } catch (MessagingException mex) {
		            mex.printStackTrace();
		        }
			}       
			return true;
		}
		return false;
	}
	
	/**
	 * Get a diff between two dates
	 * 
	 * @param oldDate    the oldest date
	 * @param actualDate    the newest date
	 * @param timeUnit the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	
	public static long getDateDiff(Date oldDate, Date actualDate, TimeUnit timeUnit) {
		long diffInMillies = actualDate.getTime() - oldDate.getTime();
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
