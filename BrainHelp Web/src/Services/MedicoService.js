import axios from 'axios'
import CONFIG from '../config'
import LoginService from './LoginService'

export default class MedicoService {
    static vincularPaciente(email){
        console.log(LoginService.getLoggedUser())
        return axios.get(`${CONFIG.API_URL_BASE}/medico/vincular/${email}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }
}