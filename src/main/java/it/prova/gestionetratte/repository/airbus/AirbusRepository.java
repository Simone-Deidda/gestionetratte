package it.prova.gestionetratte.repository.airbus;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusRepository extends CrudRepository<Airbus, Long>, CustomAirbusRepository {

	@Query("select distinct a from Airbus a left join fetch a.tratte ")
	List<Airbus> findAllEager();

	Optional<Airbus> findByCodice(String codice);

	@Query("select a from Airbus a left join fetch a.tratte where a.id = ?1")
	Optional<Airbus> findByIdEager(long id);

}
