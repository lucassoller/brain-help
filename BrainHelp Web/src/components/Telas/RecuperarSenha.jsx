import React from 'react'
import './RecuperarSenha.css'
import $ from 'jquery'
import EmailService from '../../Services/EmailService'
import CadastroService from '../../Services/CadastroService'

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
            url:'',
            email:'',
            error: '',
            selectedContent: SELECTED_CONTENTS.RECUPERARSENHA
        }

        this.handdleChange = this.handdleChange.bind(this)
        this.onClickLinkDefinirSenha = this.onClickLinkDefinirSenha.bind(this)
    }

    componentDidMount(){
        this.redefinirSenha()
    }

    redefinirSenha(){
        var url = this.props.match.params.url
        var email = url.split(":")[0]
        EmailService.redefinirSenha(url)
        .then((result) => {
            this.setState({
                email: email,
                url: url
            })
        }).catch((err) => {
            this.setState({
                error: err.response.data.message
            })
        })
        
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
            console.log("Não coincidem")
        }else{
            CadastroService.editar(this.state.email, this.state.senha)
            .then((result) => {
            }).catch((err) => {
                this.setState({
                    error: err.response.data.message
                })
            })
        }
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
                                className="recuperar-form recuperar-senha"
                                name="senha"
                                value={this.state.senha}
                                onChange={this.handdleChange}/>
                            <div className="recuperar-eye" onMouseOver={this.onShowOver} onMouseOut={this.onShowOut}></div>
                        </div>
                        <div div className="recuperar-forms">
                            <input 
                                type="password"
                                placeholder="Confirmar nova senha"
                                className="recuperar-form recuperar-confirmar"
                                name="confirmar"
                                value={this.state.confirmar}
                                onChange={this.handdleChange}/>
                            <div className="recuperar-eye" onMouseOver={this.onShowOverConfirmacao} onMouseOut={this.onShowOutConfirmacao}></div>
                        </div>
                    </div>
                    <div className="recuperar-button" onClick={this.onClickLinkDefinirSenha}>Definir senha</div>
                </div>
            </div>
            
        )
    }
}