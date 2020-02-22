package org.example.demo.ticket.consumer.impl.rowmapper.ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.example.demo.ticket.consumer.impl.rowmapper.projet.ProjectRM;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.springframework.jdbc.core.RowMapper;

public class TicketRM implements RowMapper<Ticket> {
	@Override
	public Ticket mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		Ticket vTicket = new Ticket() {};
		
	    vTicket.setDate(pRS.getDate("date"));
	    vTicket.setDescription(pRS.getString("description"));
	    vTicket.setNumero(pRS.getLong("numero"));
	    vTicket.setStatut(new TicketStatutRM().mapRow(pRS, pRowNum));
	    //TODO : Raw mapper of utilisateurs et projet
	    vTicket.setProjet(new ProjectRM().mapRow(pRS, pRowNum));
	    vTicket.setTitre(pRS.getString("titre"));
	    
	    return vTicket;
	}
	/*
	 *   private Long numero;
    private String titre;
    private Date date;
    private String description;
    private Projet projet;
    private TicketStatut statut;
	 */

}
