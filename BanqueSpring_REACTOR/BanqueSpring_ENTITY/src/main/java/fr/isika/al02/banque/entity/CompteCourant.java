package fr.isika.al02.banque.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "banque_compte_courant")
public class CompteCourant extends Compte {
	@Column(name = "compte_courant_decouvert", nullable = false)
	private Integer decouvert;

	public CompteCourant() {
		super();
	}

	public CompteCourant(Integer numero, Date dateCreation, Client client, Integer decouvert) {
		super(numero, dateCreation, client);
		this.decouvert = decouvert;
	}

	public Integer getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(Integer decouvert) {
		this.decouvert = decouvert;
	}
	
}
