package fr.isika.al02.banque.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.isika.al02.banque.business.api.IGestionBanque;
import fr.isika.al02.banque.entity.Client;
import fr.isika.al02.banque.entity.Personne;

@ManagedBean(name = "mbCnx")
@SessionScoped
public class ConnectionManagedBean {
	@ManagedProperty(value = "#{gestionBanque}")
	private IGestionBanque buBanque;
	private Personne connectedPerson;
	private String messageErreur;
	private String login;
	private String mdp;
	public String seConnecter() {
		messageErreur = "";
		connectedPerson = buBanque.connection(login, mdp);
		if (connectedPerson == null) {
			messageErreur = "mauvais login/password";
		} else if (connectedPerson.getClass() == Client.class) {
			return "/page-client.xhtml?faces-redirect=true";
		} else {
			return "/page-conseiller.xhtml?faces-redirect=true";
		}
		return "";
	}
	public IGestionBanque getBuBanque() {
		return buBanque;
	}
	public void setBuBanque(IGestionBanque buBanque) {
		this.buBanque = buBanque;
	}
	public Personne getConnectedPerson() {
		return connectedPerson;
	}
	public void setConnectedPerson(Personne connectedPerson) {
		this.connectedPerson = connectedPerson;
	}
	public String getMessageErreur() {
		return messageErreur;
	}
	public void setMessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
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
