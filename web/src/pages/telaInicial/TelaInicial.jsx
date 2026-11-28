import React from 'react'
import './TelaInicial.css'
import {useNavigate} from "react-router-dom";

function TelaInicial() {

    const navigate = useNavigate();

    return (
        <div className="container">
            <div className="imagem">

            </div>
            <div className="content">
                <div className="logo">

                </div>

                <div className="navigator">
                    <div className="title">
                        <h1>Seja Bem-Vindo ao Brain Help!</h1>
                    </div>
                    <div className="buttons">
                        <div className="entrar botao" onClick={() => navigate("/login")}>
                            Entrar
                        </div>
                        <div className="cadastrar botao" onClick={() => navigate("/")}>
                            Cadastrar
                        </div>
                    </div>
                </div>
            </div>

        </div>)
}

export default TelaInicial;