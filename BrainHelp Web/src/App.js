import React, { Component } from 'react';
import NotFound from './components/NotFound/NotFound'
import { Switch, Route, Redirect} from 'react-router-dom'
import './App.css';
import TelaInicial from './components/Telas/TelaInicial'
import Login from './components/Telas/Login'
import CadastroInicial from './components/Telas/CadastroInicial'
import Home from './components/Telas/Home'
import RecuperarSenha from './components/Telas/RecuperarSenha'
import EsquecerSenha from './components/Telas/EsquecerSenha'
import Email from './components/Telas/Email'
import Cadastro from './components/Telas/Cadastro'

class App extends Component {
  render() {
    return (
      <div className="div">
        <Switch>
            <Route path="/404" component={NotFound} />
            <Route path="/cadastro-inicial" component={CadastroInicial} />
            <Route path="/cadastro" component={Cadastro} />
            <Route path="/home/meus-pacientes" component={Home} />
            <Route path="/home/vincular-pacientes" component={Home} />
            <Route path="/email" component={Email} />
            <Route path="/recuperarSenha/:url" component={RecuperarSenha} />
            <Route path="/esquecer" component={EsquecerSenha} />
            <Route path="/" exact component={TelaInicial} />
            <Route path="/login" exact component={Login} />
            <Route path="/homepage" component={TelaInicial} />    
            <Redirect to="/404"/>
          </Switch>
      </div>
    );
  }
}

export default App;
