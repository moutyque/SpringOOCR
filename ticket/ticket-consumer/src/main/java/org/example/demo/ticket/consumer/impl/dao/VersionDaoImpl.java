package org.example.demo.ticket.consumer.impl.dao;

import org.apache.commons.lang3.mutable.MutableObject;
import org.example.demo.ticket.consumer.contract.dao.VersionDao;
import org.example.demo.ticket.consumer.impl.transaction.TransactionHelper;
import org.example.demo.ticket.model.bean.projet.Version;
import org.example.demo.ticket.model.exception.FunctionalException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.TransactionStatus;

public class VersionDaoImpl extends AbstractDaoImpl implements VersionDao {

	@Override
	public int insertVersion(Version version) {
		TransactionHelper transactionHelper = getTransaction();
        MutableObject<TransactionStatus> vStatus = transactionHelper.beginTransaction();
        int rInt = 0;
        try {
            // le traitement transactionnel ...
        	MapSqlParameterSource vParams = new MapSqlParameterSource();

    		StringBuilder sb = new StringBuilder();
    		sb.append("INSERT INTO public.version (projet_id , numero) values (:projet_id , :numero);");

    		String vSQL = sb.toString();
             vParams.addValue("projet_id", version.getProjet().getId());
             vParams.addValue("numero", version.getNumero());
             
             NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
             rInt = vJdbcTemplate.update(vSQL, vParams);
            transactionHelper.commit(vStatus);
        } finally {
            transactionHelper.rollback(vStatus);
       }
        
        return rInt;

         
	}

}
