import React from 'react'
import Alert from '../generic/Alert/Alert'
import CadastroService from '../../Services/CadastroService'
import LoginService from '../../Services/LoginService'
import './Home.css'
import {Redirect} from 'react-router-dom'
import $ from 'jquery'
import Paciente from './Paciente'
export default class Home extends React.Component{

    constructor(){
        super()
        this.state = {
            nome: '',
            tipoPaciente: 'meu'
        },
        this.handdleChange = this.handdleChange.bind(this)
        this.onClickLinkSearch = this.onClickLinkSearch.bind(this)
    }

     handdleChange(event) {
        const target = event.target
        const value = target.value
        const name = target.name
        this.setState({
            [name]: value
        })
    }

    onClickLinkSearch(){
        this.setState({
            render: true
        })
    }

    renderPacientes(){
        if(this.state.nome !== ''){
            return <Paciente 
                    tipo = {this.state.tipoPaciente}
                    nome = {this.state.nome}
                />
        }
    }

    renderTipo(){
        if(this.state.tipoPaciente){
            return 'Meus pacientes'
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
                            <div className="home-search-icon" onClick={this.onClickLinkSearch}></div>
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