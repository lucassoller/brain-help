import React from 'react'
import './Cadastro.css'
import './Teste.css'
import RegisterService from '../../Services/RegisterService'


export default class Teste extends React.Component{
    constructor(){
        super()
        this.state = {
            file: ""
        }
        this.enviar = this.enviar.bind(this)
        this.handdleChange = this.handdleChange.bind(this)
    
    }

    handdleChange(event) {
        const target = event.target
        const value = target.value
        const name = target.name
        this.setState({
            [name]: value
        })
    }

    handleFiles(e) {
        this.setState({
           file: e.target.files[0]
        })
        this.setarImagem(e)
    }

    setarImagem(e){
        var reader = new FileReader();     
        reader.onload = function(i) {
            document.getElementById("teste-imagem").setAttribute("src", reader.result )
        }
        reader.readAsDataURL(e.target.files[0]);
    }



    enviar(){ 
        var formData = new FormData();
        formData.append("file", this.state.file)
        formData.append("email", "appbrainhelp@gmail.com") 
        RegisterService.editarFoto(formData)
    }

    render(){
        return(<div className="teste-container">
            <div className="teste-button">
                    

                <img id="teste-imagem" src=""/>
                </div>
                <input 
                    type="file"
                    placeholder="Nome"
                    name="nome"
                    accept="image/png, image/jpeg"
                    className="info-input file_customizada"   
                    id="arquivo"
                    onChange={(e)=>this.handleFiles(e)}       
                />
                    

                    <input type="submit" className="teste-enviar" onClick={this.enviar}/>

                    

                               
        </div>)
    }
}