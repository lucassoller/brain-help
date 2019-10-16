import React from 'react'
import './PacienteCard.css'
import {Redirect} from 'react-router-dom'

export default class PacienteCard extends React.Component{
    
    render(){
        return(<div className="pacienteCard-container">
            <div className="pacienteCard-foto"></div>
            <div className="pacienteCard-info">
                <div className="pacienteCard-nome">Fulana da Silva Sauro</div>
                <div className="pacienteCard-outro">Telefone: (51) 99613-9667</div>
                <div className="pacienteCard-outro">Estágio: Morto</div>
            </div>
            <div className="pacienteCard-button">Vinculado</div>
        </div>)
    }
}