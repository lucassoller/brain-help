import React from 'react'
import './DesempenhoCard.css'

export default class DesempenhoCard extends React.Component{
    formataData(){
        var data = this.props.dataRealizacao;
        data = data.split('T')[0].split('-');
        return (data[2]+"/"+data[1]+"/"+data[0]);
    }
    render(){
        return(<div className="desempenhoCard-container">
            <div className="desempenhoCard-info">
                <div className="desempenhoCard-nome">Atividade: {this.props.atividade}</div>
                <div className="desempenhoCard-outro">Pontuação: {this.props.pontuacao}</div>
                <div className="desempenhoCard-outro">Desempenho: {this.props.avaliacaoDesempenho}</div>
                <div className="desempenhoCard-outro">Data da Realização: {this.formataData()}</div>
            </div> 
        </div>)
    }
}