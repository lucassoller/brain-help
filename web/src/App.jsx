import React from 'react';
import {Route, BrowserRouter, Routes} from 'react-router-dom';
import './App.css';

import TelaInicial from './pages/TelaInicial';
import Login from "./pages/login/Login.jsx";

function App() {
    return (
        <BrowserRouter>
            <Routes>

                <Route path="/" element={<TelaInicial />} />
                <Route path="/login" element={<Login />} />

            </Routes>

        </BrowserRouter>
    );
}

export default App;

/*            <Route path="/404" element={<NotFound />} />
            <Route path="/cadastro-inicial" element={<CadastroInicial />} />
            <Route path="/cadastro" element={<Cadastro />} />
            <Route path="/meu-perfil" element={<MeuPerfil />} />
            <Route path="/home/meus-pacientes" element={<Home />} />
            <Route path="/home/vincular-pacientes" element={<Home />} />
            <Route path="/home/meu-perfil" element={<Home />} />
            <Route path="/home/alterar-senha" element={<Home />} />
            <Route path="/home/paciente/:email" element={<Home />} />
            <Route path="/email" element={<Email />} />
            <Route path="/recuperarSenha/:url" element={<RecuperarSenha />} />
            <Route path="/esquecer" element={<EsquecerSenha />} />
            <Route path="/login" element={<Login />} />
            <Route path="/homepage" element={<TelaInicial />} />
            <Route path="*" element={<Navigate to="/404" replace />} /> */