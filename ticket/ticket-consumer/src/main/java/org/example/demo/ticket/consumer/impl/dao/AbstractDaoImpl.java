package org.example.demo.ticket.consumer.impl.dao;

import javax.sql.DataSource;

import org.example.demo.ticket.consumer.impl.transaction.TransactionHelper;

public abstract class AbstractDaoImpl {

    private DataSource dataSource;
    private TransactionHelper transaction;
    
    public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected DataSource getDataSource() {
        return dataSource;
    }

	public TransactionHelper getTransaction() {
		return transaction;
	}

	public void setTransaction(TransactionHelper transaction) {
		this.transaction = transaction;
	}

    // ...
}