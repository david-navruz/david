package fr.isika.al02.banque.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "banque_conseiller")
public class Conseiller extends Personne {
	@OneToMany(mappedBy = "conseil")
	private List<Client> clients;

	public Conseiller() {
		super();
	}
	public Conseiller(Integer id, String nom, String login, String mdp) {
		super(id, nom, login, mdp);
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
}
