import React from 'react'
import './TelaInicial.css'
import {Redirect} from 'react-router-dom'

const SELECTED_CONTENTS = {
    LOGIN: 'LOGIN',
    CADASTROINICIAL: 'CADASTROINICIAL',
    HOME: 'HOME',
    TELAINICIAL: 'TELAINICIAL'

}

export default class TelaInicial extends React.Component{
    
    constructor() {
        super()
        this.state = {
            selectedContent: SELECTED_CONTENTS.TELAINICIAL
        }
        this.onClickLinkLogin = this.onClickLinkLogin.bind(this)
        this.onClickLinkCadastro = this.onClickLinkCadastro.bind(this)
    }

    onClickLinkCadastro() {
        this.setSelectedContent(SELECTED_CONTENTS.CADASTROINICIAL)
    }

    onClickLinkLogin() {
        this.setSelectedContent(SELECTED_CONTENTS.LOGIN)
    }

    setSelectedContent(content) {
        this.setState({
            selectedContent: content
        })
    }

    render() {
        if( this.state.selectedContent === SELECTED_CONTENTS.CADASTROINICIAL){
            return <Redirect to='/cadastro-inicial' />
        }

        if( this.state.selectedContent === SELECTED_CONTENTS.LOGIN){
            return <Redirect to='/login' />
        }

        return (
            <div className="container">
                <div className="imagem">

                </div>  
                <div className="content">
                    <div className="logo">

                    </div>

                    <div className="navigator">
                        <div className="title">
                            <h1>Seja Bem-Vindo ao Brain Help!</h1>
                        </div>
                        <div className="buttons">
                            <div className="entrar botao" onClick={this.onClickLinkLogin}>
                                    Entrar
                            </div>
                            <div className="cadastrar botao" onClick={this.onClickLinkCadastro}>
                                    Cadastrar
                            </div>
                        </div>
                    </div>
                </div>
                
            </div>)
    }
}