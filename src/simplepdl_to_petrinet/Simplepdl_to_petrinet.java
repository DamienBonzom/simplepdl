package simplepdl_to_petrinet;

import java.io.IOException;
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

import petrinet.Liens;
import petrinet.Petri;
import petrinet.PetrinetFactory;
import petrinet.PetrinetPackage;
import petrinet.Places;
import petrinet.Transitions;
import petrinet.Zone;
import petrinet.impl.PetrinetPackageImpl;
import simplepdl.Process;
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
		URI modelURI = URI.createURI("models/SimplePDLCreator_Created_Process.xmi");
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
		URI modelURI_petrinet = URI.createURI("models/transformation_reseau_petri.xmi");
		resource_petrinet = resSet_petrinet.createResource(modelURI_petrinet);
						
		// La fabrique pour fabriquer les Ã©lÃ©ments de SimplePDL
		myFactory_petrinet = PetrinetFactory.eINSTANCE;

		// CrÃ©er un Ã©lÃ©ment Process
		petrinet = myFactory_petrinet.createPetri();
		petrinet.setNom("Mon premier reseau de petri");
						
		// Ajouter le Process dans le modÃ¨le
		resource_petrinet.getContents().add(petrinet);
		
	}
	
	//TODO: Faire une fonction commune generique pour place et transi si le temps
	
	private Places init_place(WorkDefinition w, String nom) {
		Places p_init = myFactory_petrinet.createPlaces();
		p_init.setNom_zone(nom + w.getName());
		p_init.setPetri(petrinet);
		petrinet.getElements().add(p_init);
		return p_init;
	}
	
	private Transitions init_transi(WorkDefinition w, String nom) {
		Transitions transi = myFactory_petrinet.createTransitions();
		transi.setNom_zone(nom + w.getName());
		transi.setPetri(petrinet);
		petrinet.getElements().add(transi);
		return transi;
	}
	
	private Liens init_lien(Zone predecesseur, Zone successeur, boolean read_arc) {
		Liens lien = myFactory_petrinet.createLiens();
		lien.setPredecesseur(predecesseur);
		lien.setSuccesseur(successeur);
		lien.setPetri(petrinet);
		if(read_arc) {lien.setIs_read_arc(true);}
		petrinet.getElements().add(lien);
		return lien;
	}
	
	private void init_squelette(WorkDefinition w) {
		Places p_init = init_place(w, "Init_");
		Places p_run = init_place(w, "Running_");
		Places p_end = init_place(w, "End_");
		Places p_started = init_place(w, "HasStarted_");
		
		Transitions t_init_run = init_transi(w, "Init_Run_");
		Transitions t_run_end = init_transi(w, "Run_End_");
		
		init_lien(p_init, t_init_run, false);
		init_lien(t_init_run, p_run, false);
		init_lien(p_run, t_run_end, false);
		init_lien(t_run_end, p_end, false);
		init_lien(t_init_run, p_started, false);
		
		association.put(w, Arrays.asList(p_started, p_end, t_init_run, t_run_end));
	}
	
	//TODO: le xxx_TO_FINISH donne le mauvais successeur
	private void ws_to_lien(WorkSequence ws) {
		Places predecesseur;
		Transitions successeur;
		
		switch(ws.getLinkType()) {
			case FINISH_TO_FINISH :{
				predecesseur = (Places) association.get(ws.getPredecessor()).get(1);
				successeur = (Transitions) association.get(ws.getSuccessor()).get(3);				
			}
			case FINISH_TO_START:{
				predecesseur = (Places) association.get(ws.getPredecessor()).get(1);
				successeur = (Transitions) association.get(ws.getSuccessor()).get(2);						
			}
			case START_TO_FINISH :{
				predecesseur = (Places) association.get(ws.getPredecessor()).get(0);
				successeur = (Transitions) association.get(ws.getSuccessor()).get(3);				
			}
			case START_TO_START:{
				predecesseur = (Places) association.get(ws.getPredecessor()).get(0);
				successeur = (Transitions) association.get(ws.getSuccessor()).get(2);						
			}
			default:{
				predecesseur = (Places) association.get(ws.getPredecessor()).get(0);
				successeur = (Transitions) association.get(ws.getSuccessor()).get(2);	
			}
		}
		
		init_lien(predecesseur, successeur, true);
		
	}

	public static void main(String[] args) {
		
		Simplepdl_to_petrinet fonction = new Simplepdl_to_petrinet();
		
		for (Object o : fonction.process.getProcessElements()) {
			if (o instanceof WorkDefinition) {
				WorkDefinition wd = (WorkDefinition) o;
				fonction.init_squelette(wd);
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
