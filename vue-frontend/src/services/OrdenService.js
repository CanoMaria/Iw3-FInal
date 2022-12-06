import axios from 'axios'

const ORDEN_API_BASE_URL = 'http://localhost:8080/api/v1/ordenes'

class OrdenService {
  async getOrdenes(token) {
    try {

      const response = await axios.get(ORDEN_API_BASE_URL, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });

      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async getOrdenesById(id, token) {
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

  async createOrden(nuevaOrden, token) {
    try {
      const response = await axios.post(ORDEN_API_BASE_URL, nuevaOrden, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });

      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async createOrden(nuevaOrden, token) {
    try {
      const response = await axios.post(ORDEN_API_BASE_URL, nuevaOrden, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });

      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async addPesajeInicial(pesajeInicial, token) {
    try {
      const response = await axios.post(ORDEN_API_BASE_URL + '/pesaje-inicial', pesajeInicial, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });

      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async addPesajeFinal(pesajeFinal, token) {
    try {
      const response = await axios.post(ORDEN_API_BASE_URL + '/pesaje-final', pesajeFinal, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });

      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async addCargaDatos(id, cargaDatos, token) {
    try {
      console.log(id)
      console.log(cargaDatos)
      console.log(token)

    
      const response = await axios.post(ORDEN_API_BASE_URL + '/carga-datos/' + id, cargaDatos, {
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


export default new OrdenService();