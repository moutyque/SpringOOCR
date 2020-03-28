package org.example.demo.ticket.batch;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.projet.Version;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.example.demo.ticket.model.exception.TechnicalException;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.example.demo.ticket.business.contract.ManagerFactory;
/**
 * Classe Principale de lancement des Batches.
 *
 * @author lgu
 */
public class Main {

    /** Logger pour la classe */
    private static final Log LOGGER = LogFactory.getLog(Main.class);

    ManagerFactory manager;

    public ManagerFactory getManager() {
		return manager;
	}

	public void setManager(ManagerFactory manager) {
		this.manager = manager;
	}

	/**
     * The entry point of application.
     *
     * @param pArgs the input arguments
     * @throws TechnicalException sur erreur technique
     */
    public static void main(String[] pArgs) throws TechnicalException {
     
    	 ApplicationContext vApplicationContext
         = new ClassPathXmlApplicationContext("classpath:/org/example/demo/ticket/batch/batchContext.xml");

     // Il est possible de récupérer un bean dans ce contexte :
     ManagerFactory vManagerFactory
         = vApplicationContext.getBean("managerFactory", ManagerFactory.class);
     
        try {
            if (pArgs.length < 1) {
                throw new TechnicalException("Veuillez préciser le traitement à effectuer !");
            }
            int projectId =-1;
            String vTraitementId = pArgs[0];
            if(pArgs.length>1)  projectId = Integer.parseInt(pArgs[1]);
            
            
            
            Path filePath = createOutputFile();
            if ("ExportTicketStatus".equals(vTraitementId)) {
                LOGGER.info("Execution du traitement : ExportTicketStatus");
              
               
                RechercheTicket pRechercheTicket = new RechercheTicket();
                pRechercheTicket.setProjetId(projectId);
                List<Ticket> tickets = vManagerFactory.getTicketManager().getListTicket(pRechercheTicket);
                tickets.stream().forEach(t->wirtteInFile(t.getStatut().toString(), filePath));
                
                
            } else  if ("ExportProjet".equals(vTraitementId)) {
            	Projet projet = vManagerFactory.getProjetManager().getProjet(projectId);
            	wirtteInFile(projet.toString(),filePath);
            } else  if ("ExportListProjets".equals(vTraitementId)) {
            	List<Projet> projets = vManagerFactory.getProjetManager().getListProjet();
            	
            	projets.stream().forEach(p->wirtteInFile(p.toString(), filePath));
            	
            	
            } else if ("InsertVersion".equals(vTraitementId)) {
            	
            	Utilisateur user = new Utilisateur();
            	user.setId(Integer.parseInt(pArgs[1]));
            	user.setNom(pArgs[2]);
            	user.setPrenom(pArgs[3]);
            	
            	Projet pProjet = new Projet();
            	pProjet.setCloture(Boolean.parseBoolean(pArgs[4]));
            	pProjet.setDateCreation(java.sql.Date.valueOf(LocalDate.parse(pArgs[5])));
            	pProjet.setId(Integer.parseInt(pArgs[6]));
            	pProjet.setResponsable(user);
            	
            	Version vVersion = new Version();
            	vVersion.setNumero(pArgs[7]);
            	
            	
            	vVersion.setProjet(pProjet);
            	vManagerFactory.getProjetManager().insertVersion(vVersion);
            }
            else {
            
                throw new TechnicalException("Traitement inconnu : " + vTraitementId);
            }
        } catch (Exception e) {
        	e.printStackTrace();
           // LOGGER.error(vThrowable);
            System.exit(1);
        }
    }
    
	private static void wirtteInFile(String txt,Path filePath) {
		
		try {
			txt = txt +"\n";
			Files.write(filePath,txt.getBytes(),StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	private static Path createOutputFile() throws IOException {
		//Create file for export
		Properties ppt = new Properties();
		
		//${application.home
		RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
		List<String> arguments = runtimeMxBean.getInputArguments();
		Optional<String> optArg = arguments.stream().filter(x->x.startsWith("-Dapplication.home")).findFirst();
		if(optArg.isPresent()) {
			
			
			
			String applicationPath = optArg.get().split("=")[1];
			InputStream in = new FileInputStream(new File(applicationPath+"/conf/config.properties"));
			ppt.load(in);
			String path = (String) ppt.get("exportPath");
			if(!path.endsWith("/")) path = path + "/";
			path = path+"ExportFile.txt";
			File file = new File(path);
			Path filePath = file.toPath();
			if(file.createNewFile()){
			    System.out.println("file.txt File Created in Project root directory");
			}else {
				//empty the file if exist
				PrintWriter writer = new PrintWriter(file);
				writer.print("");
				writer.close();
				System.out.println("File file.txt already exists in the project root directory");
			}
			return filePath;
		}
		return null;
		
	}
}
