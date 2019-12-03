package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Medico;
import com.example.demo.model.dto.RedefinicaoSenhaRequestDto;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.service.diagnosticado.CadastrarDiagnosticadoService;
import com.example.demo.service.medico.CadastrarMedicoService;
import com.example.demo.service.medico.EditarFotoMedicoService;
import com.example.demo.service.medico.EditarSenhaMedicoService;

@RestController
@RequestMapping("/public/registro")
public class RegistroController {

	@Autowired
	CadastrarMedicoService cadastrarMedico;
	
	@Autowired
	CadastrarDiagnosticadoService cadastrarDiagnosticado;
	
	@Autowired
	EditarSenhaMedicoService editarSenhaMedico;
	
	@Autowired
	EditarFotoMedicoService editarFotoMedico;
	
	@Autowired
	MedicoRepository medicoRepository;
	
	@PostMapping("/medico")
	public void cadastrarMedico(@RequestBody Medico medico) {
		cadastrarMedico.salvar(medico);
	}
	
	@PutMapping("/medico/editar/foto")
	public void editarFotoMedico(MultipartFile file, RedirectAttributes redirectAttributes, String email) {
		editarFotoMedico.editar(file, email);
	}
	
	@PutMapping("/medico/editar/senha")
	public void editarMedico(@RequestBody RedefinicaoSenhaRequestDto redefinicao) {
		editarSenhaMedico.editar(redefinicao);
	}
	
	@PostMapping("/diagnosticado")
	public void cadastrarDiagnosticado(@RequestBody Diagnosticado diagnosticado) throws Exception {
		cadastrarDiagnosticado.salvar(diagnosticado);
	}
}
