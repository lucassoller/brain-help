import React, { Component } from 'react';
import LoginForm from './components/LoginForm/LoginForm'
import NotFound from './components/NotFound/NotFound'
import { Switch, Route, Redirect} from 'react-router-dom'
import './App.css';
import MostrarTodasRecebidas from './components/Telas/Mensagem/MostrarTodasRecebidas';
import RegisterForm from './components/RegisterForm/RegisterForm'
import TelaInicial from './components/Telas/TelaInicial'
import Login from './components/Telas/Login'

class App extends Component {
  render() {
    return (
      <div className="div">
        <Switch>
            <Route path="/404" component={NotFound} />
            <Route path="/registro" component={RegisterForm} />
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
