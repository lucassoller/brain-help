import React from 'react'
import './PacienteCard.css'
import MedicoService from '../../Services/MedicoService'
import $ from 'jquery'
import {Redirect} from 'react-router-dom'

const SELECTED_CONTENTS = {
    MEUSPACIENTES: 'MEUSPACIENTES',
    PACIENTE: 'PACIENTE'
}

export default class PacienteCard extends React.Component{
    constructor(){
        super()
        this.state = {
            vinculado: false,
            selectedContent: SELECTED_CONTENTS.MEUSPACIENTES
        }
        this.onClickVincular = this.onClickVincular.bind(this)
        this.onClickCard = this.onClickCard.bind(this)
    }

    onClickCard(){
        if(this.props.tipoPaciente === 'meus-pacientes'){
            this.setSelectedContent(SELECTED_CONTENTS.PACIENTE)
        }
    }

    setSelectedContent(content) {
        this.setState({
            selectedContent: content
        })
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

    componentDidMount(){
        if(this.props.tipoPaciente === 'meus-pacientes'){
            $(".pacienteCard-container").css("cursor", "pointer");
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
        if(this.state.selectedContent === SELECTED_CONTENTS.PACIENTE){
            window.location.reload(false);
            return <Redirect to = {'/home/paciente/'+this.props.email}/>
        }
        return(<div className="pacienteCard-container" onClick={this.onClickCard}>
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