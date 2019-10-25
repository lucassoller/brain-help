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

export default class InformacoesPessoais extends React.Component{
    constructor(){
        super()
        this.state = {
            nome: '',
            sobrenome: '',
            email: '',
            senha: '',
            confirmar: '',
            telefone: '',
            especializacao: '',
            logradouro: '',
            numero: '',
            cidade: '',
            estado: '',
            bairro: '',
            cep: '',
            google: false,
            selectedContent: SELECTED_CONTENTS.CADASTRO
        },
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

    onClickLinkCadastrar(){
        const account = this.state
        if(account.senha !== account.confirmar){
            this.setState({
                error: 'As senhas não coincidem'
            })
        }else{
            CadastroService
            .register(account.nome, account.sobrenome, account.email, account.senha, account.telefone,
                account.especializacao, account.logradouro, account.numero, account.cidade, 
                account.estado, account.bairro, account.cep, account.google)
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

    render(){
        return(<div>

        </div>)
    }
}