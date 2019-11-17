package fr.isika.al02.banque.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "banque_personne")
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "personne_id")
	private Integer id;
	@Column(name = "personne_nom", nullable = false, length = 50)
	private String nom;
	@Column(name = "personne_login", nullable = false, length = 50)
	private String login;
	@Column(name = "personne_mot_de_passe", nullable = false, length = 50)
	private String mdp;
	public Personne() {
		super();
	}
	public Personne(Integer id, String nom, String login, String mdp) {
		super();
		this.id = id;
		this.nom = nom;
		this.login = login;
		this.mdp = mdp;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
}
