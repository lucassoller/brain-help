//package com.example.demo.service.medico;
//
//import java.util.Objects;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.example.demo.model.Medico;
//import com.example.demo.repository.MedicoRepository;
//
//@Service
//public class BuscarMedicoPorEmailOuIdService {
//
//	@Autowired
//	MedicoRepository medicoRepository;
//
//	public Medico buscar(String identificacao) {
//		if ((Objects.isNull(identificacao) || identificacao.isEmpty())) {
//			throw new IllegalArgumentException("A identifica��o n�o pode estar em branco");
//		}		
//		
//		Optional<Medico> medico = medicoRepository.findByEmail(identificacao);
//		if(!medico.isPresent()) {
//			medico = medicoRepository.findByCodMedico(identificacao);
//			if(!medico.isPresent()) {
//				throw new IllegalArgumentException("Usu�rio n�o encontrado");
//			}	
//		}
//		return medico.get();
//	}
//}