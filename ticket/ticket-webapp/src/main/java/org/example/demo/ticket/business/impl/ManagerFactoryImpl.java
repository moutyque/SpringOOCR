package org.example.demo.ticket.business.impl;


import org.example.demo.ticket.business.contract.ManagerFactory;
import org.example.demo.ticket.business.manager.contract.ProjectManager;
import org.example.demo.ticket.business.manager.contract.TicketManager;

public class ManagerFactoryImpl implements ManagerFactory {
    private ProjectManager projetManager;
    private TicketManager ticketManager;
    
	@Override
	public ProjectManager getProjetManager() {
        return projetManager;
    }

    @Override
	public TicketManager getTicketManager() {
        return ticketManager;
    }
    
    // Ajout d'un setter pour l'attribut projetManager
    @Override
	public void setProjetManager(ProjectManager pProjetManager) {
        projetManager = pProjetManager;
    }
    // Ajout d'un setter pour l'attribut ticketManager
    @Override
	public void setTicketManager(TicketManager pTicketManager) {
    	ticketManager = pTicketManager;
    }

}
