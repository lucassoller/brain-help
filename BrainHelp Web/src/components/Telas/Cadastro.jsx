import React from 'react'
import Alert from '../generic/Alert/Alert'
import CadastroService from '../../Services/CadastroService'
import LoginService from '../../Services/LoginService'
import './Cadastro.css'
import {Redirect} from 'react-router-dom'
import $ from 'jquery'
import './Cadastro.css'

const SELECTED_CONTENTS = {
    LOGIN: 'LOGIN',
    CADASTRO: 'CADASTRO',
    CADASTROINICIAL: 'CADASTROINICIAL',
    HOME: 'HOME'
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
            disabled: false,
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

    componentDidMount(){
        this.setState({
            senha: this.props.senha,
            confirmar: this.props.senha,
            email: this.props.email,
            nome: this.props.nome,
            sobrenome: this.props.sobrenome,
            google: this.props.google,
            disabled: this.props.disabled
        })
        this.setColor()
    }

    setColor(){
        if(this.props.disabled === true){
            $(".info-disabled").css("background", "rgb(233, 232, 232)");
            $(".info-disabled").css("color", "rgb(78, 76, 76)");
        }
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
            var endereco = {logradouro: account.logradouro, numero: account.numero, cidade: account.cidade,
            estado: account.estado, bairro: account.bairro, cep: account.cep};
            CadastroService
            .register(account.nome, account.sobrenome, account.email, account.senha,
                account.google, account.telefone, endereco, account.especializacao)
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
            $(".info-senha-eye").attr("type", "text");
        }
    }

    onShowOut(){
        $(".info-senha-eye").attr("type", "password");
    }

    onShowOverConfirmacao(){
        if(this.props.disabled === false){
            $(".info-confirmar-eye").attr("type", "text");
        }
    }

    onShowOutConfirmacao(){
        $(".info-confirmar-eye").attr("type", "password");
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
        if( this.state.selectedContent === SELECTED_CONTENTS.HOME){
            return <Redirect to='/home' />
        }

        if( this.state.selectedContent === SELECTED_CONTENTS.LOGIN){
            return <Redirect to='/login' />
        }
        return(<div className="info-container">
                <div className="info-navbar">
                    <div className="info-logo"></div>
                    <div className="info-center">
                        <div className="info-titulo">
                            Informações Pessoais
                        </div>
                    </div>
                    <div></div>
                </div>
                <div className="info-content">
                    <div className="info-form">
                        <div className="info-foto">
                            Foto
                        </div>
                        <div className="info-form-fields">
                            <div className="info-combo">
                                <input 
                                    type="text"
                                    placeholder="Nome"
                                    name="nome"
                                    value={this.state.nome}
                                    onChange={this.handdleChange}
                                    className="info-input"          
                                />

                                <input 
                                    type="text"
                                    placeholder="Sobrenome"
                                    name="sobrenome"
                                    value={this.state.sobrenome}
                                    onChange={this.handdleChange}
                                    className="info-input"
                                />
                            </div>
                            <input 
                                type="text"
                                placeholder="Email"
                                className="complete info-input info-disabled"
                                name="email"
                                value={this.state.email}
                                onChange={this.handdleChange}
                                disabled={this.props.disabled}
                            />
                            <div className="info-combo">
                                <div className="info-form-senha info-disabled">
                                    <input
                                        type="password"
                                        className="info-senha info-senha-eye info-input info-disabled"
                                        name="senha"
                                        placeholder="Senha"
                                        value={this.state.senha}
                                        onChange={this.handdleChange}
                                        disabled={this.props.disabled}
                                    />
                                    <div className="info-eye" onMouseOver={this.onShowOver} onMouseOut={this.onShowOut}></div>
                                </div>
                                <div className="info-form-senha info-disabled">
                                    <input
                                        type="password"
                                        className="info-senha info-confirmar-eye info-input info-disabled"                                       
                                        placeholder="Confirmar senha"
                                        name="confirmar"
                                        value={this.state.confirmar}
                                        onChange={this.handdleChange}
                                        disabled={this.props.disabled}
                                    />
                                    <div className="info-eye" onMouseOver={this.onShowOverConfirmacao} onMouseOut={this.onShowOutConfirmacao}></div>
                                </div>
                            </div>
                            <div className="info-label">Telefone</div>
                            <div className="info-combo">
                                <input 
                                    type="text"
                                    placeholder="( ) 9 9999-9999"
                                    name="telefone"
                                    value={this.state.telefone}
                                    onChange={this.handdleChange}
                                    className="info-input"
                                />

                                <input 
                                    type="text"
                                    placeholder="Especialização"
                                    name="especializacao"
                                    value={this.state.especializacao}
                                    onChange={this.handdleChange}
                                    className="info-input"
                                />
                            </div>
                            <div className="info-label">Local de trabalho</div>
                            <div className="info-combo">
                                <input 
                                    type="text"
                                    placeholder="Logradouro"
                                    className="info-logradouro info-input"
                                    name="logradouro"
                                    value={this.state.logradouro}
                                    onChange={this.handdleChange}
                                />

                                <input 
                                    type="number"
                                    placeholder="Número"
                                    className="info-numero info-input"
                                    name="numero"
                                    value={this.state.numero}
                                    onChange={this.handdleChange}
                                />
                            </div>
                            <div className="info-combo">
                                <input 
                                    type="text"
                                    placeholder="Estado"
                                    name="estado"
                                    value={this.state.estado}
                                    onChange={this.handdleChange}
                                    className="info-input"
                                />

                                <input 
                                    type="text"
                                    placeholder="Cidade"
                                    name="cidade"
                                    value={this.state.cidade}
                                    onChange={this.handdleChange}
                                    className="info-input"
                                />
                            </div>
                            <div className="info-combo">
                                <input 
                                    type="text"
                                    placeholder="Bairro"
                                    name="bairro"
                                    value={this.state.bairro}
                                    onChange={this.handdleChange}
                                    className="info-input"
                                />

                                <input 
                                    type="text"
                                    placeholder="CEP"
                                    name="cep"
                                    value={this.state.cep}
                                    onChange={this.handdleChange}
                                    className="info-input"
                                />
                            </div>
                            <div className="info-form-footer">
                                <div className="info-footer">
                                    <div>
                                        Já possui uma conta?
                                    </div>
                                    <div className="info-login" onClick={this.onClickLinkLogin}>
                                        Faça o login
                                    </div>
                                </div>
                                <div className="info-button" onClick={this.onClickLinkCadastrar}>
                                    Salvar
                                </div>
                            </div>                          
                        </div>                       
                    </div> 
                </div>
        </div>)
    }
}