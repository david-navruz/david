package fr.isika.al02.banque.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
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
@Table(name = "banque_operation")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "operation_type_operation")
public class Operation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "operation_numero")
	private Integer numero;
	@Column(name = "operation_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOperation;
	@Column(name = "operation_montant", nullable = false,
			columnDefinition = "DECIMAL(10,2)")
	private Double montant;
	@ManyToOne
	@JoinColumn(name = "operation_numero_compte",
				nullable = false,
				foreignKey = @ForeignKey(name = "FK_OPERATION_COMPTE"))
	private Compte compte;
	public Operation() {
		super();
	}
	
	public Operation(Integer numero, Date dateOperation, Double montant, Compte compte) {
		super();
		this.numero = numero;
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.compte = compte;
	}

	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Date getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
}
