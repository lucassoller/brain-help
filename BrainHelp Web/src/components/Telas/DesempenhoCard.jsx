import React from 'react'
import './PacienteCard.css'

export default class DesempenhoCard extends React.Component{
    render(){
        return(<div className="pacienteCard-container">
            <div className="pacienteCard-info">
                <div className="pacienteCard-nome">Atividade: {this.props.atividade}</div>
                <div className="pacienteCard-outro">Pontuação: {this.props.pontuacao}</div>
                <div className="pacienteCard-outro">Desempenho: {this.props.desempenho}</div>
                <div className="pacienteCard-outro">Data danrealização: {this.props.dataRealizacao}</div>
            </div> 
        </div>)
    }
}