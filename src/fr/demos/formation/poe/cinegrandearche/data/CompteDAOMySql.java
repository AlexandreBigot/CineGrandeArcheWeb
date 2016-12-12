package fr.demos.formation.poe.cinegrandearche.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import fr.demos.formation.poe.cinegrandearche.metier.Compte;

public class CompteDAOMySql implements CompteDAO {

	private Context context;
	private DataSource dataSource;
		
	// dans le constructeur je lance le context (annuaire) et le datasource (pool de connexion)
	public CompteDAOMySql() throws Exception {
		context = new InitialContext();
		dataSource = (DataSource) context.lookup("java:comp/env/jdbc/CineGrandeArche");
	}
	
	@Override
	public void insert(Compte c) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Compte c) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Compte c) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Compte select(String email, String password) {
		
		// je crée un Compte à retourner
		Compte compte = null;
		
		// je me connecte
		try (Connection cx = dataSource.getConnection()){
			
			// je crée mon psmt qui me servira à éviter l'injection SQL râce aux "?"
			PreparedStatement contexteRequeteConnexionCompte = null;
			
			// je lance ma requête SQL
			contexteRequeteConnexionCompte = cx.prepareStatement(
				"SELECT * FROM Compte WHERE lower(Compte.email) LIKE ? AND Compte.password LIKE ?");
					
			// je renseigne les valeurs des "?"
			contexteRequeteConnexionCompte.setString(1, email);
			contexteRequeteConnexionCompte.setString(2, password);
			
			// stockage de mon compte pour le parcourrir
			ResultSet rs = contexteRequeteConnexionCompte.executeQuery();
			System.out.println("execute query lancé");

			// si mon resultset est vide
			if (!rs.first()) {
				System.out.println("rs.next vide");
				// rien à faire compte (qu'on va retourner) est déjà null par défaut
				
			} else { // si pas vide
			
				// pas besoin de while next car un seul élément
				// le rs.first sélectionne le premier objet trouvé
				rs.first();
				System.out.println("rs.first effectué");
				
				// je récupère les données dans des variables
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String adresse_compte = rs.getString("adresse_compte");
				String adresse_livraison = rs.getString("adresse_livraison");
				String emailCompte = rs.getString("email");
				String telephone = rs.getString("telephone"); 
				String passwordCompte = rs.getString("password");

				// création ocmpte avec les infos récupérées
				compte = new Compte(nom, prenom, adresse_compte, adresse_livraison, emailCompte, telephone, passwordCompte);
			}// if else rs.first vide ou pas
			
						
		} // try de la connexion 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // try catch
		
		return compte;
	}

	
	
}
