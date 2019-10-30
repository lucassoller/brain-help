import React from 'react'
import './CadastroInicial.css'
import {Redirect} from 'react-router-dom'
import GoogleLogin from 'react-google-login';
import Cadastro from './Cadastro'

const SELECTED_CONTENTS = {
    LOGIN: 'LOGIN',
    CADASTRO: 'CADASTRO',
    CADASTROINICIAL: 'CADASTROINICIAL',
    HOME: 'HOME',
    SENHA: 'SENHA'
}

export default class Log extends React.Component {
    
    constructor() {
        super()
        this.state = {
            proximo: false,
            google: false,
            email: '',
            nome: '',
            sobrenome: '',
            senha: '',
            disabled: false,
            selectedContent: SELECTED_CONTENTS.CADASTROINICIAL
        }
        this.onClickLinkLogin = this.onClickLinkLogin.bind(this)
        this.onClickLinkCadastro = this.onClickLinkCadastro.bind(this)
    }

    onClickLinkLogin(){
        this.setSelectedContent(SELECTED_CONTENTS.LOGIN)
    }

    onClickLinkCadastro(){
        this.setState({
            proximo: true
        })
    }

    setSelectedContent(content) {
        this.setState({
            selectedContent: content
        })
    }

    renderTela(){
        if(this.state.proximo === false){
            const responseGoogle = (response) => {
                this.setState({
                    email: response.profileObj.email,
                    nome: response.profileObj.givenName,
                    sobrenome: response.profileObj.familyName,
                    senha: '12345678',
                    disabled: true,
                    proximo: true,
                    google: true
                })
            }

            return <div className="cadastro-inicial-container">
                        <div className="cadastro-inicial-left-container">
                            <div className="cadastro-inicial-content">
                                <div className="cadastro-inicial-title">
                                    <h1>Cadastro</h1>
                                </div>
                                    <GoogleLogin
                                        clientId="269388326604-4vldemgim545v46bel9he4erpji2sgpl.apps.googleusercontent.com" //CLIENTID NOT CREATED YET
                                        buttonText="Cadastrar com o Google"
                                        className="cadastro-inicial-google"
                                        onSuccess={responseGoogle}
                                    />
                                <div className="cadastro-inicial-divider">
                                    <div className="cadastro-inicial-barra"></div>
                                    <div className="cadastro-inicial-or">ou</div>
                                    <div className="cadastro-inicial-barra"></div>
                                </div>
                                <div className="cadastro-inicial-entrar" onClick={this.onClickLinkCadastro}>Cadastrar</div>
                                <div className="cadastro-inicial-footer">
                                    <div>
                                        Já possui uma conta?
                                    </div>
                                    <div className="cadastro-inicial-login" onClick={this.onClickLinkLogin}>
                                        Faça o login
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            }else{
                return <Cadastro 
                        nome={this.state.nome}
                        sobrenome={this.state.sobrenome}
                        email={this.state.email}
                        senha={this.state.senha}
                        disabled={this.state.disabled}
                        google={this.state.google}/>
            }
    }

    render() {
        if(this.state.selectedContent === SELECTED_CONTENTS.LOGIN){
            return <Redirect to='/login' />
        }

        if(this.state.selectedContent === SELECTED_CONTENTS.CADASTRO){
            return <Redirect to='/cadastro' />
        }

       return (this.renderTela())
    }
}