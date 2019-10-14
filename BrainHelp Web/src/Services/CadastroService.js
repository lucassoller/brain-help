import axios from 'axios'
import CONFIG from '../config'
export default class CadastroService {
    static register(nome, email, senha, localTrabalho, google) {
        return axios.post(`${CONFIG.API_URL_BASE}/public/registro/medico`, {
            nome,
            email,
            senha,
            localTrabalho,
            google
        })
    }
}