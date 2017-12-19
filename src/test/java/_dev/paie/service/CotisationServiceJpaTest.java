package _dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.service.CotisationService;
import dev.paie.service.CotisationServiceJpa;
import dev.paie.spring.DataSourceMySQLConfig;

@ContextConfiguration(classes = { ServicesConfig.class, DataSourceMySQLConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {
	@Autowired
	private CotisationService cotisationService;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {

		Cotisation cotisation = new Cotisation();

		String code = "C1";
		cotisation.setCode(code);
		cotisation.setLibelle("cotisation 1");
		cotisation.setTauxPatronal(new BigDecimal("15.20"));
		cotisation.setTauxSalarial(new BigDecimal("17.56"));
		cotisationService.sauvegarder(cotisation);
		
		List<Cotisation> listeCotisation = cotisationService.lister();
		
		Cotisation cotisation1 = listeCotisation.stream().filter(p->p.getCode().equals(code)).findFirst().orElse(null);
		assertThat(cotisation1).isNotNull();
		
		cotisation1.setCode("C001");
		cotisation1.setLibelle("Cotisation01");
		cotisation1.setTauxPatronal(new BigDecimal("25.02"));
		cotisation1.setTauxSalarial(new BigDecimal("14.23"));
		
		cotisationService.mettreAJour(cotisation1);
		
		Cotisation cotisation2 = cotisationService.lister().stream().filter(p -> p.getCode().equals("C001")).findFirst().orElse(null);
		assertThat(cotisation2).isNotNull();
		assertThat(cotisation2.getLibelle()).isEqualTo("Cotisation01");
		assertThat(cotisation2.getTauxPatronal()).isEqualTo(new BigDecimal("25.02"));
		assertThat(cotisation2.getTauxSalarial()).isEqualTo(new BigDecimal("14.23"));
		cotisationService.mettreAJour(cotisation2);
		

	}
}