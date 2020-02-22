package org.example.demo.ticket.consumer.impl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.example.demo.ticket.consumer.contract.dao.TicketDao;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Classe d'implémentation de {@link TicketDao}.
 */

public class TicketDaoImpl extends AbstractDaoImpl implements TicketDao {
	  final class TicketStatutRW implements RowMapper<TicketStatut> {
		@Override
		public TicketStatut mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		    TicketStatut vTicketStatut = new TicketStatut(pRS.getInt("id"));
		    vTicketStatut.setLibelle(pRS.getString("libelle"));
		    return vTicketStatut;
		}
	}


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

	        RowMapper<TicketStatut> vRowMapper = new TicketStatutRW();

	        List<TicketStatut> vListStatut = vJdbcTemplate.query(vSQL, vRowMapper);

	        return vListStatut;
	    }
}
