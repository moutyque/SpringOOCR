package org.example.demo.ticket.business.impl.manager;

import org.example.demo.ticket.business.impl.DaoFactory;

public abstract class AbstractManager {
	private static DaoFactory daoFactory;

	public static DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public static void setDaoFactory(DaoFactory daoFactory) {
		AbstractManager.daoFactory = daoFactory;
	}
	
	
}
