
package fr.isima.webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "searchLivres", namespace = "http://webservice.isima.fr/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchLivres", namespace = "http://webservice.isima.fr/")
public class SearchLivres {

    @XmlElement(name = "titre", namespace = "")
    private String titre;

    /**
     * 
     * @return
     *     returns String
     */
    public String getTitre() {
        return this.titre;
    }

    /**
     * 
     * @param titre
     *     the value for the titre property
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

}
