package com.brain.help.service.medico;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.config.password.Criptografia;
import com.brain.help.dto.login.LoginRequestDto;
import com.brain.help.dto.login.LoginResponseDto;
import com.brain.help.dto.senha.RedefinicaoSenhaRequestDto;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Medico;
import com.brain.help.repository.DependenteRepository;
import com.brain.help.repository.MedicoRepository;
import com.brain.help.service.dependente.BuscarDiagnosticadoPorEmailService;
import com.brain.help.service.endereco.CadastrarEnderecoService;
import com.brain.help.service.endereco.EditarEnderecoService;
import com.brain.help.utils.ImageFileWriter;

@Service
public class MedicoService {
	@Autowired
	MedicoRepository medicoRepository;
	@Autowired
	DependenteRepository dependenteRepository;
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	@Autowired
	CadastrarEnderecoService cadastrarEndereco;
	@Autowired
	EditarEnderecoService editarEndereco;
	@Autowired
	AuthenticationService authenticationService;

	public void buscarEmailUsado(String email) {
		if (Objects.isNull(email) || email.isEmpty()) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		if (medicoRepository.findByEmail(email).isPresent()) {
			throw new IllegalArgumentException("O email já está em uso");
		}
	}

	public Medico buscarPorEmailEGoogle(String emailMedico, boolean google) {
		if (Objects.isNull(emailMedico) || emailMedico.isEmpty()) {
			throw new IllegalArgumentException("A identificação não pode estar em branco");
		}
		return medicoRepository.findByEmailAndGoogle(emailMedico, google)
				.orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
	}

