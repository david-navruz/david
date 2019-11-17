package fr.isika.al02.banque.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "banque_compte")
@Inheritance(strategy = InheritanceType.JOINED)
public class Compte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "compte_numero")
	private Integer numero;
	@Column(name = "compte_date_creation", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	@ManyToOne
	@JoinColumn(name = "compte_id_client", nullable = false,
				foreignKey = @ForeignKey(name = "FK_COMPTE_CLIENT"))
	private Client client;
	public Compte() {
		super();
	}
	public Compte(Integer numero, Date dateCreation, Client client) {
		super();
		this.numero = numero;
		this.dateCreation = dateCreation;
		this.client = client;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
}
