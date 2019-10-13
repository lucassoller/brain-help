package com.example.demo.model.security.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Criptografia {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    String result = encoder.encode("myPassword");
    public String criptografarSenha(String senha){
        return encoder.encode(senha);
    }

    public boolean senhaIgual(String senhaUsuario, String senhaCriptografada){
        if(encoder.matches(senhaUsuario, senhaCriptografada)){
            return true;
        }return false;
    }
}
