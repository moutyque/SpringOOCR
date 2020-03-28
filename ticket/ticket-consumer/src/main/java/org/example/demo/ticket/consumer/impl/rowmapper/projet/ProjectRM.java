package org.example.demo.ticket.consumer.impl.rowmapper.projet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.example.demo.ticket.consumer.impl.rowmapper.utilisateur.UtilisateurRM;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

public class ProjectRM implements RowMapper<Projet>{

	@Override
	public Projet mapRow(ResultSet rs, int rowNum) throws SQLException {
			Projet vProjet = new Projet(rs.getInt("id"));
			vProjet.setNom(rs.getString("nom"));
			vProjet.setDateCreation(rs.getDate("date_creation"));
			vProjet.setCloture(rs.getBoolean("cloture"));
			
			
			Utilisateur user = new Utilisateur();
			user.setId(rs.getInt("responsable_id"));
			user.setNom(rs.getString("nom"));
			user.setPrenom(rs.getString("prenom"));

			vProjet.setResponsable(user);
								
		    return vProjet;

	}

}
