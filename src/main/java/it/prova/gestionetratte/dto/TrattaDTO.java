package it.prova.gestionetratte.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetratte.model.StatoTratta;
import it.prova.gestionetratte.model.Tratta;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrattaDTO {

	private Long id;
	@NotBlank(message = "{tratta.codice.notblank}")
	private String codice;
	@NotBlank(message = "{tratta.descrizione.notblank}")
	private String descrizione;
	@NotNull(message = "{data.notnull}")
	private LocalDate data;
	@NotNull(message = "{oraatterraggio.notnull}")
	private LocalTime oraAtterraggio;
	@NotNull(message = "{oradecollo.notnull}")
	private LocalTime oraDecollo;
	@NotNull(message = "{stato.notnull}")
	private StatoTratta stato;

	@JsonIgnoreProperties(value = { "tratte" })
	@NotNull(message = "{airbus.notnull}")
	private AirbusDTO airbus;

	public TrattaDTO() {
	}

	public TrattaDTO(Long id, String codice, String descrizione, LocalDate data, LocalTime oraAtterraggio,
			LocalTime oraDecollo, StatoTratta stato) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.data = data;
		this.oraAtterraggio = oraAtterraggio;
		this.oraDecollo = oraDecollo;
		this.stato = stato;
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getOraAtterraggio() {
		return oraAtterraggio;
	}

	public void setOraAtterraggio(LocalTime oraAtterraggio) {
		this.oraAtterraggio = oraAtterraggio;
	}

	public LocalTime getOraDecollo() {
		return oraDecollo;
	}

	public void setOraDecollo(LocalTime oraDecollo) {
		this.oraDecollo = oraDecollo;
	}

	public StatoTratta getStato() {
		return stato;
	}

	public void setStato(StatoTratta stato) {
		this.stato = stato;
	}

	public AirbusDTO getAirbusDTO() {
		return airbus;
	}

	public void setAirbusDTO(AirbusDTO airbusDTO) {
		this.airbus = airbusDTO;
	}

	public static Set<TrattaDTO> createTrattaDTOSetFromModelSet(Set<Tratta> tratte, boolean includeAirbus) {
		return tratte.stream().map(entity -> {
			return TrattaDTO.buildTrattaDTOFromModel(entity, includeAirbus);
		}).collect(Collectors.toSet());
	}

	public static TrattaDTO buildTrattaDTOFromModel(Tratta trattaModel, boolean includeAirbus) {
		TrattaDTO result = new TrattaDTO(trattaModel.getId(), trattaModel.getCodice(), trattaModel.getDescrizione(),
				trattaModel.getData(), trattaModel.getOraAtterraggio(), trattaModel.getOraDecollo(),
				trattaModel.getStato());

		if (includeAirbus)
			result.setAirbusDTO(AirbusDTO.airbus(trattaModel.getAirbus(), false));

		return result;
	}

	public static List<TrattaDTO> createTrattaDTOSetFromModelList(List<Tratta> list, boolean includeAirbus) {
		return list.stream().map(entity -> {
			return TrattaDTO.buildTrattaDTOFromModel(entity, includeAirbus);
		}).collect(Collectors.toList());
	}

	public Tratta buildTrattaModel() {
		Tratta result = new Tratta(this.id, this.codice, this.descrizione, this.data, this.oraAtterraggio, this.oraDecollo, this.stato);
		if (this.airbus != null) {
			result.setAirbus(this.airbus.buildAirbusModel());
		}
		return result;
	}

}
