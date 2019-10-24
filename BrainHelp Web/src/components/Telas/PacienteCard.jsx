import React from 'react'
import './PacienteCard.css'
import {Redirect} from 'react-router-dom'

export default class PacienteCard extends React.Component{
    
    render(){
        return(<div className="pacienteCard-container">
            <div className="pacienteCard-foto"></div>
            <div className="pacienteCard-info">
                <div className="pacienteCard-nome">{this.props.nome}</div>
                <div className="pacienteCard-outro">{this.props.telefone}</div>
                <div className="pacienteCard-outro">{this.props.estagio}</div>
            </div>
            <div className="pacienteCard-button">Vinculado</div>
        </div>)
    }
}