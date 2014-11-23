package fr.isima.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.googlecode.objectify.Key;

import static com.googlecode.objectify.ObjectifyService.ofy;
import fr.isima.persistence.Auteur;
import fr.isima.persistence.Livre;

@WebService
public class BiblioService {
	
	// Méthodes pour les auteurs
	@WebMethod
	public void addAuteur(String nom, String prenom, String adresse){
		Auteur auteur = new Auteur(nom, prenom, adresse);
		ofy().save().entity(auteur).now();
	}
	
	@WebMethod
	public List<Auteur> getAllAuteurs(){
		List<Auteur> auteurs = ofy().load().type(Auteur.class).list();
		return auteurs;
	}
	
	@WebMethod
	public List<Auteur> searchAuteurs(String nom){
		List<Auteur> auteursTrouves = ofy().load().type(Auteur.class).filter("_nom ==", nom).list();
		return auteursTrouves;
	}
	
	@WebMethod
	public void updateAuteur(Long numero_a, String nom, String prenom, String adresse){
		Key<Auteur> cleAuteur = Key.create(Auteur.class, numero_a);
		Auteur auteur = ofy().load().key(cleAuteur).now();
		auteur.setNom(nom);
		auteur.setPrenom(prenom);
		auteur.setAdresse(adresse);
		ofy().save().entity(auteur).now();
	}
	
	@WebMethod
	public void deleteAuteur(Long numero_a){
		Key<Auteur> cleAuteur = Key.create(Auteur.class, numero_a);
		Auteur auteur = ofy().load().key(cleAuteur).now();
		Long idAuteur = auteur.getNumero_a();
		
		// Suppression des livres associés à l'auteur
		List<Livre> livres = ofy().load().type(Livre.class).filter("_numero_a ==", idAuteur).list();
		for(Livre l : livres){
			ofy().delete().entity(l).now();
		}
		
		// Suppression de l'auteur
		ofy().delete().entity(auteur).now();
	}
	
	// Méthodes pour les livres
	@WebMethod
	public void addLivre(String titre, double prix, String resume, Long numero_a){
		Livre livre = new Livre(titre, prix, resume, numero_a);
		ofy().save().entity(livre).now();
	}
	
	@WebMethod
	public List<Livre> getAllLivres(){
		List<Livre> livres = ofy().load().type(Livre.class).list();
		return livres;
	}
	
	@WebMethod
	public List<Livre> searchLivres(String titre){
		List<Livre> livresTrouves = ofy().load().type(Livre.class).filter("_titre ==", titre).list();
		return livresTrouves;
	}
	
	@WebMethod
	public void updateLivre(Long numero_l, String titre, double prix, String resume, Long numero_a){
		Key<Livre> cleLivre = Key.create(Livre.class, numero_l);
		Livre livre = ofy().load().key(cleLivre).now();
		livre.setTitre(titre);
		livre.setPrix(prix);
		livre.setResume(resume);
		livre.setNumero_a(numero_a);
		ofy().save().entity(livre).now();
	}
	
	@WebMethod
	public void deleteLivre(Long numero_l){
		Key<Livre> cleLivre = Key.create(Livre.class, numero_l);
		ofy().delete().entity(cleLivre).now();
	}
	
}
