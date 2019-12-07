import axios from 'axios'
import CONFIG from '../config'
export default class RegisterService {
    static register(nome, sobrenome, email, senha, google, telefone, endereco, especializacao, foto) {
        return axios.post(`${CONFIG.API_URL_BASE}/public/registro/medico`, {
            nome,
            sobrenome,
            email,
            senha,
            google,
            telefone,
            endereco,
            especializacao,
            foto
        })
    }

    static editar(email, senha) {
        return axios.put(`${CONFIG.API_URL_BASE}/public/registro/medico/editar/senha`, {           
            email,
            senha
        })
    }
}