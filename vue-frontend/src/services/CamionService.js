import axios from 'axios'

const CAMION_API_BASE_URL = 'http://localhost:8080/api/v1/camiones'

class CamionService{
    getCamiones(){
        return axios.get(CAMION_API_BASE_URL);
    }
}

export default new CamionService()