import React, { Component } from 'react';
import LoginForm from './components/LoginForm/LoginForm'
import NotFound from './components/NotFound/NotFound'
import { Switch, Route, Redirect} from 'react-router-dom'
import './App.css';
import MostrarTodasRecebidas from './components/Telas/Mensagem/MostrarTodasRecebidas';
import RegisterForm from './components/RegisterForm/RegisterForm'
import TelaInicial from './components/Telas/TelaInicial'
import Login from './components/Telas/Login'
import CadastroInicial from './components/Telas/CadastroInicial'
import Cadastro from './components/Telas/Cadastro'
import Home from './components/Telas/Home'
import RecuperarSenha from './components/Telas/RecuperarSenha'
import EsquecerSenha from './components/Telas/EsquecerSenha'
import Email from './components/Telas/Email'

class App extends Component {
  render() {
    return (
      <div className="div">
        <Switch>
            <Route path="/404" component={NotFound} />
            <Route path="/cadastroAntigo" component={RegisterForm} />
            <Route path="/cadastro-inicial" component={CadastroInicial} />
            <Route path="/cadastro" component={Cadastro} />
            <Route path="/info" component={Home} />
            <Route path="/email" component={Email} />
            <Route path="/recuperar" component={RecuperarSenha} />
            <Route path="/esquecer" component={EsquecerSenha} />
            <Route path="/" exact component={TelaInicial} />
            <Route path="/loginAntigo" exact component={LoginForm} />
            <Route path="/login" exact component={Login} />
            <Route path="/home" component={MostrarTodasRecebidas} />    
            <Route path="/homepage" component={TelaInicial} />    
            <Redirect to="/404"/>
          </Switch>
      </div>
    );
  }
}

export default App;
