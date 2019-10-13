import React from 'react'
import Input from '../generic/Input/Input'
import Button from '../generic/Button/Button'
import Alert from '../generic/Alert/Alert'
import RegisterService from '../../Services/RegisterService'
import LoginService from '../../Services/LoginService'
import './RegisterForm.css'
import '../../global.css'
import {Redirect} from 'react-router-dom'
import Selected from '../../SelectedContents'

const SELECTED_CONTENTS = {
    LOGIN: 'LOGIN',
    REGISTER: 'REGISTER',
    HOME: 'HOME'
}

export default class RegisterForm extends React.Component {
    constructor() {
        super()
        this.state = this.getInitialState(),{
            selectedContent: Selected.REGISTER
        }
        this.handdleChange = this.handdleChange.bind(this)
        this.onClickRegisterButton = this.onClickRegisterButton.bind(this)
        this.onClickLinkLogin = this.onClickLinkLogin.bind(this)
    }

    setSelectedContent(content) {
        this.setState({
            selectedContent: content
        })
    }

    getInitialState() {
        return {
            codMedico:'',
            nome:'',
            sobrenome:'',
            email:'',
            senha: '',
            confirmarSenha: '',
            localTrabalho:'',
            especializacao:'',
            error: '',
            registered:''
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
    
    renderRegisterSuccess() {
        return this.state.registered ? <Alert alertType="primary" text={this.state.registered} /> : undefined
    }

    onClickLinkLogin(){
        this.setSelectedContent(SELECTED_CONTENTS.LOGIN)
    }
    onClickRegisterButton() {
        const account = this.state
        if(account.senha !== account.confirmarSenha){
            this.setState({
                error: 'As senhas não coincidem'
            })
        }else{
            RegisterService
            .register(account.codMedico, account.nome, account.sobrenome, account.email,
                account.senha, account.localTrabalho, account.especializacao)
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

    success(){
        this.setState({
            registered: "Cadastro realizado com sucesso, faça o login"
        })
    }

    renderError() {
        return this.state.error ? <Alert text={this.state.error} alertType="danger" /> : undefined
    }

    render() {
        if(this.state.selectedContent === SELECTED_CONTENTS.LOGIN){
            <Redirect to='/' />
        }
        if( this.state.selectedContent === SELECTED_CONTENTS.HOME){
            return <Redirect to='/home' />
        }

        return (
            <div className="Register">
            <div className="Register--container col-md-5">
                <div className="Register--header">
                <h3>Por Favor, faça o  <span className="Register--link" onClick={this.onClickLinkLogin}>Login</span>, ou <span className="Register--link">Cadastre-se</span></h3>
                </div>
                <div className="App-content">
                    <div>
                        {this.renderRegisterSuccess()}
                        {this.renderError()}
                        <Input
                            label="Código de identificação"
                            value={this.state.codMedico}
                            name="codMedico"
                            placeholder="Digite o código de identificação"
                            handdleChange={this.handdleChange}
                            type="text"
                        />
                        <Input
                            label="Nome"
                            value={this.state.nome}
                            name="nome"
                            placeholder="Digite o nome"
                            handdleChange={this.handdleChange}
                            type="text"
                        />
                        <Input
                            label="Sobrenome"
                            value={this.state.sobrenome}
                            name="sobrenome"
                            placeholder="Digite o sobrenome"
                            handdleChange={this.handdleChange}
                            type="text"
                        />
                        <Input
                            label="E-mail"
                            value={this.state.email}
                            name="email"
                            placeholder="Digite o e-mail"
                            handdleChange={this.handdleChange}
                            type="text"
                        />
                        <Input
                            label="Senha"
                            value={this.state.senha}
                            name="senha"
                            placeholder="Digite sua senha"
                            handdleChange={this.handdleChange}
                            type="password"
                        />
                        <Input
                            label="Confirmar senha"
                            value={this.state.confirmarSenha}
                            name="confirmarSenha"
                            placeholder="Confirme sua senha"
                            handdleChange={this.handdleChange}
                            type="password"
                        />
                        <Input
                            label="Local de trabalho"
                            value={this.state.localTrabalho}
                            name="localTrabalho"
                            placeholder="Digite o local de trabalho"
                            handdleChange={this.handdleChange}
                            type="text"
                        />
                        <Input
                            label="Especialização"
                            value={this.state.especializacao}
                            name="especializacao"
                            placeholder="Digite a especializacao"
                            handdleChange={this.handdleChange}
                            type="text"
                        />
                        <Button type="button"
                            text="Registrar"
                            onClick={this.onClickRegisterButton}
                            classButton="primary" />
                    </div>
                </div>
            </div>
        </div>

        )
    }
}