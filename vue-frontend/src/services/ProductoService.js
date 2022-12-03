import axios from 'axios'

const PRODUCTO_API_BASE_URL = 'http://localhost:8080/api/v1/productos'

class ProductoService{
    getProductos(){
        return axios.get(PRODUCTO_API_BASE_URL, {
            headers: {
                'Authorization': 'Bearer R3BSRW9JUVR4K2NoUUNDZW1Hd1dpUT09OjhMWXFOSTE5bEhZNFRRMm40VWxuNXc9PQ'
            }
        } );
    }
}

export default new ProductoService()