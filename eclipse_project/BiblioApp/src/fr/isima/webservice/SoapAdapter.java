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
		String nom = request.getArg0();
		String prenom = request.getArg1();
		String adresse = request.getArg2();
		bs.addAuteur(nom, prenom, adresse);
		return new AddAuteurResponse();
	}
	
	public GetAuteurResponse adapterGetAuteur(GetAuteur request){
		Long numero_a = request.getArg0();
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
		String nom = request.getArg0();
		List<Auteur> auteursTrouves = bs.searchAuteurs(nom);
		SearchAuteursResponse resp = new SearchAuteursResponse();
		resp.setReturn(auteursTrouves);
		return resp;
	}
	
	public UpdateAuteurResponse adapterUpdateAuteur(UpdateAuteur request){
		Long numero_a = request.getArg0();
		String nom = request.getArg1();
		String prenom = request.getArg2();
		String adresse = request.getArg3();
		bs.updateAuteur(numero_a, nom, prenom, adresse);
		return new UpdateAuteurResponse();
	}
	
	public DeleteAuteurResponse adapterDeleteAuteur(DeleteAuteur request){
		Long numero_a = request.getArg0();
		bs.deleteAuteur(numero_a);
		return new DeleteAuteurResponse();
	}
	
	// Méthodes pour le traitement des livres
	public AddLivreResponse adapterAddLivre(AddLivre request){
		String titre = request.getArg0();
		double prix = request.getArg1();
		String resume = request.getArg2();
		Long numero_a = request.getArg3();
		bs.addLivre(titre, prix, resume, numero_a);
		return new AddLivreResponse();
	}
	
	public GetLivreResponse adapterGetLivre(GetLivre request){
		Long numero_l = request.getArg0();
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
		String titre = request.getArg0();
		List<Livre> livresTrouves = bs.searchLivres(titre);
		SearchLivresResponse resp = new SearchLivresResponse();
		resp.setReturn(livresTrouves);
		return resp;
	}
	
	public UpdateLivreResponse adapterUpdateLivre(UpdateLivre request){
		Long numero_l = request.getArg0();
		String titre = request.getArg1();
		double prix = request.getArg2();
		String resume = request.getArg3();
		Long numero_a = request.getArg4();
		bs.updateLivre(numero_l, titre, prix, resume, numero_a);
		return new UpdateLivreResponse();
	}
	
	public DeleteLivreResponse adapterDeleteLivre(DeleteLivre request){
		Long numero_l = request.getArg0();
		bs.deleteLivre(numero_l);
		return new DeleteLivreResponse();
	}

}
