import React from 'react'
import PacienteCard from './PacienteCard'
import DiagnosticadoService from '../../Services/DiagnosticadoService'
export default class Paciente extends React.Component{
    constructor(){
        super()
        this.state = {
            pacientes: []
        }
    }

    componentDidMount(){
        this.loadPacientes()
    }

    loadPacientes(){
        DiagnosticadoService.buscarTodosPorNome(this.props.nome)
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

    renderPacientes() {
            console.log(this.state.pacientes)
            const pacientes = this.state.pacientes.map((paciente) => {
                return <div>
                    <PacienteCard
                        nome={paciente.nome}
                        email={paciente.id}
                        telefone={paciente.telefone}
                        estagio={paciente.estagioAlzheimer}
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