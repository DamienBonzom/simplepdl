package simplepdl_to_petrinet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import petrinet.Lien;
import petrinet.Petri;
import petrinet.PetrinetFactory;
import petrinet.PetrinetPackage;
import petrinet.Place;
import petrinet.Transition;
import petrinet.Zone;
import petrinet.impl.PetrinetPackageImpl;
import petrinet.impl.ZoneImpl;
import simplepdl.GestionRessource;
import simplepdl.Process;
import simplepdl.Ressource;
import simplepdl.SimplepdlFactory;
import simplepdl.SimplepdlPackage;
import simplepdl.WorkDefinition;
import simplepdl.WorkSequence;
import simplepdl.WorkSequenceType;
import simplepdl.impl.WorkDefinitionImpl;

public class Simplepdl_to_petrinet {
	
	private Process process;
	private Petri petrinet;
	private PetrinetFactory myFactory_petrinet;
	private Map<WorkDefinition, List<Zone>> association = new HashMap<WorkDefinition, List<Zone>>();
	private Resource resource_petrinet;
	private Map<Ressource, Place> ressource_place = new HashMap<Ressource, Place>();
	
	
	public Simplepdl_to_petrinet() {
		
		// Chargement du package SimplePDL afin de l'enregistrer dans le registre d'Eclipse.
		SimplepdlPackage packageInstance = SimplepdlPackage.eINSTANCE;
						
		// Enregistrer l'extension ".xmi" comme devant Ãªtre ouverte Ã 
		// l'aide d'un objet "XMIResourceFactoryImpl"
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());
						
		// CrÃ©er un objet resourceSetImpl qui contiendra une ressource EMF (notre modÃ¨le)
		ResourceSet resSet = new ResourceSetImpl();

		// Charger la ressource (notre modÃ¨le)
		URI modelURI = URI.createURI("models/exemple.xmi");
		Resource resource = resSet.getResource(modelURI, true);
						
		// RÃ©cupÃ©rer le premier Ã©lÃ©ment du modÃ¨le (Ã©lÃ©ment racine)
		process = (Process) resource.getContents().get(0);
				
		//Creation du nouveau reseau de petri
		// Charger le package SimplePDL afin de l'enregistrer dans le registre d'Eclipse.
		PetrinetPackage packageInstance_petrinet = PetrinetPackage.eINSTANCE;
						
		// Enregistrer l'extension ".xmi" comme devant Ãªtre ouverte Ã 
		// l'aide d'un objet "XMIResourceFactoryImpl"
		Resource.Factory.Registry reg_petrinet = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m_petrinet = reg.getExtensionToFactoryMap();
		m_petrinet.put("xmi", new XMIResourceFactoryImpl());
						
		// CrÃ©er un objet resourceSetImpl qui contiendra une ressource EMF (le modÃ¨le)
		ResourceSet resSet_petrinet = new ResourceSetImpl();

		// DÃ©finir la ressource (le modÃ¨le)
		URI modelURI_petrinet = URI.createURI("models/transformationJAVA.xmi");
		resource_petrinet = resSet_petrinet.createResource(modelURI_petrinet);
						
		// La fabrique pour fabriquer les Ã©lÃ©ments de SimplePDL
		myFactory_petrinet = PetrinetFactory.eINSTANCE;

		// CrÃ©er un Ã©lÃ©ment Process
		petrinet = myFactory_petrinet.createPetri();
		petrinet.setNom(process.getName()+"ToPetri_JAVA");
						
