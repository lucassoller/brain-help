import React from 'react'
import Alert from '../generic/Alert/Alert'
import MedicoService from '../../Services/MedicoService'
import './MeuPerfil.css'
import {Redirect} from 'react-router-dom'
import $ from 'jquery'

const SELECTED_CONTENTS = {
    LOGIN: 'LOGIN',
    CADASTRO: 'CADASTRO',
    CADASTROINICIAL: 'CADASTROINICIAL',
    HOME: 'HOME'
}

export default class MeuPerfil extends React.Component{
    constructor(){
        super()
        this.state = {
            nome: '',
            sobrenome: '',
            email: '',
            telefone: '',
            especializacao: '',
            logradouro: '',
            numero: '',
            cidade: '',
            estado: '',
            bairro: '',
            cep: '',
            foto: '',
            file: null,
            selectedContent: SELECTED_CONTENTS.CADASTRO
        }
        this.handdleChange = this.handdleChange.bind(this)
        this.onClickLinkEditar = this.onClickLinkEditar.bind(this)
        this.onClickUpload = this.onClickUpload.bind(this)
    }

    componentDidMount(){   
        this.loadMedicoLogado()   
        this.setColor()
        $(".perfil-file").hide();
    }

    handleFiles(e) {
        if(e.target.files[0]){
            this.setState({
                file: e.target.files[0]
            })
     
            this.setarImagem(e)   
        }
       
    }

    setarImagem(e){
        var reader = new FileReader();     
        reader.onload = function(i) {
            document.getElementById("perfil-imagem").setAttribute("src", reader.result )
            document.getElementById("perfil-imagem").style.background = "none";
        }
        reader.readAsDataURL(e.target.files[0]);
    }

    setColor(){
        $(".perfil-disabled").css("background", "rgb(233, 232, 232)");
        $(".perfil-disabled").css("color", "rgb(78, 76, 76)");
    }

    onClickUpload(){
        $(".perfil-file").click();
    }

    handdleChange(event) {
        const target = event.target
        const value = target.value
        const name = target.name
        this.setState({
            [name]: value
        })
    }

    loadMedicoLogado(){
        MedicoService.obterMedicoLogado()
        .then((result) => {  
            var foto = '';
            if(result.data.foto !== '' && result.data.foto !== null){
                foto =  'data:image/png;base64,'+result.data.foto;
                document.getElementById("perfil-imagem").style.background = "none";
                document.getElementById("perfil-imagem").src = foto;
            }

            console.log(foto);
            this.setState({
                nome: result.data.nome,
                sobrenome: result.data.sobrenome,
                email: result.data.email,
                telefone: result.data.telefone,
                especializacao: result.data.especializacao,
                logradouro: result.data.endereco.logradouro,
                numero: result.data.endereco.numero,
                estado: result.data.endereco.estado,
                cidade: result.data.endereco.cidade,
                bairro: result.data.endereco.bairro,
                cep: result.data.endereco.cep,
            })       
        }).catch((err) => {
            this.setState({
                error: err.response.data.message
            })
        })
    }

    onClickLinkEditar(){
        const account = this.state
        if(account.senha !== account.confirmar){
            this.setState({
                error: 'As senhas não coincidem'
            })
        }else{
            var foto = document.getElementById("perfil-imagem").src;
            foto = foto.split(",")[1];
            var endereco = {logradouro: account.logradouro, numero: account.numero, cidade: account.cidade,
            estado: account.estado, bairro: account.bairro, cep: account.cep};
            MedicoService
            .editarPerfil(account.nome, account.sobrenome, account.telefone, endereco, 
                account.especializacao, foto)
            .then(() => {      
            }).catch((err) => {
                this.setState({
                    error: err.response.data.message
                })
            })
        }  
    }

    setSelectedContent(content) {
        this.setState({
            selectedContent: content
        })
    }

    renderError() {
        return this.state.error ? <Alert text={this.state.error} alertType="danger" /> : undefined
    }

