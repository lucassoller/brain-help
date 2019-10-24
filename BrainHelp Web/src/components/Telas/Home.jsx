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
            render: true
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
        if(this.state.nome !== '' && this.state.render === true){
            return <Paciente 
                    nome = {this.state.nome}
                />
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