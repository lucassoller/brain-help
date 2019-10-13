import React from 'react'
import Button from '../Button/Button'
import './Modal.css'
import Input from '../Input/Input'
import MensagemService from '../../../Services/MensagemService'
export default class Modal extends React.Component {

    constructor() {
        super()
        this.state = this.getInitialState()
        this.handdleChange = this.handdleChange.bind(this)
    }

    getInitialState() {
        return {
            destinatario: '',
            mensagem: ''
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
        const showClass = this.props.show ? 'Modal--show' : ''
        return `Modal ${showClass}`

    }



    render() {
        return <div className={this.getShowClass()}>
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">{this.props.title}</h5>
                    </div>
                    <div className="modal-body">
                    <Input
                        label="Destinatários"
                        value={this.state.destinatario}
                        name="destinatario"
                        placeholder=""
                        handdleChange={this.handdleChange}
                        type="text"
                        
                        areaLabel="Mensagem"
                        areaValue={this.state.mensagem}
                        areaName="mensagem"
                        areaHanddleChange={this.handdleChange}
                        areaType="text"
                    />
                    </div>
                    <div className="modal-footer">
                        <Button classButton="secondary" onClick={this.props.onClose} text="Cancelar" />
                        {this.props.classButtonAction ? <Button classButton={this.props.classButtonAction}
                            onClick={this.props.onClickButtonAction}
                            text={this.props.textButtonAction} /> : undefined}
                    </div>
                </div>
            </div>
        </div>
    }

}