package dev.paie.repository;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;
import dev.paie.spring.DataSourceMySQLConfig;

@ContextConfiguration(classes = { ServicesConfig.class, DataSourceMySQLConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {

	@Autowired
	private AvantageRepository avantageRepository;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {

		Avantage avantage = new Avantage();
		avantage.setCode("av01");
		avantage.setNom("Avantage1");
		avantage.setMontant(new BigDecimal("85.25"));

		avantageRepository.save(avantage);

		Avantage avantage1 = avantageRepository.findAll().stream().filter(p -> p.getCode().equals("av01")).findFirst()
				.orElse(null);
		
		assertThat(avantage1.getCode()).isEqualTo("av01");
		assertThat(avantage1.getNom()).isEqualTo("Avantage1");
		assertThat(avantage1.getMontant()).isEqualTo(new BigDecimal("85.25"));
		
		
	
		avantage1.setCode("av02");
		avantage1.setNom("Avantage2");
		avantage1.setMontant(new BigDecimal("58.21"));
		int id = avantage1.getId();
		avantageRepository.save(avantage1);
		
		
		Avantage avantage2 = avantageRepository.findOne(id) ;
		assertThat(avantage2.getCode()).isEqualTo("av02");
		assertThat(avantage2.getNom()).isEqualTo("Avantage2");
		assertThat(avantage2.getMontant()).isEqualTo(new BigDecimal("58.21"));
		
		Avantage avantage3 = avantageRepository.findByCode("av02");
		assertThat(avantage3).isNotNull(); 
		
		
		
		
	}
}
