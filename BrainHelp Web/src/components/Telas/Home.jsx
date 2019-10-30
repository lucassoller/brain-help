import React from 'react'
import './Home.css'
import Paciente from './Paciente'
import $ from 'jquery'
export default class Home extends React.Component{

    constructor(){
        super()
        this.state = {
            nome: '',
            tipoPaciente: 'outros'
        }
        this.handdleChange = this.handdleChange.bind(this)
    }

    handdleChange(event) {  
        const target = event.target
        const value = target.value
        this.setState({
            nome: value
        })
    }

    renderPacientes(){
        if(this.state.nome !== ''){
            var v = $(".home-form").val();
            console.log(v)
            return <Paciente 
                    tipoPaciente = {this.state.tipoPaciente}
                    nome = {v}           
                />
        }
    }

    renderTipo(){
        if(this.state.tipoPaciente === 'meu'){
            return 'Meus pacientes'
        }else{
            return 'Vincular pacientes'
        }
    }

    render(){
        return (<div className="home-container">
                <div className="home-navbar">
                    <div className="home-menu"></div>
                    <div className="home-center">
                        <div className="home-logo"></div>
                        <div className="home-titulo">
                            Brain Help Web
                        </div>
                    </div>
                    <div className="home-perfil"></div>
                </div>
                <div className="home-content">
                    <div className="home-content-side"></div>
                    <div className="home-users">
                        <div className="home-tipo">
                            {this.renderTipo()}
                        </div>
                        <div className="home-search">
                            <input 
                                type="text"
                                className="home-form"
                                placeholder="Buscar pacientes"
                                name="nome"
                                value={this.state.nome}
                                onChange={this.handdleChange}
                            />
                            <div className="home-search-icon"></div>
                        </div>
                        <div className="home-pacientes">
                            {this.renderPacientes()}
                        </div>
                    </div>
                    <div className="home-content-side home-abc"></div>
                </div>
            </div>
        )
    }
}