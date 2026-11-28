import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import './App.css';

import NotFound from './components/NotFound/NotFound';
import TelaInicial from './components/Telas/TelaInicial';
import Login from './components/Telas/Login';
import CadastroInicial from './components/Telas/CadastroInicial';
import Home from './components/Telas/Home';
import RecuperarSenha from './components/Telas/RecuperarSenha';
import EsquecerSenha from './components/Telas/EsquecerSenha';
import Email from './components/Telas/Email';
import Cadastro from './components/Telas/Cadastro';
import MeuPerfil from './components/Telas/MeuPerfil';

function App() {
    return (
        <Routes>
            <Route path="/404" element={<NotFound />} />
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
            <Route path="/" element={<TelaInicial />} />
            <Route path="/login" element={<Login />} />
            <Route path="/homepage" element={<TelaInicial />} />
            <Route path="*" element={<Navigate to="/404" replace />} />
        </Routes>
    );
}

export default App;