package org.example.demo.ticket.business.contract.manager;

import java.util.List;

import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;

public interface TicketManager {
	public Ticket getTicket(Long pNumero) throws NotFoundException;
	public List<Ticket> getListTicket(RechercheTicket pRechercheTicket);
	int getCountTicket(RechercheTicket pRechercheTicket);
}
