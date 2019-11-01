import React from 'react'
import './PacienteCard.css'
import MedicoService from '../../Services/MedicoService'

export default class PacienteCard extends React.Component{
    constructor(){
        super()
        this.state = {
            vinculado: false
        }
        this.onClickVincular = this.onClickVincular.bind(this)
    }

    onClickVincular(){
        MedicoService.vincularPaciente(this.props.email)
        .then((result) => {
            this.setState({
                vinculado: true
            })
        }).catch((err) => {
            this.setState({
                error: err.response.data.message
            })
        })
    }

    renderVinculado(){
        if(this.props.tipoPaciente === 'vincular-pacientes'){
            if(this.state.vinculado === true){
                return <div className="pacienteCard-button green">Vinculado</div>
            }else{
                return <div className="pacienteCard-button blue" onClick={this.onClickVincular}>+ Vincular</div>
            }
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
                <div className="pacienteCard-nome">{this.props.nome+" "+this.props.sobrenome}</div>
                <div className="pacienteCard-outro">Telefone: {this.props.telefone}</div>
                <div className="pacienteCard-outro">Estágio: {this.renderEstagio()}</div>
            </div>
            {this.renderVinculado()}  
        </div>)
    }
}