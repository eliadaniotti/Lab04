package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getStudenteDAO(int matricola) {
		String sql= "SELECT * FROM studente WHERE matricola=?";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			
			Studente s=null;
			while (rs.next()) {
				s = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("CDS"));
			}
			conn.close();
			
			return s;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

}
