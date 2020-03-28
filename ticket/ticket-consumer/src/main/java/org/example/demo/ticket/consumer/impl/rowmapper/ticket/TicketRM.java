package org.example.demo.ticket.consumer.impl.rowmapper.ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.example.demo.ticket.consumer.impl.rowmapper.projet.ProjectRM;
import org.example.demo.ticket.consumer.impl.rowmapper.utilisateur.UtilisateurRM;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

public class TicketRM implements RowMapper<Ticket> {
	@Override
	public Ticket mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		Ticket vTicket = new Ticket() {};
		
	    vTicket.setDate(pRS.getDate("date"));
	    vTicket.setDescription(pRS.getString("description"));
	    vTicket.setNumero(pRS.getLong("numero"));
	    
	   TicketStatut ts = new TicketStatut();
	   ts.setId(pRS.getInt("statutID"));
	   ts.setLibelle(pRS.getString("libelle"));
	    vTicket.setStatut(ts);
	    //TODO : Raw mapper of utilisateurs et projet
	    
	    Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(pRS.getInt("auteur_id"));
		utilisateur.setNom(pRS.getString("userNom"));
		utilisateur.setPrenom(pRS.getString("userPrenom"));
		
	    Projet vProjet = new Projet(pRS.getInt("projetID"));
		vProjet.setNom(pRS.getString("nom"));
		vProjet.setDateCreation(pRS.getDate("date_creation"));
		vProjet.setCloture(pRS.getBoolean("cloture"));
		vProjet.setResponsable(utilisateur);
	    
	    vTicket.setProjet(vProjet);
	    vTicket.setTitre(pRS.getString("titre"));
	    
	    return vTicket;
	}

}
