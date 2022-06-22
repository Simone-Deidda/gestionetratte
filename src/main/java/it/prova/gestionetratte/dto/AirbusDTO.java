package it.prova.gestionetratte.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetratte.model.Airbus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirbusDTO {

	private Long id;
	@NotBlank(message = "{airbus.codice.notblank}")
	private String codice;
	@NotBlank(message = "{airbus.descrizione.notblank}")
	private String descrizione;
	@NotNull(message = "{datainizioservizio.notnull}")
	private LocalDate dataInizioServizio;
	@NotNull(message = "{numeropasseggeri.notnull}")
	@Min(1)
	private Integer numeroPasseggeri;

	@JsonIgnoreProperties(value = { "airbus" })
	private Set<TrattaDTO> tratte = new HashSet<TrattaDTO>(0);

	public AirbusDTO() {
	}

	public AirbusDTO(Long id, String codice, String descrizione, LocalDate dataInizioServizio,
			Integer numeroPasseggeri) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataInizioServizio = dataInizioServizio;
		this.numeroPasseggeri = numeroPasseggeri;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getDataInizioServizio() {
		return dataInizioServizio;
	}

	public void setDataInizioServizio(LocalDate dataInizioServizio) {
		this.dataInizioServizio = dataInizioServizio;
	}

	public Integer getNumeroPasseggeri() {
		return numeroPasseggeri;
	}

	public void setNumeroPasseggeri(Integer numeroPasseggeri) {
		this.numeroPasseggeri = numeroPasseggeri;
	}

	public Set<TrattaDTO> getTratte() {
		return tratte;
	}

	public void setTratte(Set<TrattaDTO> tratte) {
		this.tratte = tratte;
	}

	public static List<AirbusDTO> createAirbusDTOListFromModelList(List<Airbus> list, boolean includeTratte) {
		return list.stream().map(entity -> {
			AirbusDTO result = AirbusDTO.buildAirbusDTOFromModel(entity, includeTratte);
			if (includeTratte)
				result.setTratte(TrattaDTO.createTrattaDTOSetFromModelSet(entity.getTratte(), false));
			return result;
		}).collect(Collectors.toList());
	}

	public static AirbusDTO buildAirbusDTOFromModel(Airbus registaModel, boolean includeTratte) {
		AirbusDTO result = new AirbusDTO(registaModel.getId(), registaModel.getCodice(), registaModel.getDescrizione(),
				registaModel.getDataInizioServizio(), registaModel.getNumeroPasseggeri());
		if (includeTratte)
			result.setTratte(TrattaDTO.createTrattaDTOSetFromModelSet(registaModel.getTratte(), false));
		return result;
	}

	public Airbus buildAirbusModel() {
		return new Airbus(this.id, this.codice, this.descrizione, this.dataInizioServizio, this.numeroPasseggeri);
	}

	

}
