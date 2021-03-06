import CONFIG from '../config'
import axios from 'axios'

const LOGGED_USER = 'LOGGED_USER'
const USER_EMAIL = 'USER_EMAIL'
const LOGIN_TYPE = 'LOGIN_TYPE'

export default class LoginService {
	static setLoggedUser(token, email, google) {
		localStorage.setItem(LOGGED_USER, token)
		localStorage.setItem(USER_EMAIL, email)
		localStorage.setItem(LOGIN_TYPE, google)
	}

	static login(identificacao, senha) {
		return axios.post(`${CONFIG.API_URL_BASE}/public/login/medico`, {
			identificacao,
			senha
		}).then((result) => {
			this.setLoggedUser(result.data.token, result.data.email, result.data.google)
			return result
		})
	}

	static loginSocial(identificacao) {
		return axios.post(`${CONFIG.API_URL_BASE}/public/login/medico/google`, {
			identificacao
		}).then((result) => {
			this.setLoggedUser(result.data.token, result.data.email)
			return result
		})
	}

	static getLoggedUser() {
		return localStorage.getItem(LOGGED_USER)
	}

	static getUserName(){
		return localStorage.getItem(USER_EMAIL)
	}

	static getLoginType(){
		return localStorage.getItem(LOGIN_TYPE)
	}

	static logout() {
		localStorage.removeItem(LOGGED_USER);
		localStorage.removeItem(USER_EMAIL);
		localStorage.removeItem(LOGIN_TYPE);
	}
}