package dev.paie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:cotisations-imposables.xml", "classpath:cotisations-non-imposables.xml",
		"classpath:entreprise.xml", "classpath:entreprise.xml", "classpath:grade.xml",
		"classpath:profils-renumeration.xml" })
public class JeuDeDonneesConfig {

}
