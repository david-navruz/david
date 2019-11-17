package fr.isika.al02.banque.entity;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CREDIT")
public class Credit extends Operation {
	public Credit() {
		super();
	}
	public Credit(Integer numero, Date dateOperation, Double montant, Compte compte) {
		super(numero, dateOperation, montant, compte);
	}
	
}
