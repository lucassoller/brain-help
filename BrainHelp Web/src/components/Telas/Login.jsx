import React from 'react'
import Input from '../generic/Input/Input'
import Button from '../generic/Button/Button'
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
    SENHA: 'SENHA'

}

export default class Login extends React.Component {
    
    constructor() {
        super()
        this.state = {
            email: '',
            senha: '',
            error: '',
            selectedContent: SELECTED_CONTENTS.LOGIN
        }
        this.onShowOver = this.onShowOver.bind(this)
        this.onShowOut = this.onShowOut.bind(this)
        this.onClickLinkCadastro = this.onClickLinkCadastro.bind(this)
        this.onClickLinkSenha = this.onClickLinkSenha.bind(this)
        this.onClickLinkEntrar = this.onClickLinkEntrar.bind(this)
    }

    onClickLinkEntrar(){
        const account = this.state
            LoginService.login(account.email, account.senha)
            .then(() => {
                this.setSelectedContent(SELECTED_CONTENTS.HOME)
            }).catch((err) => {
                this.setState({
                    error: err.response.data.message
                })
            })
    }

    onClickLinkCadastro(){
        this.setSelectedContent(SELECTED_CONTENTS.CADASTROINICIAL)
    }

    onClickLinkSenha(){
        this.setSelectedContent(SELECTED_CONTENTS.SENHA)
    }

    setSelectedContent(content) {
        this.setState({
            selectedContent: content
        })
    }

    onShowOver(){
        $(".form-senha").attr("type", "text");
    }

    onShowOut(){
        $(".form-senha").attr("type", "password");
    }

    renderError() {
        return this.state.error ? <Alert text={this.state.error} alertType="danger" /> : undefined
    }

    render() {
        const responseGoogle = (response) => {
            console.log(response);
        }

        if(this.state.selectedContent === SELECTED_CONTENTS.CADASTROINICIAL){
            return <Redirect to='/cadastro-inicial' />
        }

        if( this.state.selectedContent === SELECTED_CONTENTS.HOME){
            return <Redirect to='/home' />
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
                            onFailure={responseGoogle}
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
                        required = "true"
                    />
                    <div className="login-form-senha">
                        <input
                            type="password"
                            className="form form-senha"
                            name="senha"
                            placeholder="Senha"
                            required = "true"
                        />
                        <div className="eye" onMouseOver={this.onShowOver} onMouseOut={this.onShowOut}></div>
                    </div>
                    <div className="login-senha">Esqueceu sua senha?</div>
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