package it.polito.tdp.lab04.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	private Model model;
	List<Corso> corsi = new ArrayList<Corso>();
	

    @FXML
    private ComboBox<Corso> boxCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnStudente;

    @FXML
    private TextArea txtNome;

    @FXML
    private TextArea txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;
    
    public void setModel(Model model) {
    	this.model=model;
    	setComboItems();
    }

    private void setComboItems() {
    	// Ottieni tutti i corsi dal model
    	corsi = model.getTuttiCorsi();

    	// Aggiungi tutti i corsi alla ComboBox
    	Collections.sort(corsi);
    	boxCorsi.getItems().add(null);
    	boxCorsi.getItems().addAll(corsi);
    	
    }
    
    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtResult.clear();
    	List<Corso> corsi = new LinkedList<Corso>();
    	Corso cc = boxCorsi.getValue();
    	Studente s;
    	
    	try {
    		s = model.getStudente(Integer.parseInt(txtMatricola.getText()));
    	}
    	catch(NumberFormatException e) {
    		txtResult.appendText("Inserire un numero");
    		return;
    	}
    	
    	if(s==null) {
    		txtResult.appendText("Studente non esistente");
    		return;
    	}
    	
    	if(cc==null) {
    		corsi.addAll(model.getCorsiStudente(s.getMatricola()));
    		
    		if(corsi==null) {
    			txtResult.appendText("Studente non iscritto a nessun corso");
    			return;
    		}
    	
    		for(Corso c: corsi) {
    			txtResult.appendText(c.toString() + "\n");
    		}
    	}
    	else {
    		if(model.isIscritto(s.getMatricola(), cc))
    			txtResult.appendText("Studente iscritto al corso");
    		else
    			txtResult.appendText("Studente NON iscritto al corso");
    	}
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	txtResult.clear();
    	List<Studente> studenti = new LinkedList<Studente>();
    	
    	Corso c = boxCorsi.getValue();
    	
    	if(c==null) {
    		txtResult.appendText("Selezionare un corso");
    		return;
    	}
    	
    	studenti = model.getStudentiCorso(c);
    	if(studenti!=null) {
    		for(Studente s : studenti) {
    			txtResult.appendText(String.valueOf(s.getMatricola())+" ");
    			txtResult.appendText(s.getNome()+" ");
    			txtResult.appendText(s.getCognome()+" ");
    			txtResult.appendText(s.getCds()+ "\n");
    		}
    	}
    	else
    		txtResult.appendText("Nessun studente iscritto a questo corso");
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtMatricola.clear();
    	boxCorsi.setValue(null);
    }

    @FXML
    void doStudente(ActionEvent event) {
    	txtResult.clear();
    	int matr=0;
    	
    	try {
    		matr = Integer.parseInt(txtMatricola.getText());
    	}
    	catch(NumberFormatException e) {
    		txtResult.appendText("Inserire un numero");
    		return;
    	}
    	
    	Studente st = model.getStudente(matr);
    	if(st!=null) {
    		txtNome.setText(st.getNome());
    		txtCognome.setText(st.getCognome());
    	}
    	else
    		txtResult.appendText("Studente non esistente");
   	}

}