		// Ajouter le Process dans le modÃ¨le
		resource_petrinet.getContents().add(petrinet);
		
	}
	

	private void init_ressource(Ressource r) {
		Place p_init = myFactory_petrinet.createPlace();
		p_init.setNom_zone("Stock" + r.getName());
		p_init.setPetri(petrinet);
		p_init.setNbr_jetons(r.getQuantite());
		petrinet.getElements().add(p_init);
		ressource_place.put(r, p_init);
	}
	
	private void gestion_to_lien(WorkDefinition wd) {
		for(GestionRessource gr : wd.getGestionressources()) {
			Transition transi_commencer = (Transition) association.get(wd).get(2);
			Transition transi_finir = (Transition) association.get(wd).get(3);
			Place place_ressource = ressource_place.get(gr.getRessource());
			init_lien(place_ressource, transi_commencer, false, gr.getQuantite());
			init_lien(transi_finir, place_ressource, false, gr.getQuantite());
		}
	}
	
	
	private Place init_place(WorkDefinition w, String nom, int nbr_jetons) {
		Place p_init = myFactory_petrinet.createPlace();
		p_init.setNom_zone(nom + w.getName());
		p_init.setPetri(petrinet);
		p_init.setNbr_jetons(nbr_jetons);
		petrinet.getElements().add(p_init);
		return p_init;
	}
	
	private Transition init_transi(WorkDefinition w, String nom) {
		Transition transi = myFactory_petrinet.createTransition();
		transi.setNom_zone(nom + w.getName());
		transi.setPetri(petrinet);
		petrinet.getElements().add(transi);
		return transi;
	}
	
	private Lien init_lien(Zone predecesseur, Zone successeur, boolean read_arc, int poids) {
		Lien lien = myFactory_petrinet.createLien();
		lien.setPredecesseur(predecesseur);
		List<Lien> temp = new ArrayList<Lien>();
		temp.addAll(predecesseur.getSuccesseurs());
		temp.add(lien);
		((ZoneImpl) predecesseur).eSet(PetrinetPackage.ZONE__SUCCESSEURS, temp);
		lien.setSuccesseur(successeur);
		temp.clear();
		temp.addAll(successeur.getPredecesseurs());
		temp.add(lien);
		((ZoneImpl) successeur).eSet(PetrinetPackage.ZONE__PREDECESSEURS, temp);
		lien.setPetri(petrinet);
		if(read_arc) {lien.setIs_read_arc(true);}
		lien.setPoids(poids);
		petrinet.getElements().add(lien);
		return lien;
	}
	
	private void init_squelette(WorkDefinition w) {
		Place p_init = init_place(w, "Init_", 1);
		Place p_run = init_place(w, "Running_", 0);
		Place p_end = init_place(w, "End_", 0);
		Place p_started = init_place(w, "HasStarted_", 0);
		
		Transition t_init_run = init_transi(w, "Init_Run_");
		Transition t_run_end = init_transi(w, "Run_End_");
		
		init_lien(p_init, t_init_run, false, 1);
		init_lien(t_init_run, p_run, false, 1);
		init_lien(p_run, t_run_end, false, 1);
		init_lien(t_run_end, p_end, false, 1);
		init_lien(t_init_run, p_started, false, 1);
		
		association.put(w, Arrays.asList(p_started, p_end, t_init_run, t_run_end));
	}
	
	//TODO: le xxx_TO_FINISH donne le mauvais successeur
	private void ws_to_lien(WorkSequence ws) {
		Place predecesseur;
		Transition successeur;
		
		switch(ws.getLinkType()) {
			case FINISH_TO_FINISH :
				predecesseur = (Place) association.get(ws.getPredecessor()).get(1);
				successeur = (Transition) association.get(ws.getSuccessor()).get(3);
				break;
			
			case FINISH_TO_START :
				predecesseur = (Place) association.get(ws.getPredecessor()).get(1);
				successeur = (Transition) association.get(ws.getSuccessor()).get(2);		
				break;
			
			case START_TO_FINISH :
				predecesseur = (Place) association.get(ws.getPredecessor()).get(0);
				successeur = (Transition) association.get(ws.getSuccessor()).get(3);				
				break;
			
			case START_TO_START :
				predecesseur = (Place) association.get(ws.getPredecessor()).get(0);
				successeur = (Transition) association.get(ws.getSuccessor()).get(2);			
				break;
			
			default:
				predecesseur = (Place) association.get(ws.getPredecessor()).get(1);
				successeur = (Transition) association.get(ws.getSuccessor()).get(3);
				break;
			
		}
		
		init_lien(predecesseur, successeur, true, 1);
		
	}

	public static void main(String[] args) {
		
		Simplepdl_to_petrinet fonction = new Simplepdl_to_petrinet();
		
		
		for (Object o : fonction.process.getProcessElements()) {
			if (o instanceof Ressource) {
				Ressource r = (Ressource) o;
				fonction.init_ressource(r);
			}
		}
		
		for (Object o : fonction.process.getProcessElements()) {
			if (o instanceof WorkDefinition) {
				WorkDefinition wd = (WorkDefinition) o;
				fonction.init_squelette(wd);
				fonction.gestion_to_lien(wd);
			}
		}
		
		for (Object o : fonction.process.getProcessElements()) {
			if (o instanceof WorkSequence) {
				WorkSequence ws = (WorkSequence) o;
				fonction.ws_to_lien(ws);
			}
		}
		
		// Sauver la ressource
	    try {
	    	fonction.resource_petrinet.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
