package it.prova.gestionetratte.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionetratte.dto.AirbusDTO;
import it.prova.gestionetratte.dto.TrattaDTO;
import it.prova.gestionetratte.exception.AirbusNotFoundException;
import it.prova.gestionetratte.exception.IdNotNullForInsertException;
import it.prova.gestionetratte.exception.TrattaNotFoundException;
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.AirbusService;

@RestController
@RequestMapping("api/airbus")
public class AirbusController {
	@Autowired
	private AirbusService airbusService;
	
	@GetMapping
	public List<AirbusDTO> getAll() {
		return AirbusDTO.createAirbusDTOListFromModelList(airbusService.listAllElementsEager(), true);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AirbusDTO createNew(@Valid @RequestBody AirbusDTO input) {
		if(input.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		
		Airbus registaInserito = airbusService.inserisciNuovo(input.buildAirbusModel());
		return AirbusDTO.airbus(registaInserito, false);
	}
	
	@GetMapping("/{id}")
	public AirbusDTO findById(@PathVariable(value = "id", required = true) long id) {
		Airbus airbus = airbusService.caricaSingoloElementoEager(id);

		if (airbus == null)
			throw new AirbusNotFoundException("Airbus not found con id: " + id);

		return AirbusDTO.airbus(airbus, true);
	}
}
