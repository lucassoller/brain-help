import React from 'react'
import './PacienteCard.css'

export default class PacienteCard extends React.Component{

    renderVinculado(){
        if(this.props.tipoPaciente !== 'meu'){
            return <div className="pacienteCard-button blue">+ Vincular</div>
        }
    }

    renderEstagio(){
       if(this.props.estagio === 'INICIAL'){
            return 'Inicial'
       }else if(this.props.estagio === 'INTERMEDIARIO'){
            return 'Intermediário'
       }else{
            return 'Avançado'
       }
    }
    
    render(){
        return(<div className="pacienteCard-container">
            <div className="pacienteCard-foto"></div>
            <div className="pacienteCard-info">
                <div className="pacienteCard-nome">{this.props.nome}</div>
                <div className="pacienteCard-outro">Telefone: {this.props.telefone}</div>
                <div className="pacienteCard-outro">Estágio: {this.renderEstagio()}</div>
            </div>
            {this.renderVinculado()}  
        </div>)
    }
}