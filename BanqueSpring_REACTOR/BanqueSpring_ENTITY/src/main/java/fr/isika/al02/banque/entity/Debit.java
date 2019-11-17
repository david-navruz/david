package fr.isika.al02.banque.entity;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DEBIT")
public class Debit extends Operation {
	public Debit() {
		super();
	}
	public Debit(Integer numero, Date dateOperation, Double montant, Compte compte) {
		super(numero, dateOperation, montant, compte);
	}
	
}
