import React from 'react'
import './Email.css'
import {Redirect} from 'react-router-dom'

const SELECTED_CONTENTS = {
    TELAINICIAL: 'TELAINICIAL',
    RECUPERARSENHA: 'RECUPERARSENHA',
    EMAIL: 'EMAIL'

}

export default class Email extends React.Component{
    constructor() {
        super()
        this.state = {
            selectedContent: SELECTED_CONTENTS.EMAIL
        }
        this.onClickLinkLogo = this.onClickLinkLogo.bind(this)
        this.onClickLinkRedefinirSenha = this.onClickLinkRedefinirSenha.bind(this)
    }
    onClickLinkLogo(){
        this.setState({
            selectedContent: SELECTED_CONTENTS.TELAINICIAL
        })
    }

    onClickLinkRedefinirSenha(){
        this.setState({
            selectedContent: SELECTED_CONTENTS.RECUPERARSENHA
        })
    }
    render(){
        if(this.state.selectedContent === SELECTED_CONTENTS.TELAINICIAL){
            return <Redirect to='/' />
        }

        if(this.state.selectedContent === SELECTED_CONTENTS.RECUPERARSENHA){
            return <Redirect to='/recuperar' />
        }
        return(
            <div className="email-container">
                    
                <div className="email-header">
                    <div className="email-logo-back"></div>
                    <div className="email-titulo">Brain Help</div>
                    <div className="email-logo-back">
                        <div className="email-logo" onClick={this.onClickLinkLogo}></div>
                    </div>       
                </div>
                <div className="email-content">
                    <div className="email-pergunta">Redefinir sua senha?</div>
                    <div className="email-texto">Se você solicitou uma redefinição de senha, clique no botão abaixo. Se você não fez essa solicitação, ignore esse e-mail.</div>
                    <div className="email-botao" onClick={this.onClickLinkRedefinirSenha}>Redefinir senha</div>
                </div>
                <div className="email-image">
                    <div className="email-imagem"></div>
                </div>
            </div>
        )
    }
}