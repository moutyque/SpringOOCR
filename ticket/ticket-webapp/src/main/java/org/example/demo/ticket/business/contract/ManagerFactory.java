package org.example.demo.ticket.business.contract;

import org.example.demo.ticket.business.manager.contract.ProjectManager;
import org.example.demo.ticket.business.manager.contract.TicketManager;

public interface ManagerFactory {

	void setTicketManager(TicketManager pTicketManager);

	void setProjetManager(ProjectManager pProjetManager);

	TicketManager getTicketManager();

	ProjectManager getProjetManager();

}
