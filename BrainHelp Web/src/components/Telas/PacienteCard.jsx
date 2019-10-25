import React from 'react'
import './PacienteCard.css'
import {Redirect} from 'react-router-dom'
import LoginService from '../../Services/LoginService'

export default class PacienteCard extends React.Component{

    renderVinculado(){
        if(this.props.emailMedico === LoginService.getUserName()){
            return <div className="pacienteCard-button green">Vinculado</div>
        }else{
            return <div className="pacienteCard-button blue">+ Vincular</div>
        }
    }
    
    render(){
        return(<div className="pacienteCard-container">
            <div className="pacienteCard-foto"></div>
            <div className="pacienteCard-info">
                <div className="pacienteCard-nome">{this.props.nome}</div>
                <div className="pacienteCard-outro">Telefone: {this.props.telefone}</div>
                <div className="pacienteCard-outro">Estagio: {this.props.estagio}</div>
                
            </div>
            {this.renderVinculado()}
            
        </div>)
    }
}