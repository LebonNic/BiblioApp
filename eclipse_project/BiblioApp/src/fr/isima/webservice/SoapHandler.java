package fr.isima.webservice;

import java.util.Iterator;

import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SAAJResult;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;

import fr.isima.webservice.jaxws.AddAuteur;
import fr.isima.webservice.jaxws.AddLivre;
import fr.isima.webservice.jaxws.DeleteAuteur;
import fr.isima.webservice.jaxws.DeleteLivre;
import fr.isima.webservice.jaxws.GetAllAuteurs;
import fr.isima.webservice.jaxws.GetAllLivres;
import fr.isima.webservice.jaxws.SearchAuteurs;
import fr.isima.webservice.jaxws.SearchLivres;
import fr.isima.webservice.jaxws.UpdateAuteur;
import fr.isima.webservice.jaxws.UpdateLivre;

public class SoapHandler {
	private static final String NAMESPACE_URI = "http://webservice.isima.fr/";
	
	private static final QName addAuteur_QNAME = new QName(NAMESPACE_URI, "addAuteur");
	private static final QName getAllAuteurs_QNAME = new QName(NAMESPACE_URI, "getAllAuteurs");
	private static final QName searchAuteurs_QNAME = new QName(NAMESPACE_URI, "searchAuteurs");
	private static final QName updateAuteur_QNAME = new QName(NAMESPACE_URI, "updateAuteur");
	private static final QName deleteAuteur_QNAME = new QName(NAMESPACE_URI, "deleteAuteur");
	
	private static final QName addLivre_QNAME = new QName(NAMESPACE_URI, "addLivre");
	private static final QName getAllLivres_QNAME = new QName(NAMESPACE_URI, "getAllLivres");
	private static final QName searchLivres_QNAME = new QName(NAMESPACE_URI, "searchLivres");
	private static final QName updateLivre_QNAME = new QName(NAMESPACE_URI, "updateLivre");
	private static final QName deleteLivre_QNAME = new QName(NAMESPACE_URI, "deleteLivre");
	
	private MessageFactory messageFactory;
	private SoapAdapter adapter;
	
	public SoapHandler() throws SOAPException{
		messageFactory = MessageFactory.newInstance();
		adapter = new SoapAdapter();
	}
	
	@SuppressWarnings("rawtypes")
	public SOAPMessage handleSOAPRequest(SOAPMessage request) throws SOAPException{
		SOAPBody body = request.getSOAPBody();
		Iterator it = body.getChildElements();
		Object response = null;
		
		while(it.hasNext()){
			
			Object next = it.next();
			
			if(next instanceof SOAPElement){
				SOAPElement soapElement = (SOAPElement) next;
				QName qname = soapElement.getElementQName();
				
				System.out.println(qname);
				System.out.println(getAllLivres_QNAME);
				
				// Auteurs
				if(SoapHandler.addAuteur_QNAME.equals(qname)){
					response = appelerAddAuteur(soapElement);
					break;
				}
				else if(SoapHandler.getAllAuteurs_QNAME.equals(qname)){
					response = appelerGetAllAuteurs(soapElement);
					break;
				}
				else if(SoapHandler.searchAuteurs_QNAME.equals(qname)){
					response = appelerSearchAuteurs(soapElement);
					break;
				}
				else if(SoapHandler.updateAuteur_QNAME.equals(qname)){
					response = appelerUpdateAuteur(soapElement);
					break;
				}
				else if(SoapHandler.deleteAuteur_QNAME.equals(qname)){
					response = appelerDeleteAuteur(soapElement);
					break;
				}
				
				// Livres
				else if(SoapHandler.addLivre_QNAME.equals(qname)){
					response = appelerAddLivre(soapElement);
					break;
				}
				else if(SoapHandler.getAllLivres_QNAME.equals(qname)){
					response = appelerGetAllLivres(soapElement);
					break;
				}
				else if(SoapHandler.searchLivres_QNAME.equals(qname)){
					response = appelerSearchLivres(soapElement);
					break;
				}
				else if(SoapHandler.updateLivre_QNAME.equals(qname)){
					response = appelerUpdateLivre(soapElement);
					break;
				}
				else if(SoapHandler.deleteLivre_QNAME.equals(qname)){
					response = appelerDeleteLivre(soapElement);
					break;
				}
			}
		}
		
		SOAPMessage soapResponse = messageFactory.createMessage();
		body = soapResponse.getSOAPBody();
		
		if(response != null){
			JAXB.marshal(response, new SAAJResult(body));
		} 
		else{
			SOAPFault fault = body.addFault();
			fault.setFaultString("Unrecognized SOAP request");
		}
		
		return soapResponse;
	}
	
	// Auteurs
	private Object appelerAddAuteur(SOAPElement soapElement){
		AddAuteur request = JAXB.unmarshal(new DOMSource(soapElement), AddAuteur.class);
		return adapter.adapterAddAuteur(request);
	}
	
	private Object appelerGetAllAuteurs(SOAPElement soapElement){
		GetAllAuteurs request = JAXB.unmarshal(new DOMSource(soapElement), GetAllAuteurs.class);
		return adapter.adapterGetAllAuteurs(request);
	}
	
	private Object appelerSearchAuteurs(SOAPElement soapElement){
		SearchAuteurs request = JAXB.unmarshal(new DOMSource(soapElement), SearchAuteurs.class);
		return adapter.adapterSearchAuteurs(request);
	}
	
	private Object appelerUpdateAuteur(SOAPElement soapElement){
		UpdateAuteur request = JAXB.unmarshal(new DOMSource(soapElement), UpdateAuteur.class);
		return adapter.adapterUpdateAuteur(request);
	}
	
	private Object appelerDeleteAuteur(SOAPElement soapElement){
		DeleteAuteur request = JAXB.unmarshal(new DOMSource(soapElement), DeleteAuteur.class);
		return adapter.adapterDeleteAuteur(request);
	}
	
	// Livres
	private Object appelerAddLivre(SOAPElement soapElement){
		AddLivre request = JAXB.unmarshal(new DOMSource(soapElement), AddLivre.class);
		return adapter.adapterAddLivre(request);
	}
	
	private Object appelerGetAllLivres(SOAPElement soapElement){
		GetAllLivres request = JAXB.unmarshal(new DOMSource(soapElement), GetAllLivres.class);
		return adapter.adapterGetAllLivres(request);
	}
	
	private Object appelerSearchLivres(SOAPElement soapElement){
		SearchLivres request = JAXB.unmarshal(new DOMSource(soapElement), SearchLivres.class);
		return adapter.adapterSearchLivres(request);
	}
	
	private Object appelerUpdateLivre(SOAPElement soapElement){
		UpdateLivre request = JAXB.unmarshal(new DOMSource(soapElement), UpdateLivre.class);
		return adapter.adapterUpdateLivre(request);
	}
	
	private Object appelerDeleteLivre(SOAPElement soapElement){
		DeleteLivre request = JAXB.unmarshal(new DOMSource(soapElement), DeleteLivre.class);
		return adapter.adapterDeleteLivre(request);
	}
}
