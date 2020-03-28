package org.example.demo.ticket.business.impl;

import org.example.demo.ticket.consumer.contract.dao.ProjectDao;
import org.example.demo.ticket.consumer.contract.dao.TicketDao;
import org.example.demo.ticket.consumer.contract.dao.VersionDao;

public class DaoFactory {
private ProjectDao projetDao;
private TicketDao ticketDao;
private VersionDao versionDao;

public ProjectDao getProjetDao() {
	return projetDao;
}
public void setProjetDao(ProjectDao projetDao) {
	this.projetDao = projetDao;
}
public TicketDao getTicketDao() {
	return ticketDao;
}
public void setTicketDao(TicketDao ticketDao) {
	this.ticketDao = ticketDao;
}
public VersionDao getVersionDao() {
	return versionDao;
}
public void setVersionDao(VersionDao versionDao) {
	this.versionDao = versionDao;
}




}
