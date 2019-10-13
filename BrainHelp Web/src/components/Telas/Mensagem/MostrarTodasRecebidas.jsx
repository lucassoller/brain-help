import React from 'react'
import Modal from '../../generic/Modal/Modal'
import { Redirect} from 'react-router-dom'
import Alert from '../../generic/Alert/Alert'
import Selected from '../../../SelectedContents'
import MensagemService from '../../../Services/MensagemService'
import UsuarioService from '../../../Services/UsuarioService'
import LoginService  from '../../../Services/LoginService'
import MensagemCard from './MensagemCard'
import Input from '../../generic/Input/Input'
import Button from '../../generic/Button/Button'
import TextArea from '../../generic/TextArea/TextArea'
export default class MostrarTodasMensagens extends React.Component {

    constructor() {
        super()
        this.state = {
            error:'',
            mensagens: [],
            ultimaPagina: false,
            primeiraPagina: false,
            paginaAtual:-1,
            username: null,
            numeroMensagens: 0,
            numeroAtual: 0,
            modal: '',
            errorModal: '',
            success: false,
            usuarioParaPesquisar: '',
            mensagemParaPesquisar: ''
        },this.getInitialState()
        this.handdleChange = this.handdleChange.bind(this)
        this.onClickLinkHome = this.onClickLinkHome.bind(this)
        this.onClickLinkLogout = this.onClickLinkLogout.bind(this)
        this.onClickMostrarUsuario = this.onClickMostrarUsuario.bind(this)
        this.onClickAnterior = this.onClickAnterior.bind(this)
        this.onClickProximo = this.onClickProximo.bind(this)
        this.onCloseModal = this.onCloseModal.bind(this)
        this.onClickEnviarMensagem = this.onClickEnviarMensagem.bind(this)
        this.onClickLinkEscrever = this.onClickLinkEscrever.bind(this)
        this.onClickPesquisarUsuarios = this.onClickPesquisarUsuarios.bind(this)
        this.onClickPesquisarMensagens = this.onClickPesquisarMensagens.bind(this)
        this.onClickLinkEnviadas = this.onClickLinkEnviadas.bind(this)
        this.onClickLinkTodasMensagens = this.onClickLinkTodasMensagens.bind(this)
        
    }

    setSelectedContent(content) {
        this.setState({
            selectedContent: content
        })
    }

    componentDidMount() {
        this.setState({
            selectedContent: 'NONE'
        })
        this.loadMensagens()
    }

    loadMensagens(){
        MensagemService.buscarPorDestinatario(this.state.paginaAtual+1)
        .then(resp =>{
            this.setState({
                mensagens: resp.data.content,
                ultimaPagina: resp.data.last,
                primeiraPagina: resp.data.first,
                numeroMensagens: resp.data.totalElements,
                numeroAtual: this.state.numeroAtual + resp.data.numberOfElements,
                paginaAtual: this.state.paginaAtual + 1
            })
        })        
    }

    onClickLinkLogout(){
        LoginService.logout()
        this.setSelectedContent(Selected.LOGIN)
    }

    onCloseModal(){
        this.setState({
            modal: ''
        })
        this.getInitialState()
    }

    loadMensagensAnteriores(){
        MensagemService.buscarPorDestinatario(this.state.paginaAtual-1)
        .then(resp =>{
            if(this.state.ultimaPagina){
                this.setState({
                    numeroAtual : this.state.numeroAtual - (resp.data.totalElements % 10)
                })
            }else{
                this.setState({
                    numeroAtual: this.state.numeroAtual - resp.data.numberOfElements,
                })
            }
            this.setState({
                mensagens: resp.data.content,
                ultimaPagina: resp.data.last,
                primeiraPagina: resp.data.first,
                numeroMensagens: resp.data.totalElements,
                paginaAtual : this.state.paginaAtual - 1,
            })
        })
        .catch(err =>{
            console.log(err.data)
        })
    }

    onClickLinkEscrever(){
        this.setState({
            modal: "true"
        })
    }

