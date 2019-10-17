import React from 'react'
import './EsquecerSenha.css'
import {Redirect} from 'react-router-dom'
import $ from 'jquery'

const SELECTED_CONTENTS = {
    LOGIN: 'LOGIN',
    ESQUECERSENHA: 'ESQUECERSENHA'
}

export default class EsquecerSenha extends React.Component{

    constructor() {
        super()
        this.state = {
            email: '',
            error: '',
            selectedContent: SELECTED_CONTENTS.ESQUECERSENHA
        }

        this.handdleChange = this.handdleChange.bind(this)
        this.onClickLinkLogin = this.onClickLinkLogin.bind(this)
    }

    handdleChange(event) {
        const target = event.target
        const value = target.value
        const name = target.name
        this.setState({
            [name]: value
        })
    }

    onClickLinkLogin(){
        this.setSelectedContent(SELECTED_CONTENTS.LOGIN)
    }

    setSelectedContent(content) {
        this.setState({
            selectedContent: content
        })
    }

    render(){
        if(this.state.selectedContent === SELECTED_CONTENTS.LOGIN){
            return <Redirect to='/login' />
        }
        
        return(
            <div className="esquecer-container">
                <div className="esquecer-content">
                    <div className="esquecer-titulo">Esqueceu sua senha?</div>
                    <div className="esquecer-subtitulo">Digite seu email e uma mensagem será enviada para você redefinir sua senha</div>
                    <div className="esquecer-input">
                        <input 
                            type="text"
                            placeholder="Email"
                            className="esquecer-form"/>
                    </div>
                    <div className="esquecer-button">Enviar</div>
                    <div className="esquecer-footer">
                        <div className="esquecer-login" onClick={this.onClickLinkLogin}>Voltar a página de login</div>
                        <div className="esquecer-footer-end"></div>
                    </div>
                </div>
            </div>
            
        )
    }
}