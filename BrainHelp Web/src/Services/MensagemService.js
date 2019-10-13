import axios from 'axios'
import CONFIG from '../config'
import LoginService from './LoginService'
export default class MensagemService {
    static salvarMensagem(destinatario, conteudo) {
        return axios.post(`${CONFIG.API_URL_BASE}/mensagem/salvar`,
        {
            destinatario,
            conteudo
        },
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static buscarPorId(id) {
        return axios.get(`${CONFIG.API_URL_BASE}/mensagem/buscar/${id}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static buscarRecebidasEEnviadas(username, pagina) {
        return axios.get(`${CONFIG.API_URL_BASE}/mensagem/buscar/${username}/${pagina}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static buscarMensagensRecebidas(username, pagina) {
        return axios.get(`${CONFIG.API_URL_BASE}/mensagem/buscar/recebidas/${username}/${pagina}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static buscarMensagensEnviadas(username, pagina) {
        return axios.get(`${CONFIG.API_URL_BASE}/mensagem/buscar/enviadas/${username}/${pagina}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static buscarTodasMensagensDoUsuario(pagina) {
        return axios.get(`${CONFIG.API_URL_BASE}/mensagem/buscar/usuario/${pagina}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static buscarPorRemetente(pagina) {
        return axios.get(`${CONFIG.API_URL_BASE}/mensagem/buscar/usuario/remetente/${pagina}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static buscarPorDestinatario(pagina) {
        return axios.get(`${CONFIG.API_URL_BASE}/mensagem/buscar/usuario/destinatario/${pagina}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static buscarRecebidasEEnviadasComConteudo(username, conteudo, pagina) {
        return axios.get(`${CONFIG.API_URL_BASE}/mensagem/buscar/${username}/${conteudo}/${pagina}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static buscarMensagensRecebidasComConteudo(username, conteudo, pagina) {
        return axios.get(`${CONFIG.API_URL_BASE}/mensagem/buscar/recebidas/${username}/${conteudo}/${pagina}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static buscarMensagensEnviadasComConteudo(username, conteudo, pagina) {
        return axios.get(`${CONFIG.API_URL_BASE}/mensagem/buscar/enviadas/${username}/${conteudo}/${pagina}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static buscarTodasMensagensDoUsuarioComConteudo(conteudo, pagina) {
        return axios.get(`${CONFIG.API_URL_BASE}/mensagem/buscar/usuario/${conteudo}/${pagina}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static buscarPorRemetenteComConteudo(conteudo, pagina) {
        return axios.get(`${CONFIG.API_URL_BASE}/mensagem/buscar/usuario/remetente/${conteudo}/${pagina}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

    static buscarPorDestinatarioComConteudo(conteudo, pagina) {
        return axios.get(`${CONFIG.API_URL_BASE}/mensagem/buscar/usuario/destinatario/${conteudo}/${pagina}`,
        {
            headers: {
                authorization:  LoginService.getLoggedUser(),
                'Content-Type': 'application/json',
            }
        })
    }

}