package fr.isima.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.googlecode.objectify.Key;

import static com.googlecode.objectify.ObjectifyService.ofy;
import fr.isima.persistence.Auteur;
import fr.isima.persistence.Livre;

/**
 * La classe BiblioService définit l'interface du service web
 * utilisé pour interagir à distance avec l'application.
 * 
 * @author Nicolas Prugne
 *
 */
@WebService
public class BiblioService {
	
	/**
	 * Ajoute un auteur dans la base de données.
	 * @param nom Nom de l'auteur.
	 * @param prenom Prenom de l'auteur.
	 * @param adresse Adresse de l'auteur.
	 * @return L'auteur nouvellement créé et ajouté à la base.
	 */
	@WebMethod
	public Auteur addAuteur(
			@WebParam(name="nom") String nom, 
			@WebParam(name="prenom") String prenom, 
			@WebParam(name="adresse")String adresse
			){
		Auteur auteur = new Auteur(nom, prenom, adresse);
		ofy().save().entity(auteur).now();
		return auteur;
	}
	
	/**
	 * Récupère un auteur stocké dans la base via son identifiant.
	 * @param numero_a Identifiant de l'auteur.
	 * @return L'auteur correspondant à l'identifiant.
	 */
	@WebMethod
	public Auteur getAuteur(@WebParam(name="numero_a") Long numero_a){
		Key<Auteur> cleAuteur = Key.create(Auteur.class, numero_a);
		Auteur auteur = ofy().load().key(cleAuteur).now();
		return auteur;
	}
	
	/**
	 * Récupère tous les auteurs présents dans la base.
	 * @return Une liste contenant tous les auteurs stockés dans
	 * la base de données.
	 */
	@WebMethod
	public List<Auteur> getAllAuteurs(){
		List<Auteur> auteurs = ofy().load().type(Auteur.class).list();
		return auteurs;
	}
	
	/**
	 * Permet de rechercher un auteur grace à son nom dans la base
	 * de données.
	 * @param nom Le nom de l'auteur recherché.
	 * @return Une liste d'auteurs dont le début du nom correspond
	 * à la chaine passée en paramètre. 
	 */
	@WebMethod
	public List<Auteur> searchAuteurs(@WebParam(name="nom") String nom){
		List<Auteur> auteursTrouves = ofy().load().type(Auteur.class).filter("_nom >=", nom).filter("_nom <", nom + "\uFFFD").list();
		return auteursTrouves;
	}
	
	/**
	 * Met à jour les attributs d'un auteur déjà présent dans la 
	 * base.
	 * @param numero_a Identifiant de l'auteur à mettre à jour.
	 * @param nom Nouveau nom de l'auteur.
	 * @param prenom Nouveau prenom de l'auteur.
	 * @param adresse Nouvelle adresse de l'auteur.
	 */
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
	
	/**
	 * Supprime un auteur à partir de son identifiant.
	 * @param numero_a Identifiant de l'auteur à supprimer.
	 */
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
	/**
	 * Ajoute un livre dans la base de données.
	 * @param titre Titre du livre.
	 * @param prix Prix du livre.
	 * @param resume Résumé du livre.
	 * @param numero_a Identifiant de l'auteur du livre.
	 * @return Le nouveau livre tout juste créé et persisté dans la
	 * base.
	 */
	@WebMethod
	public Livre addLivre(
			@WebParam(name="titre") String titre, 
			@WebParam(name="prix") double prix, 
			@WebParam(name="resume") String resume, 
			@WebParam(name="numero_a") Long numero_a
			){
		Livre livre = new Livre(titre, prix, resume, numero_a);
		ofy().save().entity(livre).now();
		return livre;
	}
	
	/**
	 * Récupère un livre dans la base de données via son identifiant.
	 * @param numero_l Identifiant du livre.
	 * @return Le livre correspondant à l'identifiant.
	 */
	@WebMethod
	public Livre getLivre(@WebParam(name="numero_l") Long numero_l){
		Key<Livre> cleLivre = Key.create(Livre.class, numero_l);
		Livre livre = ofy().load().key(cleLivre).now();
		return livre;
	}
	
	/**
	 * Récupère tous livres présents dans la base de données.
	 * @return Une liste contenant tous les livres de la base.
	 */
	@WebMethod
	public List<Livre> getAllLivres(){
		List<Livre> livres = ofy().load().type(Livre.class).list();
		return livres;
	}
	
	/**
	 * Recherche tous les livres dont le titre commence par la chaine
	 * passée en paramètre.
	 * @param titre Le titre du 
	 * @return Une liste de livres dont le titre commence par la chaine
	 * passée en paramètre.
	 */
	@WebMethod
	public List<Livre> searchLivres(@WebParam(name="titre") String titre){
		List<Livre> livresTrouves = ofy().load().type(Livre.class).filter("_titre >=", titre).filter("_titre <", titre + "\uFFFD").list();
		return livresTrouves;
	}
	
	/**
	 * Met à jour les attributs d'un livre spécifié par son identifiant.
	 * @param numero_l Identifiant du livre à mettre à jour.
	 * @param titre Nouveau titre du livre.
	 * @param prix Nouveau prix du livre.
	 * @param resume Nouveau résumé du livre.
	 * @param numero_a Nouvel identifiant de l'auteur du livre.
	 */
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
	
	/**
	 * Supprime un livre de la base via son identifiant.
	 * @param numero_l Identifiant du livre à supprimer.
	 */
	@WebMethod
	public void deleteLivre(@WebParam(name="numero_l") Long numero_l){
		Key<Livre> cleLivre = Key.create(Livre.class, numero_l);
		ofy().delete().key(cleLivre).now();
	}
	
}
