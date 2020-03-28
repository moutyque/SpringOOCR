package org.example.demo.ticket.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import org.example.demo.ticket.business.contract.manager.ProjectManager;
import org.example.demo.ticket.business.impl.DaoFactory;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.projet.Version;
import org.example.demo.ticket.model.exception.NotFoundException;
public class ProjetManagerImpl extends AbstractManager implements ProjectManager{
	

    @Override
	public Projet getProjet(Integer pId) throws NotFoundException {
        // Je n'ai pas encore codé la DAO
        // Je mets juste un code temporaire pour commencer le cours...
    	
    	
    	
        if (pId < 1) {
            throw new NotFoundException("Projet non trouvé : ID=" + pId);
        }
                Projet vProjet = getDaoFactory().getProjetDao().getProject(Long.valueOf(pId));
        vProjet.setNom("Projet n°" + pId);
        return vProjet;
    }


   
    @Override
	public List<Projet> getListProjet() {
        // Je n'ai pas encore codé la DAO
        // Je mets juste un code temporaire pour commencer le cours...
    	return getDaoFactory().getProjetDao().getListProjet();
    }
    
    @Override
    public void insertVersion(Version version) {
    	
    	getDaoFactory().getVersionDao().insertVersion(version);
    	
    	
    }
	
}
