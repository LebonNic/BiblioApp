package fr.isima.webservice;

import java.util.List;

import fr.isima.persistence.Auteur;
import fr.isima.persistence.Livre;
import fr.isima.webservice.jaxws.AddAuteur;
import fr.isima.webservice.jaxws.AddAuteurResponse;
import fr.isima.webservice.jaxws.AddLivre;
import fr.isima.webservice.jaxws.AddLivreResponse;
import fr.isima.webservice.jaxws.DeleteAuteur;
import fr.isima.webservice.jaxws.DeleteAuteurResponse;
import fr.isima.webservice.jaxws.DeleteLivre;
import fr.isima.webservice.jaxws.DeleteLivreResponse;
import fr.isima.webservice.jaxws.GetAllAuteurs;
import fr.isima.webservice.jaxws.GetAllAuteursResponse;
import fr.isima.webservice.jaxws.GetAllLivres;
import fr.isima.webservice.jaxws.GetAllLivresResponse;
import fr.isima.webservice.jaxws.GetAuteur;
import fr.isima.webservice.jaxws.GetAuteurResponse;
import fr.isima.webservice.jaxws.GetLivre;
import fr.isima.webservice.jaxws.GetLivreResponse;
import fr.isima.webservice.jaxws.SearchAuteurs;
import fr.isima.webservice.jaxws.SearchAuteursResponse;
import fr.isima.webservice.jaxws.SearchLivres;
import fr.isima.webservice.jaxws.SearchLivresResponse;
import fr.isima.webservice.jaxws.UpdateAuteur;
import fr.isima.webservice.jaxws.UpdateAuteurResponse;
import fr.isima.webservice.jaxws.UpdateLivre;
import fr.isima.webservice.jaxws.UpdateLivreResponse;

public class SoapAdapter {
	private final static BiblioService bs = new BiblioService();
	
	// Méthodes pour le traitement des auteurs
	public AddAuteurResponse adapterAddAuteur(AddAuteur request){
		String nom = request.getNom();
		String prenom = request.getPrenom();
		String adresse = request.getAdresse();
		Auteur auteur = bs.addAuteur(nom, prenom, adresse);
		AddAuteurResponse resp = new AddAuteurResponse();
		resp.setReturn(auteur);
		return resp;
	}
	
	public GetAuteurResponse adapterGetAuteur(GetAuteur request){
		Long numero_a = request.getNumeroA();
		Auteur auteur = bs.getAuteur(numero_a);
		GetAuteurResponse resp = new GetAuteurResponse();
		resp.setReturn(auteur);
		return resp;
	}
	
	public GetAllAuteursResponse adapterGetAllAuteurs(GetAllAuteurs request){
		List<Auteur> auteurs = bs.getAllAuteurs();
		GetAllAuteursResponse resp = new GetAllAuteursResponse();
		resp.setReturn(auteurs);
		return resp;
	}
	
	public SearchAuteursResponse adapterSearchAuteurs(SearchAuteurs request){
		String nom = request.getNom();
		List<Auteur> auteursTrouves = bs.searchAuteurs(nom);
		SearchAuteursResponse resp = new SearchAuteursResponse();
		resp.setReturn(auteursTrouves);
		return resp;
	}
	
	public UpdateAuteurResponse adapterUpdateAuteur(UpdateAuteur request){
		Long numero_a = request.getNumeroA();
		String nom = request.getNom();
		String prenom = request.getPrenom();
		String adresse = request.getAdresse();
		bs.updateAuteur(numero_a, nom, prenom, adresse);
		return new UpdateAuteurResponse();
	}
	
	public DeleteAuteurResponse adapterDeleteAuteur(DeleteAuteur request){
		Long numero_a = request.getNumeroA();
		bs.deleteAuteur(numero_a);
		return new DeleteAuteurResponse();
	}
	
	// Méthodes pour le traitement des livres
	public AddLivreResponse adapterAddLivre(AddLivre request){
		String titre = request.getTitre();
		double prix = request.getPrix();
		String resume = request.getResume();
		Long numero_a = request.getNumeroA();
		Livre livre = bs.addLivre(titre, prix, resume, numero_a);
		AddLivreResponse resp = new AddLivreResponse();
		resp.setReturn(livre);
		return resp;
	}
	
	public GetLivreResponse adapterGetLivre(GetLivre request){
		Long numero_l = request.getNumeroL();
		Livre livre = bs.getLivre(numero_l);
		GetLivreResponse resp = new GetLivreResponse();
		resp.setReturn(livre);
		return resp;
	}
	
	public GetAllLivresResponse adapterGetAllLivres(GetAllLivres request){
		List<Livre> livres = bs.getAllLivres();
		GetAllLivresResponse resp = new GetAllLivresResponse();
		resp.setReturn(livres);
		return resp;
	}
	
	public SearchLivresResponse adapterSearchLivres(SearchLivres request){
		String titre = request.getTitre();
		List<Livre> livresTrouves = bs.searchLivres(titre);
		SearchLivresResponse resp = new SearchLivresResponse();
		resp.setReturn(livresTrouves);
		return resp;
	}
	
	public UpdateLivreResponse adapterUpdateLivre(UpdateLivre request){
		Long numero_l = request.getNumeroL();
		String titre = request.getTitre();
		double prix = request.getPrix();
		String resume = request.getResume();
		Long numero_a = request.getNumeroA();
		bs.updateLivre(numero_l, titre, prix, resume, numero_a);
		return new UpdateLivreResponse();
	}
	
	public DeleteLivreResponse adapterDeleteLivre(DeleteLivre request){
		Long numero_l = request.getNumeroL();
		bs.deleteLivre(numero_l);
		return new DeleteLivreResponse();
	}

}
