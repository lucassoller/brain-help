import React from 'react'
import Button from '../../generic/Button/Button'
import './MensagemCard.css'
import FormatarData from '../../../FormatarData'
export default class MensagemCardCard extends React.Component{

    renderConteudo(){
        if(this.props.conteudo.length >= 112){
            return this.props.conteudo.substring(0,109)+"...";
        }else{
            return this.props.conteudo;
        }
    }

    render() {
        return (
            <div className="MinhaMensagem">
                <div className="MinhaMensagemCard">
                    <div className="foto">
                        <h2>{this.props.usuario.substring(0,1)}</h2>
                    </div>
                    <div className="MinhaMensagem--content">
                        <div className="MinhaMensagem--header">
                            <div className="tipo">{this.props.tipo}</div>
                            <div className="nome" onClick={this.props.onClickMostrarUsuario} >{this.props.nome}</div>
                            <div className="data">{FormatarData.formatar(this.props.dataDeEnvio)}</div>
                        </div>
                        <div className="usuario">@{this.props.usuario}</div>
                        <div className="conteudo">{this.renderConteudo()}</div>
                    </div>        
                </div>
            </div>)
    }
}