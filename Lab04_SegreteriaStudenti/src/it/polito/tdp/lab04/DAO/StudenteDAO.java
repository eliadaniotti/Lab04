package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getStudenteDAO(int matricola) {
		Studente s=null;
		String sql= "SELECT * FROM studente WHERE matricola=?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
				s = new Studente(matricola, rs.getString("nome"), rs.getString("cognome"), rs.getString("cds"));
			
			conn.close();
			
			return s;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		catch (NumberFormatException e) {
			// e.printStackTrace();
			throw new RuntimeException("Formato matricola errato", e);
		}
	}
	
	public List<Corso> getCorsiStudente(int matricola) {
		Studente stud = this.getStudenteDAO(matricola);
		List<Corso> corsi = new LinkedList<Corso>();
		
		if(stud==null)
			return null;
		
		String sql="SELECT * FROM corso,iscrizione WHERE iscrizione.codins=corso.codins AND matricola=?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				corsi.add(new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd")));
			}
			
			conn.close();
		}
		catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		return corsi;
	}
	
	public boolean isIscritto(int matricola, Corso c) {
		String sql="SELECT * FROM iscrizione WHERE matricola=? AND codins=?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			st.setString(2, c.getCodins());
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
				return true;
			else
				return false;
		}
		catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

}
