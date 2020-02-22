package org.example.demo.ticket.business;

import org.example.demo.ticket.business.manager.ProjetManager;
import org.example.demo.ticket.business.manager.TicketManager;

public class ManagerFactory {
	
	public ProjetManager getProjetManager() {
        return new ProjetManager();
    }

    public TicketManager getTicketManager() {
        return new TicketManager();
    }

}
