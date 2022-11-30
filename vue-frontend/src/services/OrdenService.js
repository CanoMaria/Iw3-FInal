import axios from 'axios'

const ORDEN_API_BASE_URL = 'http://localhost:8080/api/v1/ordenes'

class OrdenService{
    getOrdenes(){
        return axios.get(ORDEN_API_BASE_URL);
    }
}

export default new OrdenService()