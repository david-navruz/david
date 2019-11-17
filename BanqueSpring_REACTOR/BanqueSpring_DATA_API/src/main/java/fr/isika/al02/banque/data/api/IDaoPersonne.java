package fr.isika.al02.banque.data.api;

import java.util.List;

import javax.jws.WebParam;

import fr.isika.al02.banque.entity.Client;
import fr.isika.al02.banque.entity.Personne;

public interface IDaoPersonne {
	Personne connection(String login, String mdp);
	List<Client> recherche(String nom);
}
