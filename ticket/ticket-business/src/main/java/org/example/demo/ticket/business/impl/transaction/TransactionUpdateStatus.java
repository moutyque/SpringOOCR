package org.example.demo.ticket.business.impl.transaction;

import org.example.demo.ticket.business.impl.DaoFactory;
import org.example.demo.ticket.model.bean.ticket.HistoriqueStatut;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.example.demo.ticket.model.exception.TechnicalException;

public class TransactionUpdateStatus {
	private Ticket ticket;
	private TicketStatut ticketStatus;
	private DaoFactory daoFactory;
	
	public TransactionUpdateStatus(Ticket ticket,TicketStatut ticketStatus,DaoFactory daoFactory) {
		this.ticket = ticket;
		this.ticketStatus = ticketStatus;
		this.daoFactory = daoFactory;
	}
	
	
	public void updateStatus() throws TechnicalException {
		this.ticket.setStatut(this.ticketStatus);
		this.daoFactory.getTicketDao().updateTicket(this.ticket);
        // TODO Ajout de la ligne d'historique + commentaire ...
	}

	public HistoriqueStatut updateHistory() {
		this.ticket.setStatut(this.ticketStatus);
		this.daoFactory.getTicketDao().updateTicket(this.ticket);

         HistoriqueStatut vHistoriqueStatut = new HistoriqueStatut();
         // TODO Ajout de la ligne d'historique + commentaire ...
         return vHistoriqueStatut;
	}
}
