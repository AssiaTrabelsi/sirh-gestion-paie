package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements

		CalculerRemunerationService {

	@Autowired
	private PaieUtils paieUtils;

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {

		ResultatCalculRemuneration resultat = new ResultatCalculRemuneration();

		Grade grade = bulletin.getRemunerationEmploye().getGrade();

		BigDecimal salaireDeBase = grade.getNbHeuresBase().multiply(grade.getTauxBase());
		String salairedeBaseStr = paieUtils.formaterBigDecimal(salaireDeBase);
		BigDecimal salaireDeBaseArrondi = new BigDecimal(salairedeBaseStr);
		resultat.setSalaireDeBase(salairedeBaseStr);

		BigDecimal salaireBrut = salaireDeBaseArrondi.add(bulletin.getPrimeExceptionnelle());
		String salaireBrutStr = paieUtils.formaterBigDecimal(salaireBrut);
		BigDecimal salaireBrutArrondi = new BigDecimal(salaireBrutStr);
		resultat.setSalaireBrut(salaireBrutStr);

		List<Cotisation> cotisationNonImposable = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables();

		BigDecimal totalRetenueSalariale = cotisationNonImposable.stream().filter(c -> c.getTauxSalarial() != null)
				.map(c -> c.getTauxSalarial().multiply(salaireBrutArrondi)).reduce((d1, d2) -> d1.add(d2))
				.orElse(new BigDecimal("0.00"));
		
		resultat.setTotalRetenueSalarial(paieUtils.formaterBigDecimal(totalRetenueSalariale));

		
		BigDecimal totalCotisationPatronales = cotisationNonImposable.stream().filter(t->t.getTauxPatronal()!=null)
		.map(t->t.getTauxPatronal().multiply(salaireBrutArrondi)).reduce((c1, c2) -> c1.add(c2)).orElse(new BigDecimal("0.00"));
		resultat.setTotalCotisationsPatronales(paieUtils.formaterBigDecimal(totalCotisationPatronales));
		
		
		BigDecimal netImposable = salaireBrutArrondi.subtract(new BigDecimal(resultat.getTotalRetenueSalarial())) ;
		resultat.setNetImposable(paieUtils.formaterBigDecimal(netImposable));
		
		
		
		List<Cotisation> cotisationImposable = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsImposables();
		
		
		
		BigDecimal totalRetenueSalariale1 = cotisationImposable.stream().filter(b -> b.getTauxSalarial() != null)
				.map(b -> b.getTauxSalarial().multiply(salaireBrutArrondi)).reduce((s1, s2) -> s1.add(s2))
				.orElse(new BigDecimal("0.00"));
		
		
		BigDecimal netApayer= netImposable.subtract(totalRetenueSalariale1);
		resultat.setNetAPayer(paieUtils.formaterBigDecimal(netApayer));
		
		return resultat;

	}

}
