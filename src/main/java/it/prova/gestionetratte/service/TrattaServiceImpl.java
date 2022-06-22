package it.prova.gestionetratte.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.TrattaRepository;

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
}
