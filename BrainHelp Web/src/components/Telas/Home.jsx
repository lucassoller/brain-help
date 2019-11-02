import React from 'react'
import './Home.css'
import Paciente from './Paciente'
import {Redirect} from 'react-router-dom'
import $ from 'jquery'
import LoginService from '../../Services/LoginService'
import MeuPerfil from './MeuPerfil'
import AlterarSenha from './AlterarSenha'

const SELECTED_CONTENTS = {
    MEUSPACIENTES: 'MEUSPACIENTES',
    VINCULARPACIENTES: 'VINCULARPACINTES',
    LOGOUT: 'LOGOUT',
    MEUPERFIL: 'MEUPERFIL',
    ALTERARSENHA: 'ALTERARSENHA'
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
        this.onClickLogout = this.onClickLogout.bind(this)
        this.onClickMeuPerfil = this.onClickMeuPerfil.bind(this)
        this.onClickAlterarSenha = this.onClickAlterarSenha.bind(this)
    }

    onClickMeusPacientes(){
        this.setSelectedContent(SELECTED_CONTENTS.MEUSPACIENTES)
    }

    onClickVincularPacientes(){
        this.setSelectedContent(SELECTED_CONTENTS.VINCULARPACIENTES)
    }

    onClickLogout(){
        this.setSelectedContent(SELECTED_CONTENTS.LOGOUT)
    }

    onClickMeuPerfil(){
        this.setSelectedContent(SELECTED_CONTENTS.MEUPERFIL)
    }

    onClickAlterarSenha(){
        this.setSelectedContent(SELECTED_CONTENTS.ALTERARSENHA)
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
        var nome = $(".home-form").val();
        return <Paciente 
                tipoPaciente = {this.state.tipoPaciente}
                nome = {nome}           
            />
    }

    renderTipo(){
        if(this.state.tipoPaciente === 'meus-pacientes'){
            return 'Meus Pacientes'
        }else if(this.state.tipoPaciente === 'vincular-pacientes'){
            return 'Vincular Pacientes'
        }else if(this.state.tipoPaciente === 'meu-perfil'){
            return 'Meu Perfil'
        }else if(this.state.tipoPaciente === 'alterar-senha'){
            return 'Alterar Senha'
        }
    }

    renderConteudo(){
        if(this.state.tipoPaciente === 'meus-pacientes' || this.state.tipoPaciente === 'vincular-pacientes'){
            return (<div className="home-content">
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
                            <div className="home-search-icon"></div>
                        </div>
                        <div className="home-pacientes">
                            {this.renderPacientes()}
                        </div>
                    </div>
                    <div className="home-content-side home-abc"></div>
                </div>)
        }else if(this.state.tipoPaciente === 'meu-perfil'){
            return <MeuPerfil />
        }else if(this.state.tipoPaciente === 'alterar-senha'){
            return <AlterarSenha />
        }
    }

    renderBotaoAlterarSenha(){
        if(LoginService.getLoginType() === 'false'){
            return (<div className="nav-item" onClick={this.onClickAlterarSenha}>Alterar Senha</div>)
        }
    }

    render(){
        if(this.state.selectedContent === SELECTED_CONTENTS.MEUSPACIENTES){
            window.location.reload(false);
            return <Redirect to='/home/meus-pacientes' />
        }

        if(this.state.selectedContent === SELECTED_CONTENTS.VINCULARPACIENTES){
            window.location.reload(false);
            return <Redirect to='/home/vincular-pacientes' />
        }
        
        if(this.state.selectedContent === SELECTED_CONTENTS.LOGOUT){
            LoginService.logout()
            return <Redirect to='/' />
        }

        if(this.state.selectedContent === SELECTED_CONTENTS.MEUPERFIL){
            window.location.reload(false);
            return <Redirect to='/home/meu-perfil' />
        }

        if(this.state.selectedContent === SELECTED_CONTENTS.ALTERARSENHA){
            window.location.reload(false);
            return <Redirect to='/home/alterar-senha' />
        }
        return (<div className="home-container">
                <div className="home-navbar">
                    <div className="home-menu" onClick={this.openNav}></div>
                    <div className="home-center">
                        <div className="home-logo"></div>
                        <div className="home-titulo">
                            Brain Help | {this.renderTipo()}
                        </div>
                    </div>
                    <div className="home-perfil" onClick={this.open}></div>
                </div>
                {this.renderConteudo()}
                <div id="mySidenav" className="sidenav">
                    <div className="closebtn" onClick={this.closeNav}>x</div>
                    <div className="nav-item" onClick={this.onClickMeusPacientes}>Página Inicial</div>
                    <div className="nav-item" onClick={this.onClickVincularPacientes}>Vincular Pacientes</div>
                    <div className="nav-item">Relatórios Gerados</div>
                </div>

                <div id="mySidenav2" className="sidenav2">
                    <div className="closebtn" onClick={this.close}>x</div>
                    <div className="nav-item" onClick={this.onClickMeuPerfil}>Meu Perfil</div>
                    {this.renderBotaoAlterarSenha()}
                    <div className="nav-item" onClick={this.onClickLogout}>Logout</div>
                </div>
            </div>
        )
    }
}