	public Medico buscar(String emailMedico, boolean foto) {
		if (Objects.isNull(emailMedico) || emailMedico.isEmpty()) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		Medico medico = medicoRepository.findByEmail(emailMedico)
				.orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
		if (foto && !Objects.isNull(medico.getFoto()) && !medico.getFoto().isEmpty()) {
			try {
				medico.setFoto(ImageFileWriter.returnBase64FromFile(medico.getFoto()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return medico;
	}

	public void salvar(Medico medico) throws Exception {
		if (Objects.isNull(medico.getNome()) || medico.getNome().isEmpty()) {
			throw new IllegalArgumentException("O nome não pode estar em branco");
		}
		if (Objects.isNull(medico.getSobrenome()) || medico.getSobrenome().isEmpty()) {
			throw new IllegalArgumentException("O sobrenome não pode estar em branco");
		}
		if (Objects.isNull(medico.getEmail()) || medico.getEmail().isEmpty()) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		if (Objects.isNull(medico.getSenha()) || medico.getSenha().isEmpty()) {
			throw new IllegalArgumentException("A senha não pode estar em branco");
		}
		if (Objects.isNull(medico.getTelefone()) || medico.getTelefone().isEmpty()) {
			throw new IllegalArgumentException("O telefone não pode estar em branco");
		}
		if (Objects.isNull(medico.getEspecializacao()) || medico.getEspecializacao().isEmpty()) {
			throw new IllegalArgumentException("A especialização não pode estar em branco");
		}
		buscarEmailUsado(medico.getEmail());
		Criptografia criptografia = new Criptografia();
		medico.setSenha(criptografia.criptografarSenha(medico.getSenha()));
		if (Objects.isNull(medico.getEndereco())) {
			throw new IllegalArgumentException("O endereço não pode estar em branco");
		}
		if (!Objects.isNull(medico.getFoto()) && !medico.getFoto().isEmpty()) {
			String base64 = medico.getFoto();
			medico.setFoto(ImageFileWriter.saveImageToFolder(null, base64));
		}
		cadastrarEndereco.salvar(medico.getEndereco());
		medicoRepository.save(medico);
	}

	public void deletar(String emailMedico) {
		Medico medico = buscar(emailMedico, false);
		medicoRepository.delete(medico);
	}

	public void editar(String email, Medico medico) throws Exception {
		Medico medicoParaEditar = medicoRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
		if (!Objects.isNull(medico.getNome()) && !medico.getNome().isEmpty()) {
			medicoParaEditar.setNome(medico.getNome());
		}
		if (!Objects.isNull(medico.getSobrenome()) && !medico.getSobrenome().isEmpty()) {
			medicoParaEditar.setSobrenome(medico.getSobrenome());
		}
		if (!Objects.isNull(medico.getTelefone()) && !medico.getTelefone().isEmpty()) {
			medicoParaEditar.setTelefone(medico.getTelefone());
		}
		if (!Objects.isNull(medico.getEspecializacao()) && !medico.getEspecializacao().isEmpty()) {
			medicoParaEditar.setEspecializacao(medico.getEspecializacao());
		}
		if (!Objects.isNull(medico.getEndereco())) {
			medico.getEndereco().setCodEndereco(medicoParaEditar.getEndereco().getCodEndereco());
			editarEndereco.editar(medico.getEndereco());
		}
		if (!Objects.isNull(medico.getFoto()) && !medico.getFoto().isEmpty()) {
			String base64 = medico.getFoto();
			medicoParaEditar.setFoto(ImageFileWriter.saveImageToFolder(null, base64));
		}
		medicoRepository.save(medicoParaEditar);
	}

	public void editar(RedefinicaoSenhaRequestDto redefinicaoRequest) {
		if (Objects.isNull(redefinicaoRequest.getEmail()) || redefinicaoRequest.getEmail().isEmpty()) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		if (Objects.isNull(redefinicaoRequest.getSenha()) || redefinicaoRequest.getSenha().isEmpty()) {
			throw new IllegalArgumentException("A senha não pode estar em branco");
		}
		Medico medico = buscar(redefinicaoRequest.getEmail(), false);
		Criptografia criptografia = new Criptografia();
		medico.setSenha(criptografia.criptografarSenha(redefinicaoRequest.getSenha()));
		medicoRepository.save(medico);
	}

	public LoginResponseDto logar(LoginRequestDto loginDto) {
		if (Objects.isNull(loginDto.getIdentificacao()) || loginDto.getIdentificacao().isEmpty()) {
			throw new IllegalArgumentException("O nome de usuário não pode estar em branco");
		}
		if (Objects.isNull(loginDto.getSenha()) || loginDto.getSenha().isEmpty()) {
			throw new IllegalArgumentException("A senha não pode estar em branco");
		}
		Criptografia criptografia = new Criptografia();
		Medico medico = buscar(loginDto.getIdentificacao(), false);
		if (!criptografia.senhaIgual(loginDto.getSenha(), medico.getSenha())) {
			throw new IllegalArgumentException("Senha incorreta");
		}
		String token = authenticationService.authenticate(medico.getEmail(), medico.getSenha());
		return new LoginResponseDto(medico.getEmail(), token, medico.isGoogle());
	}

	public LoginResponseDto logarGoogle(LoginRequestDto loginDto) {
		if (Objects.isNull(loginDto.getIdentificacao()) || loginDto.getIdentificacao().isEmpty()) {
			throw new IllegalArgumentException("O nome de usuário não pode estar em branco");
		}
		Medico medico = buscarPorEmailEGoogle(loginDto.getIdentificacao(), true);
		medico.setSenha("senha");
		String token = authenticationService.authenticate(medico.getEmail(), medico.getSenha());
		return new LoginResponseDto(medico.getEmail(), token, medico.isGoogle());
	}

	public void vincular(String emailMedico, String emailDiagnosticado) {
		if (Objects.isNull(emailMedico) || emailMedico.isEmpty()) {
			throw new IllegalArgumentException("O email do médico não pode estar em branco");
		}
		if (Objects.isNull(emailDiagnosticado) || emailDiagnosticado.isEmpty()) {
			throw new IllegalArgumentException("O email do diagnosticado não pode estar em branco");
		}
		Medico medico = buscar(emailMedico, false);
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		dependente.setMedico(medico);
		dependenteRepository.save(dependente);
	}
}
