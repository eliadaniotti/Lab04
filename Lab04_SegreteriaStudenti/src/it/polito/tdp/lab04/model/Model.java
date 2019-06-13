package it.polito.tdp.lab04.model;

import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	StudenteDAO s = new StudenteDAO();
	
	public Studente getStudente(int matricola) {
		return s.getStudenteDAO(matricola);
	}

}
