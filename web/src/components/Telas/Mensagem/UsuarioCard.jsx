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
                    <div className="MinhaMensagem--content">
                        <div className="MinhaMensagem--header">
                            <div className="nome" onClick={this.props.onClickMostrarUsuario} >{this.props.nome}</div>
                        </div>
                        <div className="usuario">@{this.props.usuario}</div>
                    </div>        
                </div>
            </div>)
    }
}