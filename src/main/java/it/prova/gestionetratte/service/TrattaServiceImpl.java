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
	public void inserisciNuovo(Tratta tratta) {
		trattaRepository.save(tratta);
	}
}
