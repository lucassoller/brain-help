import React, { Component } from 'react';

import GoogleLogin from 'react-google-login';

export default class Google extends React.Component{

  render() {

    const responseGoogle = (response) => {
      console.log(response);
    }

    return (
      <div className="App">

      <GoogleLogin
        clientId="269388326604-4vldemgim545v46bel9he4erpji2sgpl.apps.googleusercontent.com" //CLIENTID NOT CREATED YET
        buttonText="Entrar com o Google"
        onSuccess={responseGoogle}
        onFailure={responseGoogle}
      />

      </div>
    );
  }
}