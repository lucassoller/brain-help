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
    REGISTER: 'REGISTER',
    HOME: 'HOME',
    SENHA: 'SENHA'

}

export default class Login extends React.Component {
    
    constructor() {
        super()
        this.onShowOver = this.onShowOver.bind(this)
        this.onShowOut = this.onShowOut.bind(this)
    }


    onShowOver(){
        $(".form-senha").attr("type", "text");
    }

    onShowOut(){
        $(".form-senha").attr("type", "password");
    }

    render() {
        const responseGoogle = (response) => {
            console.log(response);
        }

       return (
        <div className="login-container">
            <div className="login-right-container">
                <div className="login-content">
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
                    />
                    <input
                        type="password"
                        className="form form-senha"
                        name="email"
                        placeholder="Senha"
            
                    />
                    <div className="eye" onMouseOver={this.onShowOver} onMouseOut={this.onShowOut}></div>
                    <div className="login-senha">Esqueceu sua senha?</div>
                    <div className="login-entrar">Entrar</div>
                    <div className="login-footer">
                        <div>
                            Não possui uma conta?
                        </div>
                        

                        <div className="login-cadastro">
                            Cadastre-se
                        </div>
                    </div>
                </div>
            </div>
        
        </div>)
    }
}