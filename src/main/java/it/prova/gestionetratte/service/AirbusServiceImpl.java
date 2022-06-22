package it.prova.gestionetratte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.dto.AirbusDTO;
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.AirbusRepository;

@Service
public class AirbusServiceImpl implements AirbusService {
	@Autowired
	private AirbusRepository airbusRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Airbus> listAllElementsEager() {
		return airbusRepository.findAllEager();
	}

	@Override
	@Transactional(readOnly = true)
	public Airbus findByCodice(String codiceAirbus) {
		return airbusRepository.findByCodice(codiceAirbus).orElse(null);
	}

	@Override
	@Transactional
	public Airbus inserisciNuovo(Airbus airbus) {
		return airbusRepository.save(airbus);
	}

	@Override
	@Transactional(readOnly = true)
	public Airbus caricaSingoloElementoEager(long id) {
		return airbusRepository.findByIdEager(id).orElse(null);
	}
}
