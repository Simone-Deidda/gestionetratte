package it.prova.gestionetratte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.exception.AirbusAssegnatoATrattaException;
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.airbus.AirbusRepository;

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

	@Override
	@Transactional(readOnly = true)
	public Airbus caricaSingoloElemento(Long id) {
		return airbusRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Airbus aggiorna(Airbus input) {
		return airbusRepository.save(input);
	}

	@Override
	@Transactional
	public void rimuovi(Airbus airbus) {
		if (airbus.getTratte() != null && !airbus.getTratte().isEmpty()) {
			throw new AirbusAssegnatoATrattaException("Impossibile eliminare l'Airbus con id " + airbus.getId()
					+ " perché è assegnato ad almeno una Tratta.");
		}

		airbusRepository.delete(airbus);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Airbus> findByExample(Airbus example) {
		return airbusRepository.findByExample(example);
	}
}
