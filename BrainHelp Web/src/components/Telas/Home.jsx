import React from 'react'
import Alert from '../generic/Alert/Alert'
import CadastroService from '../../Services/CadastroService'
import LoginService from '../../Services/LoginService'
import './Home.css'
import {Redirect} from 'react-router-dom'
import $ from 'jquery'
export default class Home extends React.Component{

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
                            />
                            <div className="home-search-icon"></div>
                        </div>
                        <div className="home-pacientes"></div>
                    </div>
                    <div className="home-content-side home-abc"></div>
                </div>
            </div>
        )
    }
}