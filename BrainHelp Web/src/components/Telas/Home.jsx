import React from 'react'
import './Home.css'
import Paciente from './Paciente'
import {Redirect} from 'react-router-dom'

const SELECTED_CONTENTS = {
    MEUSPACIENTES: 'MEUSPACIENTES',
    VINCULARPACIENTES: 'VINCULARPACINTES'
}
export default class Home extends React.Component{

    constructor(){
        super()
        this.state = {
            nome: '',
            tipoPaciente: '',
            selectedContent: SELECTED_CONTENTS.LOGIN,
        }
        this.handdleChange = this.handdleChange.bind(this)
        this.closeNav = this.closeNav.bind(this)
        this.openNav = this.openNav.bind(this)
        this.close = this.close.bind(this)
        this.open = this.open.bind(this)
        this.onClickMeusPacientes = this.onClickMeusPacientes.bind(this)
        this.onClickVincularPacientes = this.onClickVincularPacientes.bind(this)
        this.baseState = this.state 
    }

    onClickMeusPacientes(){
        this.setSelectedContent(SELECTED_CONTENTS.MEUSPACIENTES)
    }

    onClickVincularPacientes(){
        this.setSelectedContent(SELECTED_CONTENTS.VINCULARPACIENTES)
    }

    setSelectedContent(content) {
        this.setState({
            selectedContent: content
        })
    }

    componentDidMount(){
        var url = window.location.href
        url = url.split("/")
        var tipoPaciente = url[url.length-1]
        this.setState({
            tipoPaciente: tipoPaciente
        })
    }

    closeNav(){
        document.getElementById("mySidenav").style.width = "0";
      }
    
      openNav(){
          document.getElementById("mySidenav").style.width = "250px";
      }

      close(){
        document.getElementById("mySidenav2").style.width = "0";
      }
    
      open(){
        document.getElementById("mySidenav2").style.width = "250px";
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
            // var v = $(".home-form").val();
            return <Paciente 
                    tipoPaciente = {this.state.tipoPaciente}
                    nome = {this.state.nome}           
                />
        }
    }

    renderTipo(){
        if(this.state.tipoPaciente === 'meus-pacientes'){
            return 'Meus pacientes'
        }else if(this.state.tipoPaciente === 'vincular-pacientes'){
            return 'Vincular pacientes'
        }
    }

    render(){
        if( this.state.selectedContent === SELECTED_CONTENTS.MEUSPACIENTES){
            window.location.reload(false);
            return <Redirect to='/home/meus-pacientes' />
        }

        if( this.state.selectedContent === SELECTED_CONTENTS.VINCULARPACIENTES){
            window.location.reload(false);
            return <Redirect to='/home/vincular-pacientes' />
        }
        return (<div className="home-container">
                <div className="home-navbar">
                    <div className="home-menu" onClick={this.openNav}></div>
                    <div className="home-center">
                        <div className="home-logo"></div>
                        <div className="home-titulo">
                            Brain Help Web
                        </div>
                    </div>
                    <div className="home-perfil" onClick={this.open}></div>
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
                <div id="mySidenav" className="sidenav">
                    <div className="closebtn" onClick={this.closeNav}>x</div>
                    <div onClick={this.onClickMeusPacientes}>Página Inicial</div>
                    <div onClick={this.onClickVincularPacientes}>Vincular Pacientes</div>
                    <div>Relatórios Gerados</div>
                </div>

                <div id="mySidenav2" className="sidenav2">
                    <div className="closebtn" onClick={this.close}>x</div>
                    <a href="https://www.w3schools.com/howto/tryit.asp?filename=tryhow_js_sidenav">About</a>
                    <a href="https://www.w3schools.com/howto/tryit.asp?filename=tryhow_js_sidenav">Services</a>
                    <a href="https://www.w3schools.com/howto/tryit.asp?filename=tryhow_js_sidenav">Clients</a>
                    <a href="https://www.w3schools.com/howto/tryit.asp?filename=tryhow_js_sidenav">Contact</a>
                </div>
            </div>
        )
    }
}