    renderErrorModal(){
        return this.state.errorModal ? <Alert text={this.state.errorModal} alertType="danger" /> : undefined
    }

    renderError() {
        return this.state.error ? <Alert text={this.state.error} alertType="danger" /> : undefined
    }

    renderTipoUsuario(mensagem){
        if(mensagem.destinatario.username === LoginService.getUserName()){
            return mensagem.remetente
        }else{
            return mensagem.destinatario
        }
    }

    onClickMostrarUsuario(username){
        this.setState({
            username: username,
            selectedContent: Selected.MOSTRARUSUARIO
        })
    }

    onClickAnterior(){
        this.loadMensagensAnteriores()
    }

    onClickProximo(){     
        this.loadMensagens()
    }

    onClickMostrarUsuario(username){
        this.setState({
            username: username,
            selectedContent: Selected.MOSTRARUSUARIO
        })
    }
    
    onClickLinkHome(){
        this.setState({
            setSelectedContent: Selected.MENSAGENSRECEBIDAS
        })
    }

    onClickPesquisarUsuarios(){
        this.setState({
            selectedContent: Selected.PESQUISARUSUARIOS
        })
    }

    onClickPesquisarMensagens(){
        this.setState({
            selectedContent: Selected.PESQUISARMENSAGENS
        })
    }

    onClickLinkEnviadas(){
        this.setState({
            selectedContent: Selected.MENSAGENSENVIADAS
        })
    }

    onClickLinkTodasMensagens(){
        this.setState({
            selectedContent: Selected.TODASMENSAGENS
        })
    }

    onClickEnviarMensagem(){
        MensagemService.salvarMensagem(this.state.destinatario, this.state.msg)
        .then(then =>{
            this.setState({
                success: true,
                paginaAtual : this.state.paginaAtual - 1
            })
            this.onCloseModal() 
            this.loadMensagens()          
        })
        .catch(err =>{
            this.setState({
                errorModal: err.response.data.message
            })
        })
    }

    renderBotaoAnterior(){
        if(this.state.primeiraPagina){
            return <span className="anterior desativado">></span>        
        }else{
            return <span className="anterior ativado" onClick={this.onClickAnterior}>></span>        
        }
    }

    renderBotaoProximo(){
        if(this.state.ultimaPagina){
            return <span className="proximo desativado">></span>
        }else{
            return <span className="proximo ativado" onClick={this.onClickProximo}>></span>        
        }
    }

    renderSuccess(){
        if(this.state.success){
            return <div className="sucesso">
                Mensagem Enviada
            </div>
        }
    }

    getInitialState() {
        return {
            destinatario: '',
            msg: ''
        }
    }

    handdleChange(event) {
        const target = event.target
        const value = target.value
        const name = target.name
        this.setState({
            [name]: value
        })
    }
    getShowClass() {
        const showClass = this.state.modal ? 'Modal--show' : ''
        return `Modal ${showClass}`

    }

    renderInicial(){
        if(this.state.numeroAtual < 10){
            return this.state.numeroAtual;
        }else{
            return this.state.numeroAtual - 9
        }
    }

    renderModal() {
        return <div className={this.getShowClass()}>
            <div className="modal-dialog" role="document">
                {this.renderErrorModal()}
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Enviar Mensagem</h5>
                    </div>
                    <div className="modal-body">
                    <Input
                        label="Destinatários"
                        value={this.state.destinatario}
                        name="destinatario"
                        handdleChange={this.handdleChange}
                        type="text"
                    />
                    <TextArea 
                        label="Mensagem" 
                        value={this.state.msg}
                        name="msg"
                        handdleChange={this.handdleChange}
                        type="text"
                    />
                                  
                    </div>
                    <div className="modal-footer">
                        <Button classButton="secondary" onClick={this.onCloseModal} text="Cancelar" />
                        <Button classButton="success" onClick={this.onClickEnviarMensagem} text="Enviar" />
                    </div>
                </div>
            </div>
        </div>
    }


