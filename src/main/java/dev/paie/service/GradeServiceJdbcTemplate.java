package dev.paie.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;

@Service
public class GradeServiceJdbcTemplate implements GradeService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void sauvegarder(Grade nouveauGrade) {
		String sql = "insert into grade (code,nbHeureBase,tauxBase) VALUES (?,?,?)";
		this.jdbcTemplate.update(sql, nouveauGrade.getCode(), nouveauGrade.getNbHeuresBase(),
				nouveauGrade.getTauxBase());
	}

	@Override
	public void mettreAJour(Grade grade) {

		String sql = "UPDATE grade SET code = ?,nbHeureBase = ?,tauxBase = ? WHERE id = ?;";
		this.jdbcTemplate.update(sql, grade.getCode(), grade.getNbHeuresBase(), grade.getTauxBase(), grade.getId());

	}

	@Override
	public List<Grade> lister() {

		String sql = "SELECT * from grade";
		return this.jdbcTemplate.query(sql, new GradeMapper());
	}

}
