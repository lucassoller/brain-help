package com.example.demo.web;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Desempenho;
import com.example.demo.model.security.UserPrincipal;
import com.example.demo.repository.DesempenhoRepository;
import com.example.demo.service.desempenho.BuscarDesempenhoPorIdService;
import com.example.demo.service.desempenho.BuscarDesempenhosService;
import com.example.demo.service.desempenho.CadastrarDesempenhoService;

@RestController
@RequestMapping("/desempenho")
public class DesempenhoController {
	
	@Autowired
	private DesempenhoRepository desempenhoRepository;
	
	@Autowired
	private CadastrarDesempenhoService cadastrarDesemepenho;
	
	@Autowired
	private BuscarDesempenhoPorIdService buscarDesempenhoPorId;
	
	@Autowired
	private BuscarDesempenhosService buscarDesempenhos;
	
	@GetMapping("/buscar/todos")
	public List<Desempenho> buscarTodos(){
		return desempenhoRepository.findAll();
	}
	
	@GetMapping("/buscar/{ID}")
	public Desempenho buscarPorId(@PathVariable("ID") Integer codDesempenho){
		return buscarDesempenhoPorId.buscar(codDesempenho);
	}
	
	@GetMapping("/buscar/desempenhos")
	public List<Desempenho> buscarDesempenhosDeAtividades(@RequestParam("emailDiagnosticado") String emailDiagnosticado, @RequestParam("dataInicial") Date dataInicial, @RequestParam("dataFinal") Date dataFinal){
		return buscarDesempenhos.buscar(emailDiagnosticado, dataInicial, dataFinal);
	}
	
	@PostMapping("/cadastrar")
	public void cadastrarDesempenho(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Integer codAtividade) {
		cadastrarDesemepenho.salvar(userPrincipal.getEmail(), codAtividade);
	}
}
