import CONFIG from '../config'
import axios from 'axios'
import LoginService from './LoginService'
export default class DiagnosticadoService {

	static buscarMeusPacientesPorNome(nome) {
		return axios.get(`${CONFIG.API_URL_BASE}/diagnosticado/buscar/todos/vinculados/${nome}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }
    
    static buscarOutrosPacientesPorNome(nome) {
		return axios.get(`${CONFIG.API_URL_BASE}/diagnosticado/buscar/todos/${nome}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
	}
}