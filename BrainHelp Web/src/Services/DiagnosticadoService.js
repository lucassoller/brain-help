import CONFIG from '../config'
import axios from 'axios'
import LoginService from './LoginService'
export default class DiagnosticadoService {

	static buscarTodosPorNome(nome) {
		return axios.get(`${CONFIG.API_URL_BASE}/diagnosticado/buscar/todos/nome/${nome}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
	}

	static buscarTodosPorEmail(email) {
		return axios.get(`${CONFIG.API_URL_BASE}/diagnosticado/buscar/todos/email/${email}`,{},
        {
            headers: {
                authorization: LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
	}
}