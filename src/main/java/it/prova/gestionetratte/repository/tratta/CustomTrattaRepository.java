package it.prova.gestionetratte.repository.tratta;

import java.util.List;

import it.prova.gestionetratte.model.Tratta;

public interface CustomTrattaRepository {
	public List<Tratta> findByExample(Tratta example);
}
