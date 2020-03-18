package org.example.demo.ticket.business.contract.manager;

import java.util.List;

import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.exception.NotFoundException;

public interface ProjectManager {
	public Projet getProjet(Integer pId) throws NotFoundException;
	public List<Projet> getListProjet();
}
