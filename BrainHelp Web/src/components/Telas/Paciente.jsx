import React from 'react'
import PacienteCard from './PacienteCard'
import DiagnosticadoService from '../../Services/DiagnosticadoService'
export default class Paciente extends React.Component{
    constructor(){
        super()
        this.state = {
            pacientes: [{nome: 'lucas', email:'email', telefone:'telefone', estagioAlzheimer:'estagio', medico:{email:'123'}}, {nome: 'lucas', email:'email', telefone:'telefone', estagioAlzheimer:'estagio', medico:{email:'lucassoller2000@gmail.com'}}, {nome: 'lu', email:'email', telefone:'telefone', estagioAlzheimer:'estagio', medico:{email:'lucassoller2000@gmail.com'}}, {nome: 'soller', email:'email', telefone:'telefone', estagioAlzheimer:'estagio', medico:{email:'123'}}],
            y: 1
        }
    }

    componentDidMount(){
        this.loadPacientes()
    }

    loadPacientes(){
        if(this.props.tipo === "meu"){
            DiagnosticadoService.buscarMeusPacientesPorNome(this.props.nome)
            .then((result => {
                this.setState({
                    pacientes: result.data
                })
                this.renderPacientes()
            })).catch((err =>{
                this.setState({
                    error: err.response.data
                })
            }))
        }else{
            DiagnosticadoService.buscarOutrosPacientesPorNome(this.props.nome)
            .then((result => {
                this.setState({
                    pacientes: result.data
                })
                this.renderPacientes()
            })).catch((err =>{
                this.setState({
                    error: err.response.data
                })
            }))
        }       
    }

    renderPacientes() {
            const pacientes = this.state.pacientes.map((paciente) => {
                if(paciente.nome.startsWith(this.props.nome)){

                
                return <div>
                    <PacienteCard
                        nome={paciente.nome}
                        email={paciente.email}
                        telefone={paciente.telefone}
                        estagio={paciente.estagioAlzheimer}
                        emailMedico={paciente.medico.email}
                    />
                </div>
                }
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