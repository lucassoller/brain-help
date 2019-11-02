import React from 'react'
import './AlterarSenha.css'
import $ from 'jquery'
import MedicoService from '../../Services/MedicoService'
import LoginService from '../../Services/LoginService'

const SELECTED_CONTENTS = {
    LOGIN: 'LOGIN',
    CADASTROINICIAL: 'CADASTROINICIAL',
    HOME: 'HOME',
    ALTERARSENHA: 'ALTERARSENHA'

}

export default class AlterarSenha extends React.Component{

    constructor() {
        super()
        this.state = {
            senha: '',
            confirmar: '',
            error: '',
            selectedContent: SELECTED_CONTENTS.ALTERARSENHA
        }

        this.handdleChange = this.handdleChange.bind(this)
        this.onClickLinkDefinirSenha = this.onClickLinkDefinirSenha.bind(this)
    }

    handdleChange(event) {
        const target = event.target
        const value = target.value
        const name = target.name
        this.setState({
            [name]: value
        })
    }

    onClickLinkDefinirSenha(){
        if(this.state.senha !== this.state.confirmar){
            this.setState({
                error: "As senhas não coincidem"
            })
        }else{
            MedicoService.alterarSenha(LoginService.getUserName(), this.state.senha)
            .then((result) => {
            }).catch((err) => {
                this.setState({
                    error: err.response.data.message
                })
            })
        }
    }

    onShowOver(){
        $(".alterar-senha").attr("type", "text");
    }

    onShowOut(){
        $(".alterar-senha").attr("type", "password");
    }

    onShowOverConfirmacao(){
        $(".alterar-confirmar").attr("type", "text");
    }

    onShowOutConfirmacao(){
        $(".alterar-confirmar").attr("type", "password");
    }

    render(){
        return(
            <div className="alterar-container">
                <div className="alterar-content">
                    <div className="alterar-titulo">Redefina a sua senha</div>
                    <div className="alterar-subtitulo">Defina uma nova senha para sua conta</div>
                    <div className="alterar-input">
                        <div className="alterar-forms">
                            <input 
                                type="password"
                                placeholder="Nova senha"
                                className="alterar-form alterar-senha"
                                name="senha"
                                value={this.state.senha}
                                onChange={this.handdleChange}/>
                            <div className="alterar-eye" onMouseOver={this.onShowOver} onMouseOut={this.onShowOut}></div>
                        </div>
                        <div div className="alterar-forms">
                            <input 
                                type="password"
                                placeholder="Confirmar nova senha"
                                className="alterar-form alterar-confirmar"
                                name="confirmar"
                                value={this.state.confirmar}
                                onChange={this.handdleChange}/>
                            <div className="alterar-eye" onMouseOver={this.onShowOverConfirmacao} onMouseOut={this.onShowOutConfirmacao}></div>
                        </div>
                    </div>
                    <div className="alterar-button" onClick={this.onClickLinkDefinirSenha}>Alterar senha</div>
                </div>
            </div>
            
        )
    }
}