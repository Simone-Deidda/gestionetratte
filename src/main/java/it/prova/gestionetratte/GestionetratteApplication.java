package it.prova.gestionetratte;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.StatoTratta;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.AirbusService;
import it.prova.gestionetratte.service.TrattaService;

@SpringBootApplication
public class GestionetratteApplication implements CommandLineRunner {
	@Autowired
	private TrattaService trattaService;
	@Autowired
	private AirbusService airbusService;

	public static void main(String[] args) {
		SpringApplication.run(GestionetratteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String codiceAirbus = "0014898";
		Airbus airbus = airbusService.findByCodice(codiceAirbus);

		if (airbus == null) {
			airbus = new Airbus(codiceAirbus, "descrizione airbus " + codiceAirbus, LocalDate.of(1980, 2, 1), 75);
			airbusService.inserisciNuovo(airbus);
		}
		
		String codiceTratta = "ROMMIL010122";
		Tratta romaMilano = trattaService.findByCodice(codiceTratta);
		if (romaMilano == null) {
			romaMilano = new Tratta(codiceTratta, "tratta che parte da Roma e arriva a Milano",
					LocalDate.of(2020, 9, 18), LocalTime.of(13, 17), LocalTime.of(12, 25), StatoTratta.ATTIVA);
			romaMilano.setAirbus(airbus);
			trattaService.inserisciNuovo(romaMilano);
		}
	}

}
