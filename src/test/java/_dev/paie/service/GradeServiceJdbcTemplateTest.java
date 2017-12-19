package _dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;
import dev.paie.service.GradeService;
import dev.paie.spring.DataSourceMySQLConfig;

@ContextConfiguration(classes = { ServicesConfig.class, DataSourceMySQLConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {

	@Autowired
	private GradeService gradeService;

	@Autowired
	private DataSource dataSource;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {

		// creation de nouveau grade
		Grade nouveauGrade = new Grade();
		String code = "G1";
		nouveauGrade.setCode(code);
		nouveauGrade.setNbHeuresBase(new BigDecimal("2.5"));
		nouveauGrade.setTauxBase(new BigDecimal("7.50"));

		gradeService.sauvegarder(nouveauGrade);

		List<Grade> listeGrade = gradeService.lister();

		Grade grade1 = listeGrade.stream().filter(p -> p.getCode().equals(code)).findFirst().orElse(null);
		assertThat(grade1).isNotNull();
		grade1.setCode("G2");
		grade1.setNbHeuresBase(new BigDecimal("46.23"));
		grade1.setTauxBase(new BigDecimal("15.24"));
		gradeService.mettreAJour(grade1);

		List<Grade> listeGrade2 = gradeService.lister();
		Grade grade2 = listeGrade2.stream().filter(p -> p.getCode().equals("G2")).findFirst().orElse(null);
		assertThat(grade2).isNotNull();
		assertThat(grade2.getNbHeuresBase()).isEqualTo(new BigDecimal("46.23"));
		assertThat(grade2.getTauxBase()).isEqualTo(new BigDecimal("15.24"));

		gradeService.mettreAJour(grade2);

	}

	@After
	public void afterEach() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "Delete from grade where code LIKE 'G%';";
		jdbcTemplate.update(sql);

	}

}
