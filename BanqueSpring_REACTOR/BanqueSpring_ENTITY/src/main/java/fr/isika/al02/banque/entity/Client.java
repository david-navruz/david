package fr.isika.al02.banque.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "banque_client")
public class Client extends Personne {
	@ManyToOne
	@JoinColumn(name = "client_id_conseil", 
				nullable = false,
				foreignKey = 
				@ForeignKey(name = "FK_CLIENT_CONSEILLER"))
	private Conseiller conseil;
	@OneToMany(mappedBy = "client")
	private List<Compte> comptes;
	public Client(Integer id, String nom, String login, String mdp, Conseiller conseil) {
		super(id, nom, login, mdp);
		this.conseil = conseil;
	}

	public Client() {
		super();
	}

	public Conseiller getConseil() {
		return conseil;
	}

	public void setConseil(Conseiller conseil) {
		this.conseil = conseil;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	
}
