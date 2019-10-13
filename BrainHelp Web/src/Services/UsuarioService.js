import axios from 'axios'
import CONFIG from '../config'
import LoginService from './LoginService'
export default class ReservaService {
    static buscarUsuariosPorUsername(username, pagina) {
        return axios.get(`${CONFIG.API_URL_BASE}/usuario/buscar/todos/${username}/${pagina}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static buscarPrimeirosUsuarios(username) {
        return axios.get(`${CONFIG.API_URL_BASE}/usuario/buscar/primeiros/${username}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static editar(foto, senha, confirmarSenha) {
        return axios.put(`${CONFIG.API_URL_BASE}/usuario`,
        {
            foto,
            senha,
            confirmarSenha
        },
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }
}