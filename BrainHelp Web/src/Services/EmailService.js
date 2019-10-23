import axios from 'axios'
import CONFIG from '../config'
export default class EmailService {
    static redefinirSenha(url) {
        return axios.get(`${CONFIG.API_URL_BASE}/public/email/redefinir/medico/${url}`, {})
    }

    static recuperarSenha(email) {
        return axios.get(`${CONFIG.API_URL_BASE}/public/email/recuperar/medico/${email}`, {})
    }
}