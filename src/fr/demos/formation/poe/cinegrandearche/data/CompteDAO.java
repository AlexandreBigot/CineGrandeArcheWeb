package fr.demos.formation.poe.cinegrandearche.data;

import fr.demos.formation.poe.cinegrandearche.metier.Compte;

public interface CompteDAO {

	void insert (Compte c) throws Exception;
	void update (Compte c) throws Exception;
	void delete (Compte c) throws Exception;
	Compte select (String email, String password);
	
}
