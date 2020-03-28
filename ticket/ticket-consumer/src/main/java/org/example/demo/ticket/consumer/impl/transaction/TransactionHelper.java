package org.example.demo.ticket.consumer.impl.transaction;

import org.apache.commons.lang3.mutable.MutableObject;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionHelper {


	private PlatformTransactionManager platformTransactionManager;

	public PlatformTransactionManager getPlatformTransactionManager() {
		return platformTransactionManager;
	}

	public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}

	private DefaultTransactionDefinition definition = new DefaultTransactionDefinition();

	public MutableObject<TransactionStatus> beginTransaction() {
		return beginTransaction(null);
	}

	public MutableObject<TransactionStatus> beginTransaction(DefaultTransactionDefinition pDefinition) {
		DefaultTransactionDefinition vDefinition = pDefinition != null ? pDefinition : definition;
		TransactionStatus vStatus = platformTransactionManager.getTransaction(pDefinition);
		return new MutableObject<TransactionStatus>(vStatus);
	}

	public void commit(MutableObject<TransactionStatus> pStatus) {
		if (pStatus != null && pStatus.getValue() != null) {
			platformTransactionManager.commit(pStatus.getValue());
			pStatus.setValue(null);
		}
	}

	public void rollback(MutableObject<TransactionStatus> pStatus) {
		if (pStatus != null && pStatus.getValue() != null) {
			platformTransactionManager.rollback(pStatus.getValue());
			pStatus.setValue(null);
		}
		
	}
}

