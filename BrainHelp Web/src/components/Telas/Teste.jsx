import React from 'react'
import Alert from '../generic/Alert/Alert'
import CadastroService from '../../Services/CadastroService'
import LoginService from '../../Services/LoginService'
import './Cadastro.css'
import {Redirect} from 'react-router-dom'
import $ from 'jquery'
import './Teste.css'



export default class Teste extends React.Component{
    constructor(){
        super()
        this.state = {

        }
    
    }


    render(){

        return(<div className="teste-container">
        
                    <input 
                        type="file"
                        placeholder="Nome"
                        name="nome"
                        value={this.state.nome}
                        onChange={this.handdleChange}
                        accept="image/png, image/jpeg"
                        className="info-input"          
                    />

                               
        </div>)
    }
}