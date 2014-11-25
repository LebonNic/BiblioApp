
package fr.isima.webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "searchAuteurs", namespace = "http://webservice.isima.fr/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchAuteurs", namespace = "http://webservice.isima.fr/")
public class SearchAuteurs {

    @XmlElement(name = "nom", namespace = "")
    private String nom;

    /**
     * 
     * @return
     *     returns String
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * 
     * @param nom
     *     the value for the nom property
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

}
