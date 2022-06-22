package it.prova.gestionetratte.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Tratta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "codice")
	private String codice;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "data")
	private LocalDate data;
	@Column(name = "oraatterraggio")
	private LocalTime oraAtterraggio;
	@Column(name = "oradecollo")
	private LocalTime oraDecollo;
	@Column(name = "stato")
	private StatoTratta stato;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "airbus_id", nullable = false)
	private Airbus airbus;

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

	public Airbus getAirbus() {
		return airbus;
	}

	public void setAirbus(Airbus airbus) {
		this.airbus = airbus;
	}

}
