import axios from 'axios'
import TokenService from '@/services/TokenService'

const CAMION_API_BASE_URL = 'http://localhost:8080/api/v1/camiones'


class CamionService {
  async getCamiones() {
    try {
      const token = await TokenService.getTokens();


      const response = await axios.get(CAMION_API_BASE_URL, {
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

export default new CamionService();