package it.prova.gestionetratte.repository.tratta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.StatoTratta;
import it.prova.gestionetratte.model.Tratta;

public interface TrattaRepository extends CrudRepository<Tratta, Long>, CustomTrattaRepository {

	@Query("select distinct t from Tratta t left join fetch t.airbus ")
	List<Tratta> findAllEager();

	Optional<Tratta> findByCodice(String codiceTratta);

	@Query("select t from Tratta t left join fetch t.airbus where t.id = ?1")
	Optional<Tratta> findByIdEager(long id);

	List<Tratta> findAllByStatoAndDataBefore(StatoTratta attiva, LocalDate date);

}
