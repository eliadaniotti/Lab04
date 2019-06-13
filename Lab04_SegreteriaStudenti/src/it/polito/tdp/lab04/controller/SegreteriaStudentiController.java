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
    	boxCorsi.getItems().addAll(corsi);
    }
    
    @FXML
    void doCercaCorsi(ActionEvent event) {

    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	txtResult.clear();
    	List<Studente> studenti = new LinkedList<Studente>();
    	
    	Corso c = boxCorsi.getValue();
    	studenti = model.getStudentiCorso(c);
    	if(studenti!=null) {
    		for(Studente s : studenti)
    			txtResult.appendText(s);
    	}
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
    }

    @FXML
    void doStudente(ActionEvent event) {
    	int matr = Integer.parseInt(txtMatricola.getText());
    	Studente st = model.getStudente(matr);
    	if(st!=null) {
    		txtNome.setText(st.getNome());
    		txtCognome.setText(st.getCognome());
    	}
    	else
    		txtResult.appendText("Studente non esistente");
   	}

}
