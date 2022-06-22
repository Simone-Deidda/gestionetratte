package it.prova.gestionetratte.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionetratte.dto.TrattaDTO;
import it.prova.gestionetratte.exception.IdNotNullForInsertException;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.TrattaService;

@RestController
@RequestMapping("api/tratta")
public class TrattaController {
	@Autowired
	private TrattaService trattaService;

	@GetMapping
	public List<TrattaDTO> getAll() {
		return TrattaDTO.createTrattaDTOSetFromModelList(trattaService.listAllElementsEager(), true);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TrattaDTO createNew(@Valid @RequestBody TrattaDTO input) {
		if(input.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		
		Tratta trattaInserita = trattaService.inserisciNuovo(input.buildTrattaModel());
		return TrattaDTO.buildTrattaDTOFromModel(trattaInserita, true);
	}
}
