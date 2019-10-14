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
            nome:'',
            email:'',
            senha: '',
            confirmar: '',
            local:'',
            error: '',
            google: false,
            selectedContent: SELECTED_CONTENTS.CADASTRO
        }

        this.handdleChange = this.handdleChange.bind(this)
        this.onShowOver = this.onShowOver.bind(this)
        this.onShowOut = this.onShowOut.bind(this)
        this.onShowOverConfirmacao = this.onShowOverConfirmacao.bind(this)
        this.onShowOutConfirmacao = this.onShowOutConfirmacao.bind(this)
        this.onClickLinkLogin = this.onClickLinkLogin.bind(this)
        this.onClickLinkCadastrar = this.onClickLinkCadastrar.bind(this)
    }

    handdleChange(event) {
        const target = event.target
        const value = target.value
        const name = target.name
        this.setState({
            [name]: value
        })
    }

    componentDidMount(){
        this.setState({
            senha: this.props.senha,
            confirmar: this.props.senha,
            email: this.props.email,
            nome: this.props.nomeCompleto,
            google: this.props.google
        })
    }

    onClickLinkCadastrar() {
        const account = this.state
        if(account.senha !== account.confirmar){
            this.setState({
                error: 'As senhas não coincidem'
            })
        }else{
            CadastroService
            .register(account.nome,  account.email,
                account.senha, account.local, account.google)
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
        if(this.props.disabled === false){
            $(".cadastro-confirmacao").attr("type", "text");
        }
    }

    onShowOut(){
        $(".cadastro-confirmacao").attr("type", "password");
    }

    onShowOverConfirmacao(){
        if(this.props.disabled === false){
            $(".cadastro-confirmacao-2").attr("type", "text");
        }
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
           <div>
                    {this.renderError()}
                    <div className="cadastro-title">
                        <h1>Nova conta</h1>
                    </div>
                    <input
                        value={this.state.nome} 
                        type="text"
                        className="cadastro-placeholder cadastro-form"
                        name="nome"
                        placeholder="Nome completo"
                        disabled={this.props.disabled}
                        required = "true"
                        onChange={this.handdleChange}      
                    />
                    <input
                        type="text"
                        className="cadastro-placeholder cadastro-form"
                        name="email"
                        placeholder= "Email"
                        required = "true"
                        value={this.state.email}
                        onChange={this.handdleChange} 
                        disabled={this.props.disabled} 
                    />

                    <div className="cadastro-senha">
                        <input
                            type="password"
                            className="cadastro-placeholder cadastro-form-senha cadastro-confirmacao"
                            name="senha"
                            placeholder="Senha"
                            required = "true"
                            value={this.state.senha}
                            onChange={this.handdleChange}  
                            disabled={this.props.disabled}
                        />
                        <div className="cadastro-eye" onMouseOver={this.onShowOver} onMouseOut={this.onShowOut}></div>
                    </div>
                    <div className="cadastro-senha">
                        <input
                            type="password"
                            className="cadastro-placeholder cadastro-form-senha cadastro-confirmacao-2"
                            name="confirmar"
                            placeholder="Senha"
                            required = "true"
                            value={this.state.confirmar}
                            onChange={this.handdleChange}  
                            disabled={this.props.disabled}
                        />
                       
                        <div className="cadastro-eye" onMouseOver={this.onShowOverConfirmacao} onMouseOut={this.onShowOutConfirmacao}></div>
                    </div>
                    <input
                        type="text"
                        className="cadastro-form cadastro-placeholder"
                        name="local"
                        placeholder="Local de trabalho"
                        required = "true"
                        value={this.state.local}
                        onChange={this.handdleChange}  
                    />
                    <div className="cadastro-entrar" onClick={this.onClickLinkCadastrar}>Cadastrar</div>
                    <div className="cadastro-footer">
                        <div>
                            Já possui uma conta?
                        </div>
                        <div className="cadastro-login" onClick={this.onClickLinkLogin}>
                            Faça o login
                        </div>
                    </div>
        </div>)
    }
}