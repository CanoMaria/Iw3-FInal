import axios from 'axios'

const CHOFER_API_BASE_URL = 'http://localhost:8080/api/v1/choferes'

class ChoferService{
    getChoferes(){
        return axios.get(CHOFER_API_BASE_URL);
    }
}

export default new ChoferService()