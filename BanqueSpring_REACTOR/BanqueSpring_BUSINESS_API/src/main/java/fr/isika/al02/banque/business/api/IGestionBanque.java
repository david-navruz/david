package fr.isika.al02.banque.business.api;

import fr.isika.al02.banque.entity.Personne;

public interface IGestionBanque {
	Personne connection(String login, String mdp);
}
