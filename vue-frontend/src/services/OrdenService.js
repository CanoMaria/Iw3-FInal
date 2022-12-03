import axios from 'axios'

const ORDEN_API_BASE_URL = 'http://localhost:8080/api/v1/ordenes'

class OrdenService{
    getOrdenes(){
        return axios.get(ORDEN_API_BASE_URL, {
            headers: {
                'Authorization': 'Bearer R3BSRW9JUVR4K2NoUUNDZW1Hd1dpUT09OjhMWXFOSTE5bEhZNFRRMm40VWxuNXc9PQ'
            }
        } );
    }
}

export default new OrdenService()