    render(){
        if( this.state.selectedContent === SELECTED_CONTENTS.HOME){
            return <Redirect to='/home' />
        }

        if( this.state.selectedContent === SELECTED_CONTENTS.LOGIN){
            return <Redirect to='/login' />
        }
        return(
                <div className="perfil-content">
                    <div className="perfil-form">
                    <div className="perfil-left">
                        <div className="perfil-foto">
                            <img src='' id="perfil-imagem" alt=""/>
                        </div>
                        <div className="perfil-upload">
                            <div onClick={this.onClickUpload}>Enviar foto</div>
                            <input 
                                type="file"
                                placeholder="Nome"
                                name="nome"
                                accept="image/png, image/jpeg"
                                className="perfil-file"   
                                id="perfil-arquivo"
                                onChange={(e)=>this.handleFiles(e)}       
                            />
                        </div>
                        
                    </div>
                        <div className="perfil-form-fields">
                            <div className="perfil-combo">
                                <div className="perfil-column">
                                    <div className="perfil-label">Nome</div>
                                    <input 
                                        type="text"
                                        placeholder="Nome"
                                        name="nome"
                                        value={this.state.nome}
                                        onChange={this.handdleChange}
                                        className="perfil-input"          
                                    />
                                </div>
                                <div className="perfil-column">
                                    <div className="perfil-label">Sobrenome</div>
                                    <input 
                                        type="text"
                                        placeholder="Sobrenome"
                                        name="sobrenome"
                                        value={this.state.sobrenome}
                                        onChange={this.handdleChange}
                                        className="perfil-input"          
                                    />
                                </div>
                            </div>
                            <div className="perfil-label">Email</div>
                            <input 
                                type="text"
                                placeholder="Email"
                                className="perfil-complete perfil-input perfil-disabled"
                                name="email"
                                value={this.state.email}
                                onChange={this.handdleChange}
                                disabled={true}
                            />
                            <div className="perfil-combo">
                                <div className="perfil-column">
                                    <div className="perfil-label">Telefone</div>
                                    <input 
                                        type="text"
                                        placeholder="( ) 9 9999-9999"
                                        name="telefone"
                                        value={this.state.telefone}
                                        onChange={this.handdleChange}
                                        className="perfil-input"
                                    />
                                </div>
                                <div className="perfil-column">
                                    <div className="perfil-label">Especialização</div>
                                    <input 
                                        type="text"
                                        placeholder="Especialização"
                                        name="especializacao"
                                        value={this.state.especializacao}
                                        onChange={this.handdleChange}
                                        className="perfil-input"
                                    />
                                </div>                                                  
                            </div>
                            <div className="perfil-combo">
                                <div className="perfil-logradouro">
                                    <div className="perfil-label">Logradouro</div>
                                    <input 
                                        type="text"
                                        placeholder="Logradouro"
                                        className="perfil-input"
                                        name="logradouro"
                                        value={this.state.logradouro}
                                        onChange={this.handdleChange}
                                    />
                                </div>
                                <div className="perfil-numero">
                                    <div className="perfil-label">Número</div>
                                    <input 
                                    type="number"
                                    placeholder="Número"
                                    className="perfil-input"
                                    name="numero"
                                    value={this.state.numero}
                                    onChange={this.handdleChange}
                                    />
                                </div>
                            </div>
                            <div className="perfil-combo">
                                <div className="perfil-column">
                                    <div className="perfil-label">Estado</div>
                                    <input 
                                        type="text"
                                        placeholder="Estado"
                                        name="estado"
                                        value={this.state.estado}
                                        onChange={this.handdleChange}
                                        className="perfil-input"
                                    />
                                </div>
                                <div className="perfil-column">
                                    <div className="perfil-label">Cidade</div>
                                    <input 
                                        type="text"
                                        placeholder="Cidade"
                                        name="cidade"
                                        value={this.state.cidade}
                                        onChange={this.handdleChange}
                                        className="perfil-input"
                                    />
                                </div>
                            </div>
                            <div className="perfil-combo">
                                <div className="perfil-column">
                                    <div className="perfil-label">Bairro</div>
                                    <input 
                                        type="text"
                                        placeholder="Bairro"
                                        name="bairro"
                                        value={this.state.bairro}
                                        onChange={this.handdleChange}
                                        className="perfil-input"
                                    />
                                </div>
                                <div className="perfil-column">
                                    <div className="perfil-label">CEP</div>
                                    <input 
                                        type="text"
                                        placeholder="CEP"
                                        name="cep"
                                        value={this.state.cep}
                                        onChange={this.handdleChange}
                                        className="perfil-input"
                                    />
                                </div>                                                       
                            </div>
                            <div className="perfil-form-footer">
                                <div className="perfil-button" onClick={this.onClickLinkEditar}>
                                    Editar
                                </div>
                            </div>                          
                        </div>                       
                    </div> 
        </div>)
    }
}