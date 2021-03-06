package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.email.RecuperarSenhaService;
import com.example.demo.service.email.RedefinirSenhaService;

@RestController
@RequestMapping("/public/email")
public class EmailController {
	
	@Autowired
	RecuperarSenhaService recuperarSenha;
	
	@Autowired
	RedefinirSenhaService redefinirSenha;
	
	@GetMapping("recuperar/medico/{EMAIL}")
	public void recuperarSenha(@PathVariable("EMAIL") String emailRecuperacao) {
		recuperarSenha.recuperar(emailRecuperacao);
	}
	
	@GetMapping("redefinir/medico/{URL}")
	public void redefinirSenha (@PathVariable("URL") String url) {
		redefinirSenha.redefinir(url);
	}
}
