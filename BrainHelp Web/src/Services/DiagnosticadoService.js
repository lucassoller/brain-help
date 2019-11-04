import CONFIG from '../config'
import axios from 'axios'
import LoginService from './LoginService'
export default class DiagnosticadoService {

    static buscarMeusPacientes() {
		return axios.get(`${CONFIG.API_URL_BASE}/diagnosticado/buscar/todos/vinculados`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }
    
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
		return axios.get(`${CONFIG.API_URL_BASE}/diagnosticado/buscar/todos/desvinculados/${nome}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }
    
    static buscarPacientePorEmail(email) {
		return axios.get(`${CONFIG.API_URL_BASE}/diagnosticado/buscar/${email}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
	}
}