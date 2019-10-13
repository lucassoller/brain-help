import React from 'react'
import './TelaInicial.css'
import {Redirect} from 'react-router-dom'
import Google from '../Google'

const SELECTED_CONTENTS = {
    LOGIN: 'LOGIN',
    REGISTER: 'REGISTER',
    HOME: 'HOME',
    INICIAL: 'INICIAL'

}

export default class TelaInicial extends React.Component{
    
    constructor() {
        super()
        this.state = {
            selectedContent: SELECTED_CONTENTS.INICIAL
        }
        this.onClickLinkLogin = this.onClickLinkLogin.bind(this)
        this.onClickLinkCadastro = this.onClickLinkCadastro.bind(this)
    }

    onClickLinkCadastro() {
        this.setSelectedContent(SELECTED_CONTENTS.REGISTER)
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
        if( this.state.selectedContent === SELECTED_CONTENTS.REGISTER){
            return <Redirect to='/registro' />
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