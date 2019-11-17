package fr.isika.al02.banque.data.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.isika.al02.banque.data.api.IDaoPersonne;
import fr.isika.al02.banque.entity.Client;
import fr.isika.al02.banque.entity.Personne;

@Service
@Transactional(transactionManager = "banqueTransactionManager")
public class DaoPersonne implements IDaoPersonne {
	private Logger log = Logger.getLogger(getClass());
	@PersistenceContext(unitName = "BanqueSpring")
	private EntityManager em;
	@Override
	public Personne connection(String login, String mdp) {
		try {
			return em.createQuery(
				"SELECT p FROM Personne p WHERE p.login = :plogin AND p.mdp = :pmdp",
				Personne.class)
					.setParameter("plogin", login)
					.setParameter("pmdp", mdp)
					.getSingleResult();
		} catch (Exception e) {
			log.debug("aucun utilisateur avec login/pass", e);
			return null;
		}
	}
	@Override
	public List<Client> recherche(String nom) {
		return em.createQuery(
			"SELECT c FROM Client c WHERE c.nom like :anthony", Client.class)
				.setParameter("anthony", "%" + nom + "%")
				.getResultList();
	}
}
