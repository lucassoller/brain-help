import React from 'react'
import Input from '../generic/Input/Input'
import Button from '../generic/Button/Button'
import Alert from '../generic/Alert/Alert'
import LoginService from '../../Services/LoginService'
import './CadastroInicial.css'
import {Redirect} from 'react-router-dom'
import GoogleLogin from 'react-google-login';
import Cadastro from './Cadastro'
import $ from 'jquery'
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
            nomeCompleto: '',
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
                    nomeCompleto: response.profileObj.givenName+" "+response.profileObj.familyName,
                    senha: '12345678',
                    disabled: true,
                    proximo: true,
                    google: true
                })
            }

            return <div>
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
            }else{
                return <Cadastro 
                        nomeCompleto={this.state.nomeCompleto}
                        email={this.state.email}
                        senha={this.state.senha}
                        disabled={this.state.disabled}/>
            }
    }

    render() {
        if(this.state.selectedContent === SELECTED_CONTENTS.LOGIN){
            return <Redirect to='/login' />
        }

        if(this.state.selectedContent === SELECTED_CONTENTS.CADASTRO){
            return <Redirect to='/cadastro' />
        }

       return (
        <div className="cadastro-inicial-container">
            <div className="cadastro-inicial-left-container">
                <div className="cadastro-inicial-content">
                    {this.renderTela()}            
                </div>
            </div>    
        </div>)
    }
}