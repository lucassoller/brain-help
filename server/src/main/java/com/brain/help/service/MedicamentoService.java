package com.brain.help.service;

import com.brain.help.dto.medicamento.MedicamentoDTO;
import com.brain.help.dto.medicamento.MedicamentoResponseDTO;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Medicamento;
import com.brain.help.exception.BusinessException;
import com.brain.help.exception.NotFoundException;
import com.brain.help.repository.MedicamentoRepository;
import com.brain.help.security.UsuarioLogadoService;
import com.brain.help.utils.ImageFileWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicamentoService {

	private final MedicamentoRepository medicamentoRepository;
	private final UsuarioLogadoService usuarioLogadoService;

	@Transactional
	public MedicamentoResponseDTO cadastrar(MedicamentoDTO dto) throws Exception {
		Dependente dependenteLogado = usuarioLogadoService.getDependenteLogado();
		Medicamento entity = toEntity(dto, dependenteLogado);
		Medicamento salvo = medicamentoRepository.save(entity);
		return toDTO(salvo);
	}

	@Transactional(readOnly = true)
	public List<MedicamentoResponseDTO> buscarTodosDoLogado() {
		Dependente dependenteLogado = usuarioLogadoService.getDependenteLogado();
		return medicamentoRepository.findByDependenteIdOrderByNomeMedicamentoAsc(dependenteLogado.getId())
				.stream()
				.map(this::toDTO)
				.toList();
	}

	@Transactional(readOnly = true)
	public MedicamentoResponseDTO buscarPorId(Long id) {
		Medicamento entity = buscarMedicamentoPorId(id);
		return toDTO(entity);
	}

	@Transactional
	public MedicamentoResponseDTO editar(Long id, MedicamentoDTO dto) throws Exception {
		Medicamento entity = buscarMedicamentoPorId(id);

		entity.setNomeMedicamento(dto.getNomeMedicamento());
		entity.setFuncao(dto.getFuncao());
		entity.setContraIndicacoes(dto.getContraIndicacoes());
		entity.setEfeitosColaterais(dto.getEfeitosColaterais());
		entity.setQuantidade(dto.getQuantidade());
		entity.setMedida(dto.getMedida());
		entity.setDataHoraInicial(dto.getDataHoraInicial());

		if (dto.getDataFinal() != null && dto.getDataFinal().isBefore(dto.getDataHoraInicial().toLocalDate())) {
			throw new BusinessException("A data final não pode ser anterior à data de início");
		}

		entity.setDataFinal(dto.getDataFinal());
		entity.setFrequencia(dto.getFrequencia());

		if (dto.getFoto() != null && !dto.getFoto().isBlank()) {
			entity.setFoto(ImageFileWriter.saveImageToFolder(entity.getFoto(), dto.getFoto()));
		}

		Medicamento atualizado = medicamentoRepository.save(entity);
		return toDTO(atualizado);
	}

	@Transactional
	public void deletar(Long id) {
		Medicamento entity = buscarMedicamentoPorId(id);
		medicamentoRepository.delete(entity);
	}

	private Medicamento buscarMedicamentoPorId (Long medicamentoId) {
		return medicamentoRepository.findById(medicamentoId)
				.orElseThrow(() -> new NotFoundException("Medicamento não encontrado"));
	}

	private Medicamento toEntity(MedicamentoDTO dto, Dependente dependente) throws Exception {
		Medicamento entity = new Medicamento();

		entity.setNomeMedicamento(dto.getNomeMedicamento());
		entity.setFuncao(dto.getFuncao());
		entity.setContraIndicacoes(dto.getContraIndicacoes());
		entity.setEfeitosColaterais(dto.getEfeitosColaterais());
		entity.setQuantidade(dto.getQuantidade());
		entity.setMedida(dto.getMedida());
		entity.setDataHoraInicial(dto.getDataHoraInicial());

		if (dto.getDataFinal() != null && dto.getDataFinal().isBefore(dto.getDataHoraInicial().toLocalDate())) {
			throw new BusinessException("A data final não pode ser anterior à data de início");
		}

		entity.setDataFinal(dto.getDataFinal());
		entity.setFrequencia(dto.getFrequencia());
		entity.setDependente(dependente);

		if (dto.getFoto() != null && !dto.getFoto().isBlank()) {
			entity.setFoto(ImageFileWriter.saveImageToFolder(null, dto.getFoto()));
		}

		return entity;
	}

	private MedicamentoResponseDTO toDTO(Medicamento entity) {
		MedicamentoResponseDTO dto = new MedicamentoResponseDTO();

		dto.setId(entity.getId());
		dto.setNomeMedicamento(entity.getNomeMedicamento());
		dto.setFuncao(entity.getFuncao());
		dto.setContraIndicacoes(entity.getContraIndicacoes());
		dto.setEfeitosColaterais(entity.getEfeitosColaterais());
		dto.setQuantidade(entity.getQuantidade());
		dto.setMedida(entity.getMedida());
		dto.setDataHoraInicial(entity.getDataHoraInicial());

		dto.setDataFinal(entity.getDataFinal());
		dto.setFrequencia(entity.getFrequencia());
		dto.setFoto(entity.getFoto());

		return dto;
	}
}