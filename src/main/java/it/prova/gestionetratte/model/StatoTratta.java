package it.prova.gestionetratte.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;


public enum StatoTratta {
	ATTIVA, CONCLUSA, ANNULLATA;
	
	@JsonCreator
	public static StatoTratta getTrattaFromCode(String input) {
		if(StringUtils.isBlank(input))
			return null;
		
		for (StatoTratta trattaItem : StatoTratta.values()) {
			if (trattaItem.equals(StatoTratta.valueOf(input))) {
				return trattaItem;
			}
		}
		return null;
	}
}
