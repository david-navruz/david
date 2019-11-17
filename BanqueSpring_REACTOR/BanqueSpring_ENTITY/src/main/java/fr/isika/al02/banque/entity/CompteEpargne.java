package fr.isika.al02.banque.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "banque_compte_epargne")
public class CompteEpargne extends Compte {
	@Column(name = "compte_epargne_plafond", nullable = false)
	private Integer plafond;

	public CompteEpargne() {
		super();
	}

	public CompteEpargne(Integer numero, Date dateCreation, Client client, Integer plafond) {
		super(numero, dateCreation, client);
		this.plafond = plafond;
	}

	public Integer getPlafond() {
		return plafond;
	}

	public void setPlafond(Integer plafond) {
		this.plafond = plafond;
	}
}
