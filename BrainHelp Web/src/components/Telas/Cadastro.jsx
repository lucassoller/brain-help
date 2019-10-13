import React from 'react'
import Alert from '../generic/Alert/Alert'
import CadastroService from '../../Services/CadastroService'
import LoginService from '../../Services/LoginService'
import './Cadastro.css'
import {Redirect} from 'react-router-dom'
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
            nome: '',
            email: '',
            senha: '',
            confirmar: '',
            local: '',
            erro: '',
            selectedContent: SELECTED_CONTENTS.CADASTRO
        }
        this.onShowOver = this.onShowOver.bind(this)
        this.onShowOut = this.onShowOut.bind(this)
        this.onShowOverConfirmacao = this.onShowOverConfirmacao.bind(this)
        this.onShowOutConfirmacao = this.onShowOutConfirmacao.bind(this)
        this.onClickLinkLogin = this.onClickLinkLogin.bind(this)
    }

    onClickLinkCadastrar() {
        const account = this.state
        if(account.senha !== account.confirmar){
            this.setState({
                error: 'As senhas não coincidem'
            })
        }else{
            CadastroService
            .register('123', account.nome, 'sobrenome', account.email,
                account.senha, account.local, 'medico')
            .then((result) => {
                LoginService.login(account.email, account.senha)
                .then((r) =>{
                    this.setSelectedContent(SELECTED_CONTENTS.HOME)
                })
            }).catch((err) => {
                this.setState({
                    error: err.response.data.message
                })
            })
        }    
    }

    onShowOver(){
        $(".cadastro-confirmacao").attr("type", "text");
    }

    onShowOut(){
        $(".cadastro-confirmacao").attr("type", "password");
    }

    onShowOverConfirmacao(){
        $(".cadastro-confirmacao-2").attr("type", "text");
    }

    onShowOutConfirmacao(){
        $(".cadastro-confirmacao-2").attr("type", "password");
    }


    onClickLinkLogin(){
        this.setSelectedContent(SELECTED_CONTENTS.LOGIN)
    }

    setSelectedContent(content) {
        this.setState({
            selectedContent: content
        })
    }

    renderError() {
        return this.state.error ? <Alert text={this.state.error} alertType="danger" /> : undefined
    }

    render() {
        const responseGoogle = (response) => {
            console.log(response);
        }

        if(this.state.selectedContent === SELECTED_CONTENTS.LOGIN){
            return <Redirect to='/login' />
        }

        if( this.state.selectedContent === SELECTED_CONTENTS.HOME){
            return <Redirect to='/home' />
        }
       return (
        <div className="cadastro-container">
            <div className="cadastro-left-container">
                <div className="cadastro-content">
                    {this.renderError()}
                    <div className="cadastro-title">
                        <h1>Nova conta</h1>
                    </div>
                    <input
                        type="text"
                        className="cadastro-placeholder cadastro-form"
                        name="nome"
                        placeholder="Nome completo"
                        required = "true"
                    />
                    <input
                        type="text"
                        className="cadastro-placeholder cadastro-form"
                        name="email"
                        placeholder="Email"
                        required = "true"
                    />
                    <div className="cadastro-senha">
                        <input
                            type="password"
                            className="cadastro-placeholder cadastro-form-senha cadastro-confirmacao"
                            name="senha"
                            placeholder="Senha"
                            required = "true"
                        />
                        <div className="cadastro-eye" onMouseOver={this.onShowOver} onMouseOut={this.onShowOut}></div>
                    </div>
                    <div className="cadastro-senha">
                        <input
                            className="cadastro-placeholder cadastro-form-senha cadastro-confirmacao-2"
                            name="confimar"
                            placeholder="Confirmar senha"
                            type="password"
                            required = "true"
                        />
                        <div className="cadastro-eye" onMouseOver={this.onShowOverConfirmacao} onMouseOut={this.onShowOutConfirmacao}></div>
                    </div>
                    <input
                        type="text"
                        className="cadastro-form cadastro-placeholder"
                        name="local"
                        placeholder="Local de trabalho"
                        required = "true"
                    />
                    <div className="cadastro-entrar">Cadastrar</div>
                    <div className="cadastro-footer">
                        <div>
                            Já possui uma conta?
                        </div>
                        <div className="cadastro-login" onClick={this.onClickLinkLogin}>
                            Faça o login
                        </div>
                    </div>
                </div>
            </div>
        
        </div>)
    }
}