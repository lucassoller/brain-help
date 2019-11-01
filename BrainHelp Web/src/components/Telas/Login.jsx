import React from 'react'
import Alert from '../generic/Alert/Alert'
import LoginService from '../../Services/LoginService'
import './Login.css'
import {Redirect} from 'react-router-dom'
import GoogleLogin from 'react-google-login';
import $ from 'jquery'

const SELECTED_CONTENTS = {
    LOGIN: 'LOGIN',
    CADASTROINICIAL: 'CADASTROINICIAL',
    HOME: 'HOME',
    ESQUECERSENHA: 'ESQUECERSENHA'
}

export default class Login extends React.Component {
    
    constructor() {
        super()
        this.state = {
            email: '',
            senha: '',
            error: '',
            show: true,
            social: false,
            selectedContent: SELECTED_CONTENTS.LOGIN
        }
        this.handdleChange = this.handdleChange.bind(this)
        this.onShowOver = this.onShowOver.bind(this)
        this.onShowOut = this.onShowOut.bind(this)
        this.onClickLinkCadastro = this.onClickLinkCadastro.bind(this)
        this.onClickLinkSenha = this.onClickLinkSenha.bind(this)
        this.onClickLinkEntrar = this.onClickLinkEntrar.bind(this)
    }

    handdleChange(event) {
        const target = event.target
        const value = target.value
        const name = target.name
        this.setState({
            [name]: value
        })
    }

    onClickLinkEntrar(){
        const account = this.state
            if(account.social === true){
                LoginService.loginSocial(account.email)
                .then(() => {
                    this.setSelectedContent(SELECTED_CONTENTS.HOME)
                }).catch((err) => {
                    this.setState({
                        error: err.response.data.message
                    })
                })
            }else{
                LoginService.login(account.email, account.senha)
                .then(() => {
                    this.setSelectedContent(SELECTED_CONTENTS.HOME)
                }).catch((err) => {
                    this.setState({
                        error: err.response.data.message
                    })
                })
            }
    }

    onClickLinkCadastro(){
        this.setSelectedContent(SELECTED_CONTENTS.CADASTROINICIAL)
    }

    onClickLinkSenha(){
        this.setSelectedContent(SELECTED_CONTENTS.ESQUECERSENHA)
    }

    setSelectedContent(content) {
        this.setState({
            selectedContent: content
        })
    }

    onShowOver(){
        if(this.state.show === true){
            $(".form-senha").attr("type", "text");
        }
    }

    onShowOut(){
        $(".form-senha").attr("type", "password");
    }

    renderError() {
        return this.state.error ? <Alert text={this.state.error} alertType="danger" /> : undefined
    }

    render() {
        const responseGoogle = (response) => {
            this.setState({
                email: response.profileObj.email,
                show: false,
                social: true
            })
            this.onClickLinkEntrar()
        }

        if(this.state.selectedContent === SELECTED_CONTENTS.CADASTROINICIAL){
            return <Redirect to='/cadastro-inicial' />
        }

        if( this.state.selectedContent === SELECTED_CONTENTS.HOME){
            return <Redirect to='/home/meus-pacientes' />
        }

        if( this.state.selectedContent === SELECTED_CONTENTS.ESQUECERSENHA){
            return <Redirect to='/esquecer' />
        }

       return (
        <div className="login-container">
            <div className="login-right-container">
                <div className="login-content">
                    {this.renderError()}
                    <div className="login-title">
                        <h1>Entrar</h1>
                    </div>
                        <GoogleLogin
                            clientId="269388326604-4vldemgim545v46bel9he4erpji2sgpl.apps.googleusercontent.com" //CLIENTID NOT CREATED YET
                            buttonText="Entrar com o Google"
                            className="login-google"
                            onSuccess={responseGoogle}
                        />
                    <div className="divider">
                        <div className="barra"></div>
                        <div className="or">ou</div>
                        <div className="barra"></div>
                    </div>
                    <input
                        type="text"
                        className="form form-email"
                        name="email"
                        placeholder="Email"
                        value={this.state.email}
                        onChange={this.handdleChange}
                    />
                    <div className="login-form-senha">
                        <input
                            type="password"
                            className="form form-senha"
                            name="senha"
                            placeholder="Senha"
                            value={this.state.senha}
                            onChange={this.handdleChange}
                        />
                        <div className="eye" onMouseOver={this.onShowOver} onMouseOut={this.onShowOut}></div>
                    </div>
                    <div className="login-senha" onClick={this.onClickLinkSenha}>Esqueceu sua senha?</div>
                    <div className="login-entrar" onClick={this.onClickLinkEntrar}>Entrar</div>
                    <div className="login-footer">
                        <div>
                            Não possui uma conta?
                        </div>
                        

                        <div className="login-cadastro" onClick={this.onClickLinkCadastro}>
                            Cadastre-se
                        </div>
                    </div>
                </div>
            </div>
        
        </div>)
    }
}