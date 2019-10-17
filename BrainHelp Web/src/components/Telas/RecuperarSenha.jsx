import React from 'react'
import './RecuperarSenha.css'
import {Redirect} from 'react-router-dom'
import $ from 'jquery'

const SELECTED_CONTENTS = {
    LOGIN: 'LOGIN',
    CADASTROINICIAL: 'CADASTROINICIAL',
    HOME: 'HOME',
    RECUPERARSENHA: 'RECUPERARSENHA'

}

export default class RecuperarSenha extends React.Component{

    constructor() {
        super()
        this.state = {
            senha: '',
            confirmar: '',
            error: '',
            selectedContent: SELECTED_CONTENTS.RECUPERARSENHA
        }

        this.handdleChange = this.handdleChange.bind(this)
        this.onShowOver = this.onShowOver.bind(this)
        this.onShowOut = this.onShowOut.bind(this)
        this.onShowOverConfirmacao = this.onShowOverConfirmacao.bind(this)
        this.onShowOutConfirmacao = this.onShowOutConfirmacao.bind(this)
    }

    handdleChange(event) {
        const target = event.target
        const value = target.value
        const name = target.name
        this.setState({
            [name]: value
        })
    }

    onShowOver(){
        $(".recuperar-senha").attr("type", "text");
    }

    onShowOut(){
        $(".recuperar-senha").attr("type", "password");
    }

    onShowOverConfirmacao(){
        $(".recuperar-confirmar").attr("type", "text");
    }

    onShowOutConfirmacao(){
        $(".recuperar-confirmar").attr("type", "password");
    }

    render(){
        return(
            <div className="recuperar-container">
                <div className="recuperar-content">
                    <div className="recuperar-titulo">Redefina a sua senha</div>
                    <div className="recuperar-subtitulo">Defina uma nova senha para sua conta</div>
                    <div className="recuperar-input">
                        <div className="recuperar-forms">
                            <input 
                                type="password"
                                placeholder="Nova senha"
                                className="recuperar-form recuperar-senha"/>
                            <div className="recuperar-eye" onMouseOver={this.onShowOver} onMouseOut={this.onShowOut}></div>
                        </div>
                        <div div className="recuperar-forms">
                            <input 
                                type="password"
                                placeholder="Confirmar nova senha"
                                className="recuperar-form recuperar-confirmar"/>
                            <div className="recuperar-eye" onMouseOver={this.onShowOverConfirmacao} onMouseOut={this.onShowOutConfirmacao}></div>
                        </div>
                    </div>
                    <div className="recuperar-button">Definir senha</div>
                </div>
            </div>
            
        )
    }
}