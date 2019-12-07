import React from 'react'
import DesempenhoCard from './DesempenhoCard'
import DiagnosticadoService from '../../Services/DiagnosticadoService'
export default class Desempenho extends React.Component{
    constructor(){
        super()
        this.state = {
            desempenhos: []
        }
    }

    componentDidMount(){
        this.loadDesempenhos()
    }

    loadDesempenhos(){
        DiagnosticadoService.buscarDesempenhos(this.props.emailDiagnosticado, this.props.dataInicial, this.props.dataFinal)
        .then((result => {
            this.setState({
                desempenhos: result.data
            })
        })).catch((err =>{
            this.setState({
                error: err.response.data
            })
        }))
    }

    renderDesempenhos() {
            const desempenhos = this.state.desempenhos.map((desempenho) => {              
                return <div>
                    <DesempenhoCard
                        atividade={desempenho.atividade}
                        pontuacao={desempenho.pontuacao}
                        dataRelizacao={desempenho.dataRelizacao}
                        avaliacaoDesempenho={desempenho.avaliacaoDesempenho}
                    />
                </div>
            })
            return <div>
                {desempenhos}
            </div>
    }

    render(){
        return(<div>
            {this.renderDesempenhos()}
        </div>)
    }
}