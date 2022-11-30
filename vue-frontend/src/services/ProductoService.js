import axios from 'axios'

const PRODUCTO_API_BASE_URL = 'http://localhost:8080/api/v1/productos'

class ProductoService{
    getProductos(){
        return axios.get(PRODUCTO_API_BASE_URL);
    }
}

export default new ProductoService()