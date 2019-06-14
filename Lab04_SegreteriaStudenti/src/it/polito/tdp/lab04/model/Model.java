package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	StudenteDAO s = new StudenteDAO();
	CorsoDAO c = new CorsoDAO();
	
	public List<Corso> getTuttiCorsi(){
		return c.getTuttiICorsi();
	}
	
	public List<Corso> getCorsiStudente(int matricola){
		return s.getCorsiStudente(matricola);
	}
	
	public List<Studente> getStudentiCorso(Corso corso) {
		return c.getStudentiIscrittiAlCorso(corso);
	}
	
	public Studente getStudente(int matricola) {
		return s.getStudenteDAO(matricola);
	}
	
	public boolean isIscritto(int matricola, Corso c) {
		return s.isIscritto(matricola, c);
	}
}
