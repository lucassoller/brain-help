import axios from 'axios'
import CONFIG from '../config'
import LoginService from './LoginService'

export default class MedicoService {
    static vincularPaciente(email){
        return axios.get(`${CONFIG.API_URL_BASE}/medico/vincular/${email}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static editarPerfil(nome, sobrenome, telefone, endereco, especializacao) {
        return axios.put(`${CONFIG.API_URL_BASE}/medico/editar/perfil`, {
            nome,
            sobrenome,  
            telefone,
            endereco,
            especializacao,
        },
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static obterMedicoLogado() {
        return axios.get(`${CONFIG.API_URL_BASE}/medico/buscar/${LoginService.getUserName()}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static alterarSenha(email, senha) {
        return axios.put(`${CONFIG.API_URL_BASE}/medico/editar/senha`, {           
            email,
            senha
        },
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }
}