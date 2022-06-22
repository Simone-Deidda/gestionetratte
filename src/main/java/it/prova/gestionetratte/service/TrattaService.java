package it.prova.gestionetratte.service;

import java.util.List;

import it.prova.gestionetratte.model.Tratta;

public interface TrattaService {

	public List<Tratta> listAllElementsEager();

	public Tratta findByCodice(String codiceTratta);

	public Tratta inserisciNuovo(Tratta tratta);

}
