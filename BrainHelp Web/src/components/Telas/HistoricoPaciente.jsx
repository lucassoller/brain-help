import React from 'react'
import './HistoricoPaciente.css'
export default class HistoricoPaciente extends React.Component{
    constructor(){
        super()
        this.state = {
            nome: 'Lucas',
            email: 'email',
            idade: 19,
            telefone: '34749413',
            endereco: 'rua vitalino batista de andrade, 43',
            estagio: 'Inicial',
            dataDiagnostico: '15/09/2000'
        }
    }

    render(){
        return(<div className="historico-container">
            <div className="historico-content">
                <div className="historico-circle">
                    <div className="historico-foto"></div>
                </div>
                <div className="historico-form">                                    
                    <div className="historico-combo">
                        <div className="historico-column">
                            <div className="historico-label">Nome</div>
                            <input 
                                type="text"
                                name="nome"
                                value={this.state.nome}
                                disabled={true}
                                className="historico-input historico-half"
                            />
                        </div>
                        <div className="historico-column">
                            <div className="historico-label">Email</div>
                            <input 
                                type="text"
                                name="email"
                                value={this.state.email}
                                className="historico-input historico-half"
                                disabled={true}
                            />
                        </div>
                    </div>
                        
                    <div className="historico-combo">
                        <div className="historico-column">
                            <div className="historico-label">Telefone</div>
                            <input 
                                type="text"
                                name="telefone"
                                value={this.state.telefone}
                                disabled={true}
                                className="historico-input historico-half"
                            />
                        </div>
                        <div className="historico-column">
                            <div className="historico-label">Idade</div>
                            <input 
                                type="text"
                                name="idade"
                                value={this.state.idade}
                                className="historico-input historico-half"
                                disabled={true}
                            />
                        </div>
                    </div>
                    <div className="historico-label">Endereço</div>
                    <input 
                        type="text"
                        className="historico-input"
                        name="endereco"
                        value={this.state.endereco}
                        disabled={true}
                    />      
                    <div className="historico-combo">
                        <div className="historico-column">
                            <div className="historico-label">Estágio da doença</div>
                            <input 
                                type="text"
                                name="estagio"
                                value={this.state.estagio}
                                className="historico-input historico-half"
                                disabled={true}
                            />
                        </div>
                        <div className="historico-column">
                            <div className="historico-label">Data do diagnóstico</div>
                            <input 
                                type="text"
                                name="dataDiagnostico"
                                value={this.state.dataDiagnostico}
                                className="historico-input historico-half"
                                disabled={true}
                            />
                        </div>                                                       
                    </div>
                </div>
            </div>
            <div className="historico-relatorios">
                <div className="historico-atividades">
                    <div className="historico-atividade-titulo">Relatório de atividades realizadas</div>
                    <div className="historico-atividade-combo">
                        <div className="historico-atividade-data">Data (de):</div>
                        <input 
                            type="date"
                            name="dataAtividade1"
                            value={this.state.dataAtividade1}
                            className="historico-input-data"
                            placeholder="dd/mm/AAAA"
                        />
                        <div className="historico-data-final historico-atividade-data">Até:</div>
                        <input 
                            type="date"
                            name="dataAtividade2"
                            value={this.state.dataAtividade2}
                            className="historico-input-data"
                            placeholder="dd/mm/AAAA"
                        />
                    </div>
                    <div className="historico-footer"> 
                        <div className="historico-atividade-button">Gerar relatório</div>
                    </div>
                </div>
                <div className="historico-conversas">
                    <div className="historico-atividade-titulo">Histórico de conversas com o chatbot</div>
                    <div className="historico-atividade-combo">
                        <div className="historico-atividade-data">Data (de):</div>
                            <input 
                                type="date"
                                name="dataAtividade1"
                                value={this.state.dataAtividade1}
                                className="historico-input-data"
                                placeholder="dd/mm/AAAA"
                            />
                            <div className="historico-data-final historico-atividade-data">Até:</div>
                            <input 
                                type="date"
                                name="dataAtividade2"
                                value={this.state.dataAtividade2}
                                className="historico-input-data"
                                placeholder="dd/mm/AAAA"
                            />
                        </div>
                        <div className="historico-footer"> 
                            <div className="historico-atividade-button">Gerar histórico</div>
                        </div>
                </div>
            </div>
        </div>)
    }
}