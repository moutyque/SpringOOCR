package org.example.demo.ticket.consumer.impl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.example.demo.ticket.consumer.contract.dao.TicketDao;
import org.example.demo.ticket.consumer.impl.rowmapper.ticket.TicketRM;
import org.example.demo.ticket.consumer.impl.rowmapper.ticket.TicketStatutRM;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Classe d'implémentation de {@link TicketDao}.
 */

public class TicketDaoImpl extends AbstractDaoImpl implements TicketDao {
//	  final class TicketStatutRW implements RowMapper<TicketStatut> {
//		@Override
//		public TicketStatut mapRow(ResultSet pRS, int pRowNum) throws SQLException {
//		    TicketStatut vTicketStatut = new TicketStatut(pRS.getInt("id"));
//		    vTicketStatut.setLibelle(pRS.getString("libelle"));
//		    return vTicketStatut;
//		}
//	}


	@Override
	    public int getCountTicket(RechercheTicket pRechercheTicket) {
	        MapSqlParameterSource vParams = new MapSqlParameterSource();

	        StringBuilder vSQL = new StringBuilder("SELECT COUNT(*) FROM ticket WHERE 1=1");

	        if (pRechercheTicket != null) {
	            if (pRechercheTicket.getAuteurId() != null) {
	                vSQL.append(" AND auteur_id = :auteur_id");
	                vParams.addValue("auteur_id", pRechercheTicket.getAuteurId());
	            }
	            if (pRechercheTicket.getProjetId() != null) {
	                vSQL.append(" AND projet_id = :projet_id");
	                vParams.addValue("projet_id", pRechercheTicket.getProjetId());
	            }
	        }

	        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
	        return vJdbcTemplate.queryForObject(vSQL.toString(), vParams, Integer.class);
	    }
	  
	  
	    @Override
	    public List<TicketStatut> getListStatut() {
	        String vSQL = "SELECT * FROM public.statut";

	        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());

	        RowMapper<TicketStatut> vRowMapper = new TicketStatutRM();

	        List<TicketStatut> vListStatut = vJdbcTemplate.query(vSQL, vRowMapper);

	        return vListStatut;
	    }


		@Override
		public Ticket getTicket(Long id) {
			MapSqlParameterSource vParams = new MapSqlParameterSource();
			String vSQL = "SELECT * FROM public.ticket WHERE 1=1  AND numero = :numero";
             vParams.addValue("numero", id);
             NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
 	        return vJdbcTemplate.queryForObject(vSQL.toString(), vParams, Ticket.class);
		}
		
		
		@Override
		public List<Ticket> getTickets(Long projectId) {
			MapSqlParameterSource vParams = new MapSqlParameterSource();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT *,");
			sb.append("B.id as projetID,");
			sb.append("C.id as statutID, ");
			sb.append("D.nom as userNom, ");
			sb.append("D.prenom as userPrenom ");
			sb.append("FROM public.ticket A ");
			sb.append("INNER JOIN projet B ");
			sb.append("on A.projet_id = B.id ");
			sb.append("INNER JOIN statut C ");
			sb.append("on A.statut_actuel_id = C.id ");
			sb.append("INNER JOIN utilisateur D ");
			sb.append("on A.auteur_id = D.id ");
			sb.append("WHERE 1=1 AND projet_id = :projet_id;");

			
			String vSQL = sb.toString();//"SELECT * FROM public.ticket WHERE 1=1  AND projet_id = :projet_id";
             vParams.addValue("projet_id", projectId);
             
             NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
             RowMapper<Ticket> vRowMapper  = new TicketRM();
             // vJdbcTemplate.queryForObject(vSQL.toString(), vParams, Ticket.class);
 	        return vJdbcTemplate.query(vSQL,vParams, vRowMapper);
		}
	
		@Override
		public void updateTicket(Ticket pTicket) {
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE ticket SET numero = :numero");
			sb.append(",titre=:titre");
			sb.append(",date:=date");
			sb.append(",description:=description");
			sb.append(",statut_actuel_id:=statut_actuel_id");
			sb.append(",auteur_id:=auteur_id");
			sb.append(",projet_id:=projet_id");
			sb.append("WHERE numero = :numero");
			 String vSQL = sb.toString();
			 

		        BeanPropertySqlParameterSource vParams = new BeanPropertySqlParameterSource(pTicket);
		        vParams.registerSqlType("numero", Types.INTEGER);
		        vParams.registerSqlType("titre", Types.VARCHAR);
		        vParams.registerSqlType("date", Types.DATE);
		        vParams.registerSqlType("description", Types.VARCHAR);
		        vParams.registerSqlType("statut_actuel_id", Types.INTEGER);
		        vParams.registerSqlType("auteur_id", Types.INTEGER);
		        vParams.registerSqlType("projet_id", Types.INTEGER);
		        
		        
		        NamedParameterJdbcTemplate
		          vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		        vJdbcTemplate.update(vSQL, vParams);
			
		}
}
