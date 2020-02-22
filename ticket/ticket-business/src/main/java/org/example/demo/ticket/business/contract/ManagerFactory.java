package org.example.demo.ticket.business.contract;

import org.example.demo.ticket.business.contract.manager.ProjectManager;
import org.example.demo.ticket.business.contract.manager.TicketManager;

public interface ManagerFactory {

	void setTicketManager(TicketManager pTicketManager);

	void setProjetManager(ProjectManager pProjetManager);

	TicketManager getTicketManager();

	ProjectManager getProjetManager();

}
