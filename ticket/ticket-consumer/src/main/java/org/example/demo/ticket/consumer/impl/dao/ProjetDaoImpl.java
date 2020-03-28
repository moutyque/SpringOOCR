package org.example.demo.ticket.consumer.impl.dao;


import java.util.List;

import org.example.demo.ticket.consumer.contract.dao.ProjectDao;
import org.example.demo.ticket.consumer.impl.rowmapper.projet.ProjectRM;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


public class ProjetDaoImpl extends AbstractDaoImpl implements ProjectDao {
	@Override
	public Projet getProject(Long id) {
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * ");
		sb.append("FROM public.projet A ");
		sb.append("INNER JOIN utilisateur B on A.responsable_id = B.id ");
		sb.append("WHERE 1=1  AND A.id = :projet");
		
		String vSQL = sb.toString();
         vParams.addValue("projet", id);

         NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
         RowMapper<Projet> vRowMapper  = new ProjectRM();
         List<Projet> projets = vJdbcTemplate.query(vSQL,vParams, vRowMapper);
	     return projets.get(0);
         
      
	        
	}
	
	@Override
	public List<Projet> getListProjet() {
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * ");
		sb.append("FROM public.projet A ");
		sb.append("INNER JOIN utilisateur B on A.responsable_id = B.id ");
		sb.append("WHERE 1=1");
		
		String vSQL = sb.toString();

         NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
         RowMapper<Projet> vRowMapper  = new ProjectRM();
         List<Projet> projets = vJdbcTemplate.query(vSQL,vParams, vRowMapper);
	     return projets;
         
      
	        
	}


}
