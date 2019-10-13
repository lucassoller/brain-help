import axios from 'axios'
import CONFIG from '../config'
export default class RegisterService {
    static register(codMedico, nome, sobrenome, email, senha, localTrabalho, especializacao) {
        return axios.post(`${CONFIG.API_URL_BASE}/public/registro/medico`, {
            codMedico,
            nome,
            sobrenome,
            email,
            senha,
            localTrabalho,
            especializacao
        })
    }
}