import axios from 'axios'

const ORDEN_API_BASE_URL = 'http://localhost:8080/api/v1/alerta'

class AlertaService {
  async getAlerta(id, token) {
    try {

      const response = await axios.get(ORDEN_API_BASE_URL + '/' + id, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });

      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async getAlertaList(id, token) {
    try {

      const response = await axios.get(ORDEN_API_BASE_URL + '/list/' + id, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      console.log(response.data)
      return response.data;
    } catch (error) {
      throw error;
    }
  }
  
  async acceptAlerta(id,cargaDatos,token) {
    try {

      const response = await axios.put(ORDEN_API_BASE_URL + '/aceptar/' + id, cargaDatos, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });

      return response.data;
    } catch (error) {
      throw error;
    }
  }
}


export default new AlertaService();