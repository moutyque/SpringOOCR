package org.example.demo.ticket.consumer.impl.rowmapper.utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

public class UtilisateurRM implements RowMapper<Utilisateur> {

	@Override
	public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(rs.getInt("id"));
		utilisateur.setNom(rs.getString("nom"));
		utilisateur.setPrenom(rs.getString("prenom"));

		return utilisateur;
	}

}
