package it.prova.gestionetratte.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.exception.TrattaNonAnnullataException;
import it.prova.gestionetratte.model.StatoTratta;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.tratta.TrattaRepository;

@Service
public class TrattaServiceImpl implements TrattaService {
	@Autowired
	private TrattaRepository trattaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Tratta> listAllElementsEager() {
		return (List<Tratta>) trattaRepository.findAllEager();
	}

	@Override
	@Transactional(readOnly = true)
	public Tratta findByCodice(String codiceTratta) {
		return trattaRepository.findByCodice(codiceTratta).orElse(null);
	}

	@Override
	@Transactional
	public Tratta inserisciNuovo(Tratta tratta) {
		return trattaRepository.save(tratta);
	}

	@Override
	@Transactional(readOnly = true)
	public Tratta caricaSingoloElementoEager(long id) {
		return trattaRepository.findByIdEager(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Tratta caricaSingoloElemento(Long id) {
		return trattaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Tratta aggiorna(Tratta input) {
		return trattaRepository.save(input);
	}

	@Override
	@Transactional
	public void rimuovi(Tratta tratta) {
		if (tratta.getStato() != StatoTratta.ANNULLATA) {
			throw new TrattaNonAnnullataException("Impossibile eliminare la Tratta con id " + tratta.getId() + " perché è ancora attiva.");
		}
		trattaRepository.delete(tratta);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tratta> findByExample(Tratta example) {
		return trattaRepository.findByExample(example);
	}

	@Override
	@Transactional
	public void concludiTratte() {
		List<Tratta> listaTratteAttive = trattaRepository.findAllByStatoAndDataBefore(StatoTratta.ATTIVA, LocalDate.now());
		for (Tratta tratta : listaTratteAttive) {
			tratta.setStato(StatoTratta.CONCLUSA);
			trattaRepository.save(tratta);
		}
		
	}
}
