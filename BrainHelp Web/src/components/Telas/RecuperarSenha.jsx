import React from 'react'
import './RecuperarSenha.css'
import {Redirect} from 'react-router-dom'

export default class RecuperarSenha extends React.Component{

    render(){
        return(
            <div className="recuperar-container">
                <div className="recuperar-content">
                    <div className="recuperar-titulo">Redefina a sua senha</div>
                    <div className="recuperar-subtitulo">Defina uma nova senha para sua conta.</div>
                    <div className="recuperar-input">
                        <div className="recuperar-forms">
                            <input 
                                type="password"
                                placeholder="Nova senha"
                                className="recuperar-form recuperar-senha"/>
                            <div className="recuperar-eye"></div>
                        </div>
                        <div div className="recuperar-forms">
                            <input 
                                type="password"
                                placeholder="Confirmar nova senha"
                                className="recuperar-form recuperar-confirmar"/>
                            <div className="recuperar-eye"></div>
                        </div>
                    </div>
                    <div className="recuperar-button">Definir senha</div>
                </div>
            </div>
            
        )
    }
}