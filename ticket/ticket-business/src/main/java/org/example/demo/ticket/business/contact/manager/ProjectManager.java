package org.example.demo.ticket.business.contact.manager;

import java.util.List;

import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.exception.NotFoundException;

public interface ProjectManager {
	 /**
     * Renvoie le projet demandé
     *
     * @param pId l'identifiant du projet
     * @return Le {@link Projet}
     * @throws NotFoundException Si le projet n'est pas trouvé
     */
	List<Projet> getListProjet();

	Projet getProjet(Integer pId) throws NotFoundException;

}
