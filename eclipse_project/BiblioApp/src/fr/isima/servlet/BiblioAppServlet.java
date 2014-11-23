package fr.isima.servlet;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.http.*;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import com.googlecode.objectify.ObjectifyService;

import fr.isima.persistence.Auteur;
import fr.isima.persistence.Livre;
import fr.isima.webservice.SoapHandler;

@SuppressWarnings("serial")
public class BiblioAppServlet extends HttpServlet {
	
	private static MessageFactory messageFactory;
	private static SoapHandler soapHandler;
	
	static{
		ObjectifyService.register(Auteur.class);
		ObjectifyService.register(Livre.class);
		
		try {
			BiblioAppServlet.messageFactory = MessageFactory.newInstance();
			BiblioAppServlet.soapHandler = new SoapHandler();
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static MimeHeaders getHeaders(HttpServletRequest req){
		Enumeration headerNames = req.getHeaderNames();
		MimeHeaders headers = new MimeHeaders();
		
		while(headerNames.hasMoreElements()){
			String headerName = (String) headerNames.nextElement();
			String headerValue = req.getHeader(headerName);
			StringTokenizer values = new StringTokenizer(headerValue, ",");
			while(values.hasMoreTokens()){
				headers.addHeader(headerName, values.nextToken().trim());
			}
		}
		return headers;
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try{
			MimeHeaders headers = BiblioAppServlet.getHeaders(req);
			InputStream is = req.getInputStream();
			SOAPMessage soapRequest = messageFactory.createMessage(headers, is);
			
			SOAPMessage soapResponse = soapHandler.handleSOAPRequest(soapRequest);
			
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("text/xml;charset=\"utf-8\"");
			OutputStream os = resp.getOutputStream();
			soapResponse.writeTo(os);
			os.flush();
		}
		catch(SOAPException e){
			throw new IOException("Exception while creating SOAP message.", e);
		}
	}
}
