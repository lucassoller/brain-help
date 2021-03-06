import React from 'react'
import PacienteCard from './PacienteCard'
import DiagnosticadoService from '../../Services/DiagnosticadoService'
export default class Paciente extends React.Component{
    constructor(){
        super()
        this.state = {
            pacientes: [],
            nome: '',
            tipoPaciente: ''
        }
    }

    componentDidMount(){
        this.loadPacientes()
    }

    componentDidUpdate(){
        if(this.props.nome !== this.state.nome){
            this.loadPacientes()
        }
    }

    loadPacientes(){
        this.setState({
            nome: this.props.nome
        })
        if(this.props.tipoPaciente === "meus-pacientes"){
            if(this.props.nome === '' || this.props.nome === undefined){
                DiagnosticadoService.buscarMeusPacientes()
                .then((result => {
                    this.setState({
                        pacientes: result.data
                    })
                })).catch((err =>{
                    this.setState({
                        error: err.response.data
                    })
                }))
            }else{
                DiagnosticadoService.buscarMeusPacientesPorNome(this.props.nome)
                .then((result => {
                    this.setState({
                        pacientes: result.data
                    })
                })).catch((err =>{
                    this.setState({
                        error: err.response.data
                    })
                }))
            }            
            
        }else if(this.props.tipoPaciente === "vincular-pacientes"){
            if(this.props.nome !== '' && this.props.nome !== undefined){
                DiagnosticadoService.buscarOutrosPacientesPorNome(this.props.nome)
                .then((result => {
                    this.setState({
                        pacientes: result.data
                    })
                })).catch((err =>{
                    this.setState({
                        error: err.response.data
                    })
                }))
            }else{
                this.setState({
                    pacientes: []
                })
            }
        }       
    }

    renderPacientes() {
            const pacientes = this.state.pacientes.map((paciente) => {              
                return <div>
                    <PacienteCard
                        nome={paciente.nome}
                        sobrenome={paciente.sobrenome}
                        email={paciente.email}
                        telefone={paciente.telefone}
                        estagio={paciente.estagioAlzheimer}
                        tipoPaciente={this.props.tipoPaciente}
                        foto={paciente.foto}
                    />
                </div>
        
            })
            return <div>
                {pacientes}
            </div>
    }

    render(){
        return(<div>
            {this.renderPacientes()}
        </div>)
    }
}