package org.example.demo.ticket.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.mutable.MutableObject;
import org.example.demo.ticket.business.contract.manager.TicketManager;
import org.example.demo.ticket.business.impl.transaction.TransactionHelper;
import org.example.demo.ticket.business.impl.transaction.TransactionUpdateStatus;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.ticket.Bug;
import org.example.demo.ticket.model.bean.ticket.Commentaire;
import org.example.demo.ticket.model.bean.ticket.Evolution;
import org.example.demo.ticket.model.bean.ticket.HistoriqueStatut;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.example.demo.ticket.model.exception.FunctionalException;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.example.demo.ticket.model.exception.TechnicalException;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class TicketManagerImpl extends AbstractManager implements TicketManager {

private PlatformTransactionManager platformTransactionManager;
private TransactionTemplate transactionTemplate;

    public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {    	
	this.platformTransactionManager = platformTransactionManager;
}
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public Ticket getTicket(Long pNumero) throws NotFoundException {
        // Je n'ai pas encore codé la DAO
        // Je mets juste un code temporaire pour commencer le cours...
    	//TODO : Modifier cette fonction pour faire appel à TicketDAO
        if (pNumero < 1L) {
            throw new NotFoundException("Ticket non trouvé : numero=" + pNumero);
        }
        return getDaoFactory().getTicketDao().getTicket(pNumero);
//        Evolution vEvolution = new Evolution(pNumero);
//        vEvolution.setPriorite(10);
//        return vEvolution;
    }

    @Override
	public List<Ticket> getListTicket(RechercheTicket pRechercheTicket) {
        // Je n'ai pas encore codé la DAO
        // Je mets juste un code temporaire pour commencer le cours...
    	//TODO add DAO
        List<Ticket> vList = new ArrayList<>();
        if (pRechercheTicket.getProjetId() != null) {
        	Long projectId = Long.valueOf((long)pRechercheTicket.getProjetId());
        	vList = getDaoFactory().getTicketDao().getTickets(projectId);
            
//        	Projet vProjet = new Projet(pRechercheTicket.getProjetId());
//            for (int vI = 0; vI < 4; vI++) {
//                Ticket vTicket = new Bug((long) pRechercheTicket.getProjetId() * 10 + vI);
//                vTicket.setProjet(vProjet);
//                vList.add(vTicket);
//            }
        } else {
            for (int vI = 0; vI < 9; vI++) {
                Ticket vTicket = new Evolution((long) vI);
                vList.add(vTicket);
            }
        }
        return vList;
    }

    @Override
	public int getCountTicket(RechercheTicket pRechercheTicket) {
        // Je n'ai pas encore codé la DAO
        // Je mets juste un code temporaire pour commencer le cours...
        return 42;
    }

    @Override
    public void changerStatut(Ticket pTicket, TicketStatut pNewStatut,
                              Utilisateur pUtilisateur, Commentaire pCommentaire) throws FunctionalException{
    	TransactionUpdateStatus statutsTransaction = new TransactionUpdateStatus(pTicket,pNewStatut,getDaoFactory());
    	
    	TransactionHelper transactionHelper = new TransactionHelper();
    	//Version où tout est géré pas spring
    	
//    	transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//            @Override
//         
//   protected void doInTransactionWithoutResult(TransactionStatus pTransactionStatus) {
//                TicketStatut vOldStatut = pTicket.getStatut();
//            	try {
//            		statutsTransaction.updateStatus();
//				} catch (TechnicalException e) {
//					 pTransactionStatus.setRollbackOnly();
//			         pTicket.setStatut(vOldStatut);
//				}
//            }
//        });
    	
//    	HistoriqueStatut vHistorique =
//    			transactionTemplate.execute(new TransactionCallback<HistoriqueStatut>() {
//    		        @Override
//    		        public HistoriqueStatut doInTransaction(TransactionStatus
//    		                                                    pTransactionStatus) {
//    		        	return statutsTransaction.updateHistory();
//    		        }
//    		    });
    	
    	
    	MutableObject<TransactionStatus> vStatus = transactionHelper.beginTransaction();
        try {
            // le traitement transactionnel ...
            //throw new FunctionalException("...");
        	HistoriqueStatut vHistorique = statutsTransaction.updateHistory();
            transactionHelper.commit(vStatus);
         
        } finally {
            transactionHelper.rollback(vStatus);
        }
    	
    }

	

	
}