    renderMensagens(){
        const mensagens = this.state.mensagens.map((mensagem) => {
            return <div key={mensagem.id}>
                <MensagemCard
                    usuario={this.renderTipoUsuario(mensagem).username}
                    nome={this.renderTipoUsuario(mensagem).nome}
                    tipo=''
                    conteudo={mensagem.conteudo}
                    dataDeEnvio={mensagem.dataDeEnvio}
                    onClickMostrarUsuario={() => this.onClickMostrarUsuario(this.renderTipoUsuario(mensagem).username)}
                />
            </div>
        })
        return <div className="mensagens">
            {mensagens}
        </div>
    }

    render() {
        if(this.state.selectedContent === Selected.MOSTRARUSUARIO)
        {
            return <Redirect to ={'/mensagem/usuario/' + `${this.state.username}`} />
        }
        if(this.state.selectedContent === Selected.PESQUISARMENSAGENS)
        {
            return <Redirect to ={'/mensagem/todas/' + `${this.state.mensagemParaPesquisar}`} />
        }
        if(this.state.selectedContent === Selected.PESQUISARUSUARIOS)
        {
            return <Redirect to ={'/buscar/usuarios/' + `${this.state.usuarioParaPesquisar}`} />
        }
        if(this.state.selectedContent === Selected.MENSAGENSRECEBIDAS)
        {
            return <Redirect to={'/home'} />
        }
        if(this.state.selectedContent === Selected.MENSAGENSENVIADAS)
        {
            return <Redirect to={'/mensagem/enviadas'} />
        }
        if(this.state.selectedContent === Selected.TODASMENSAGENS)
        {
            return <Redirect to={'/mensagem/todas'} />
        }
        if( this.state.selectedContent === Selected.LOGIN){
            return <Redirect to='/' />
        }
        return <div className="Home">
        <div className="navBar">
            <div className="navbar--buttons">
                <span className="Home--button" onClick={this.onClickLinkEscrever}><h5>Escrever mensagem</h5></span>
                <h3><span className="Home--link" onClick={this.onClickLinkHome}>Caixa de Entrada</span></h3>
                <h3><span className="Home--link" onClick={this.onClickLinkEnviadas}>Enviadas</span></h3>
                <h3><span className="Home--link" onClick={this.onClickLinkTodasMensagens}>Todas mensagens</span></h3>
                {this.renderSuccess()}
            </div>
        </div>
        <div className="Home--content">
            <div className="Home--header">
                <div className="inputs">
                    <div class="input-group col-md-5">
                        <input onChange={this.handdleChange} type="text" class="form-control" placeholder="Pesquisar mensagens" aria-label="Recipient's username" aria-describedby="button-addon2" name="mensagemParaPesquisar" value={this.state.mensagemParaPesquisar}/>
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="button" id="button-addon2" onClick={this.onClickPesquisarMensagens}>Pesquisar</button>
                        </div>
                    </div>
                    <div class="input-group col-md-5">
                        <input onChange={this.handdleChange} type="text" class="form-control" placeholder="Pesquisar usuários" aria-label="Recipient's username" aria-describedby="button-addon2" name="usuarioParaPesquisar" value={this.state.usuarioParaPesquisar}/>
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="button" id="button-addon2" onClick={this.onClickPesquisarUsuarios}>Pesquisar</button>
                        </div>
                    </div>
            </div>
                <div className="Home--username">
                    <div className="user">
                    {LoginService.getUserName()}   
                    </div>                                   
                </div>
                <div className="barra">|</div>
                <span className="sair" onClick={this.onClickLinkLogout}>
                    Sair
                </span>
            </div>
            <div className="page">
                <div className="pagina">
                    {this.renderInicial()} - {this.state.numeroAtual} de {this.state.numeroMensagens} mensagens
                <div className="botoes">
                    {this.renderBotaoAnterior()}
                    {this.renderBotaoProximo()}
                </div>  
            </div>               
            </div>
                {this.renderMensagens()}    
                {this.renderModal()} 

        </div>
    </div>    
    }
}