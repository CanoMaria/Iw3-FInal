import axios from 'axios'

const LOGIN_API_BASE_URL = 'http://localhost:8080/api/v1/login'

class TokenService{
    getTokens(pUsername, pPassword){
        return axios.post(LOGIN_API_BASE_URL,null, {
            params: {
                username: pUsername,
                password: pPassword
            }
        }).then(response => {
                console.log(response.data);
                this.token_generated = response.data
        });
    }
}

export default new TokenService()