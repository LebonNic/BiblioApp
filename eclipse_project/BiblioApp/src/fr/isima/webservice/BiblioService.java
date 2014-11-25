package fr.isima.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.googlecode.objectify.Key;

import static com.googlecode.objectify.ObjectifyService.ofy;
import fr.isima.persistence.Auteur;
import fr.isima.persistence.Livre;

@WebService
public class BiblioService {
	
	// Méthodes pour les auteurs
	@WebMethod
	public void addAuteur(
			@WebParam(name="nom") String nom, 
			@WebParam(name="prenom") String prenom, 
			@WebParam(name="adresse")String adresse
			){
		Auteur auteur = new Auteur(nom, prenom, adresse);
		ofy().save().entity(auteur).now();
	}
	
	@WebMethod
	public Auteur getAuteur(@WebParam(name="numero_a") Long numero_a){
		Key<Auteur> cleAuteur = Key.create(Auteur.class, numero_a);
		Auteur auteur = ofy().load().key(cleAuteur).now();
		return auteur;
	}
	
	@WebMethod
	public List<Auteur> getAllAuteurs(){
		List<Auteur> auteurs = ofy().load().type(Auteur.class).list();
		return auteurs;
	}
	
	@WebMethod
	public List<Auteur> searchAuteurs(@WebParam(name="nom") String nom){
		List<Auteur> auteursTrouves = ofy().load().type(Auteur.class).filter("_nom ==", nom).list();
		return auteursTrouves;
	}
	
	@WebMethod
	public void updateAuteur(
			@WebParam(name="numero_a") Long numero_a, 
			@WebParam(name="nom") String nom, 
			@WebParam(name="prenom") String prenom, 
			@WebParam(name="adresse") String adresse
			){
		Key<Auteur> cleAuteur = Key.create(Auteur.class, numero_a);
		Auteur auteur = ofy().load().key(cleAuteur).now();
		auteur.setNom(nom);
		auteur.setPrenom(prenom);
		auteur.setAdresse(adresse);
		ofy().save().entity(auteur).now();
	}
	
	@WebMethod
	public void deleteAuteur(@WebParam(name="numero_a") Long numero_a){
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
	public void addLivre(
			@WebParam(name="titre") String titre, 
			@WebParam(name="prix") double prix, 
			@WebParam(name="resume") String resume, 
			@WebParam(name="numero_a") Long numero_a
			){
		Livre livre = new Livre(titre, prix, resume, numero_a);
		ofy().save().entity(livre).now();
	}
	
	@WebMethod
	public Livre getLivre(@WebParam(name="numero_l") Long numero_l){
		Key<Livre> cleLivre = Key.create(Livre.class, numero_l);
		Livre livre = ofy().load().key(cleLivre).now();
		return livre;
	}
	
	@WebMethod
	public List<Livre> getAllLivres(){
		List<Livre> livres = ofy().load().type(Livre.class).list();
		return livres;
	}
	
	@WebMethod
	public List<Livre> searchLivres(@WebParam(name="titre") String titre){
		List<Livre> livresTrouves = ofy().load().type(Livre.class).filter("_titre ==", titre).list();
		return livresTrouves;
	}
	
	@WebMethod
	public void updateLivre(
			@WebParam(name="numero_l") Long numero_l, 
			@WebParam(name="titre") String titre, 
			@WebParam(name="prix") double prix, 
			@WebParam(name="resume") String resume, 
			@WebParam(name="numero_a") Long numero_a
			){
		Key<Livre> cleLivre = Key.create(Livre.class, numero_l);
		Livre livre = ofy().load().key(cleLivre).now();
		livre.setTitre(titre);
		livre.setPrix(prix);
		livre.setResume(resume);
		livre.setNumero_a(numero_a);
		ofy().save().entity(livre).now();
	}
	
	@WebMethod
	public void deleteLivre(@WebParam(name="numero_l") Long numero_l){
		Key<Livre> cleLivre = Key.create(Livre.class, numero_l);
		ofy().delete().key(cleLivre).now();
	}
	
}
