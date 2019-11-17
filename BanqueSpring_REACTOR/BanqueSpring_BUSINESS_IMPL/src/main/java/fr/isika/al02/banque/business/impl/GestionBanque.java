package fr.isika.al02.banque.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.al02.banque.business.api.IGestionBanque;
import fr.isika.al02.banque.data.api.IDaoPersonne;
import fr.isika.al02.banque.entity.Personne;

@Service
public class GestionBanque implements IGestionBanque {
	@Autowired
	private IDaoPersonne daoPersonne;
	@Override
	public Personne connection(String login, String mdp) {
		return daoPersonne.connection(login, mdp);
	}
	// Pour injection par Spring
	public void setDaoPersonne(IDaoPersonne daoPersonne) {
		this.daoPersonne = daoPersonne;
	}
}
