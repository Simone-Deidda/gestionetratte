package it.prova.gestionetratte.service;

import java.util.List;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusService {

	public List<Airbus> listAllElementsEager();

	public Airbus findByCodice(String codiceAirbus);

	public Airbus inserisciNuovo(Airbus airbus);

	public Airbus caricaSingoloElementoEager(long id);

	public Airbus caricaSingoloElemento(Long id);

	public Airbus aggiorna(Airbus buildAirbusModel);

	public void rimuovi(Airbus airbus);

}
